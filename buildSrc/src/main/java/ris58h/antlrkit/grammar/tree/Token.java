package ris58h.antlrkit.grammar.tree;

public class Token implements Node {
    public final String name;

    public Token(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return name;
    }
}
