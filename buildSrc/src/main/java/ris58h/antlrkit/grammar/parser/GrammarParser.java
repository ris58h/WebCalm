package ris58h.antlrkit.grammar.parser;

import org.antlr.runtime.tree.CommonTree;
import org.antlr.runtime.tree.Tree;
import org.antlr.v4.Tool;
import org.antlr.v4.parse.ANTLRParser;
import org.antlr.v4.tool.Grammar;
import org.antlr.v4.tool.ast.*;
import ris58h.antlrkit.grammar.GrammarInfo;
import ris58h.antlrkit.grammar.RuleInfo;

import java.util.List;
import java.util.Map;
import java.util.Set;

public class GrammarParser {
    public static GrammarInfo parse(String fileName) {
        Tool tool = new Tool();
        Grammar grammar = tool.loadGrammar(fileName);
        return grammarInfo(grammar);
    }

    private static GrammarInfo grammarInfo(Grammar grammar) {
        GrammarRootAST grammarRootAST = grammar.ast;
        GrammarInfo grammarInfo = new GrammarInfo(grammarType(grammar.getType()), grammarRootAST.hasErrors);
        collectInfos(grammarRootAST, grammarInfo.ruleInfos, grammarInfo.tokenNames, grammarInfo.implicitTokens);
        return grammarInfo;
    }

    private static GrammarInfo.GrammarType grammarType(int type) {
        switch (type) {
            case ANTLRParser.LEXER: return GrammarInfo.GrammarType.LEXER;
            case ANTLRParser.PARSER: return GrammarInfo.GrammarType.PARSER;
            case ANTLRParser.COMBINED: return GrammarInfo.GrammarType.COMBINED;
            default: throw new IllegalStateException("Unknown grammar type " + type);
        }
    }

    private static void collectInfos(
            GrammarRootAST grammarRootAST,
            List<RuleInfo> ruleInfos,
            Set<String> tokenNames,
            Set<String> implicitTokens
    ) {
        grammarRootAST.visit(new RecursiveGrammarASTVisitor() {
            RuleInfo prototype;

            RuleInfo currentRuleInfo() {
                return ruleInfos.get(ruleInfos.size() - 1);
            }

            @Override
            public Object visit(RuleAST node) {
                Tree ruleRef = node.getFirstChildWithType(ANTLRParser.RULE_REF);
                if (ruleRef != null) {
                    String ruleName = ruleRef.getText();
                    RuleInfo ruleInfo = new RuleInfo(ruleName);
                    ruleInfos.add(ruleInfo);
                    prototype = ruleInfo;
                }
                return super.visit(node);
            }

            @Override
            public Object visit(AltAST node) {
                GrammarAST altLabel = node.altLabel;
                if (altLabel != null) {
                    String label = altLabel.getText();
                    ruleInfos.add(new RuleInfo(label, prototype.name));
                    prototype.isPrototype = true;
                }
                return super.visit(node);
            }

            @Override
            public Object visit(RuleRefAST node) {
                String ruleName = node.getText();
                boolean frequency = computeFrequency(node);
                Map<String, Boolean> subRules = currentRuleInfo().subRuleFrequencies;
                // TODO different merge strategies for alternatives and labeled alternatives. See 'exportFromBlock'
//                exportFromBlock
//                      : importNamespace importFrom eos
//                      | exportModuleItems importFrom? eos
//                      ;
                // So we should use OR when it's not labeled
                // TODO multiple elements in the same alternative is always true! See 'importNamespace'
//                importNamespace
//                      : ('*' | identifierName) (As identifierName)?
//                      ;
                subRules.merge(ruleName, frequency, (a, b) -> a || b);
                return super.visit(node);
            }

            private boolean computeFrequency(CommonTree node) {
                if (node == null || node instanceof RuleAST) {
                    return false;
                }
                if (node instanceof StarBlockAST || node instanceof PlusBlockAST) {
                    return true;
                }
                return computeFrequency(node.parent);
            }

            @Override
            public Object visit(TerminalAST node) {
                int type = node.getType();
                if (type == ANTLRParser.TOKEN_REF) {
                    tokenNames.add(node.getText());
                } else if (type == ANTLRParser.STRING_LITERAL) {
                    implicitTokens.add(node.getText());
                }
                return super.visit(node);
            }
        });
    }
}
