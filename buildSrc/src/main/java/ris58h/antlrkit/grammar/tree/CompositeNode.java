package ris58h.antlrkit.grammar.tree;

import java.util.List;

public class CompositeNode<T extends Node> implements Node {
    public final List<T> children;

    public CompositeNode(List<T> children) {
        this.children = children;
    }
}
