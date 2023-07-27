package ris58h.antlrkit.grammar.tree;

public class RuleRefNode implements Node {
    public final String name;

    public RuleRefNode(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return name;
    }
}
