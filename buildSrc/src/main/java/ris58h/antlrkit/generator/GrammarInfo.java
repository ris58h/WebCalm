package ris58h.antlrkit.generator;

import ris58h.antlrkit.grammar.Grammar;
import ris58h.antlrkit.grammar.tree.*;

import java.util.*;
import java.util.function.Consumer;

class GrammarInfo {
    public final List<RuleInfo> ruleInfos = new ArrayList<>();
    public final Set<String> tokenNames = new LinkedHashSet<>();
    public final Set<String> implicitTokens = new LinkedHashSet<>();

    private GrammarInfo() {}

    public static GrammarInfo from(Grammar grammar) {
        GrammarInfo grammarInfo = new GrammarInfo();
        collectInfos(grammar.rules, grammarInfo.ruleInfos, grammarInfo.tokenNames, grammarInfo.implicitTokens);
        return grammarInfo;
    }

    private static void collectInfos(
            List<RuleNode> ruleNodes,
            List<RuleInfo> ruleInfos,
            Set<String> tokenNames,
            Set<String> implicitTokens
    ) {
        for (RuleNode ruleNode : ruleNodes) {
            processTree(ruleNode, node -> {
                if (node instanceof RuleNode) {
                    RuleInfo ruleInfo = new RuleInfo(((RuleNode) node).name);
                    ruleInfos.add(ruleInfo);
                }
                if (node instanceof AlternativeNode) {
                    String label = ((AlternativeNode) node).label;
                    if (label != null) {
                        ruleInfos.add(new RuleInfo(label));
                    }
                }
                // TODO compute frequencies
//                if (node instanceof RuleRefNode) {
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
//                }
                if (node instanceof NamedToken) {
                    tokenNames.add(((NamedToken) node).name);
                }
                if (node instanceof ImplicitToken) {
                    implicitTokens.add(((ImplicitToken) node).name);
                }
            });
        }
    }

    private static void processTree(Node node, Consumer<Node> callback) {
        callback.accept(node);
        if (node instanceof CompositeNode) {
            List<Node> children = ((CompositeNode) node).children;
            for (Node child : children) {
                processTree(child, callback);
            }
        }
    }
}
