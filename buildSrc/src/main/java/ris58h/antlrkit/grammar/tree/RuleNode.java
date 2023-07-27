package ris58h.antlrkit.grammar.tree;

import java.util.List;

public class RuleNode extends CompositeNode<AlternativeNode> implements Node {
    public final String name;

    public RuleNode(String name, List<AlternativeNode> children) {
        super(children);
        this.name = name;
    }
}
