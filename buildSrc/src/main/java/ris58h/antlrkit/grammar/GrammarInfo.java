package ris58h.antlrkit.grammar;

import java.util.*;

public class GrammarInfo {
    public final GrammarType type;
    public final boolean hasErrors;
    public final List<RuleInfo> ruleInfos = new ArrayList<>();
    public final Set<String> tokenNames = new LinkedHashSet<>();
    public final Set<String> implicitTokens = new LinkedHashSet<>();

    public GrammarInfo(GrammarType type, boolean hasErrors) {
        this.type = type;
        this.hasErrors = hasErrors;
    }

    public enum GrammarType {
        LEXER, PARSER, COMBINED;
    }
}
