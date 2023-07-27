package ris58h.antlrkit.grammar.tree;

import java.util.List;

public class BlockNode extends CompositeNode<AlternativeNode> {
    public BlockNode(List<AlternativeNode> children) {
        super(children);
    }
}
