package ris58h.antlrkit.grammar.tree;

import java.util.List;

public class AlternativeNode extends CompositeNode<Node> implements Node {
    public final String label;

    public AlternativeNode(String label, List<Node> children) {
        super(children);
        this.label = label;
    }
}
