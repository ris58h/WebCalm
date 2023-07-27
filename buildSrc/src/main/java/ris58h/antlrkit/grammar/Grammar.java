package ris58h.antlrkit.grammar;

import ris58h.antlrkit.grammar.tree.RuleNode;

import java.util.List;

public class Grammar {
    public final Type type;
    public final boolean hasErrors;
    public final List<RuleNode> rules;

    public Grammar(Type type, boolean hasErrors, List<RuleNode> rules) {
        this.type = type;
        this.hasErrors = hasErrors;
        this.rules = rules;
    }

    public enum Type {
        LEXER, PARSER, COMBINED;
    }
}
