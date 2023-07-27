package ris58h.antlrkit.grammar.parser;

import org.antlr.runtime.tree.BaseTree;
import org.antlr.runtime.tree.Tree;
import org.antlr.v4.Tool;
import org.antlr.v4.parse.ANTLRParser;
import org.antlr.v4.tool.ast.*;
import ris58h.antlrkit.grammar.Grammar;
import ris58h.antlrkit.grammar.tree.*;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Consumer;

public class GrammarParser {
    public static Grammar parse(String fileName) {
        Tool tool = new Tool();
        GrammarRootAST grammarRootAST = tool.loadGrammar(fileName).ast;
        return new Grammar(
                grammarType(grammarRootAST.grammarType),
                grammarRootAST.hasErrors,
                ruleNodes(grammarRootAST)
        );
    }

    private static Grammar.Type grammarType(int grammarType) {
        switch (grammarType) {
            case ANTLRParser.LEXER: return Grammar.Type.LEXER;
            case ANTLRParser.PARSER: return Grammar.Type.PARSER;
            case ANTLRParser.COMBINED: return Grammar.Type.COMBINED;
            default: throw new IllegalStateException("Unknown grammar type " + grammarType);
        }
    }

    private static List<RuleNode> ruleNodes(GrammarRootAST grammarRootAST) {
        Tree rulesAST = grammarRootAST.getFirstChildWithType(ANTLRParser.RULES);
        List<RuleNode> result = new ArrayList<>();
        forEachChild(rulesAST, child -> result.add(ruleNode((RuleAST) child)));
        return result;
    }

    private static RuleNode ruleNode(RuleAST node) {
        Tree ruleRef = node.getFirstChildWithType(ANTLRParser.RULE_REF);
        return new RuleNode(ruleRef.getText(), innerBlockChildren(node));
    }

    private static AlternativeNode alternative(AltAST node) {
        String label = node.altLabel == null ? null : node.altLabel.getText();
        return new AlternativeNode(label, alternativeChildren(node));
    }

    private static List<Node> alternativeChildren(AltAST node) {
        List<Node> result = new ArrayList<>();
        forEachChild(node, child -> {
            Node childTree = alternativeChild(child);
            if (childTree != null) {
                result.add(childTree);
            }
        });
        return result;
    }

    private static Node alternativeChild(Tree node) {
        if (node instanceof BlockAST) {
            return new BlockNode(blockChildren((BlockAST) node));
        }
        if (node instanceof OptionalBlockAST) {
            return new OptionalNode(innerBlockChildChildren(node));
        }
        if (node instanceof PlusBlockAST) {
            return new PlusNode(innerBlockChildChildren(node));
        }
        if (node instanceof StarBlockAST) {
            return new StarNode(innerBlockChildChildren(node));
        }
        if (node instanceof RuleRefAST) {
            return new RuleRefNode(node.getText());
        }
        if (node instanceof TerminalAST) {
            int type = node.getType();
            if (type == ANTLRParser.TOKEN_REF) {
                return new NamedToken(node.getText());
            } else if (type == ANTLRParser.STRING_LITERAL) {
                return new ImplicitToken(node.getText());
            }
        }
        return null;
    }

    private static List<Node> innerBlockChildChildren(Tree node) {
        List<AlternativeNode> alternativeNodes = innerBlockChildren(node);
        return alternativeNodes.isEmpty() ? List.of() : alternativeNodes.get(0).children;
    }

    private static List<AlternativeNode> innerBlockChildren(Tree node) {
        Tree block = ((BaseTree) node).getFirstChildWithType(ANTLRParser.BLOCK);
        return blockChildren((BlockAST) block);
    }

    private static List<AlternativeNode> blockChildren(BlockAST node) {
        List<AlternativeNode> result = new ArrayList<>();
        forEachChild(node, child -> {
            if (child instanceof AltAST) {
                result.add(alternative((AltAST) child));
            }
        });
        return result;
    }

    private static void forEachChild(Tree node, Consumer<Tree> callback) {
        for (int i = 0; i < node.getChildCount(); i++) {
            callback.accept(node.getChild(i));
        }
    }
}
