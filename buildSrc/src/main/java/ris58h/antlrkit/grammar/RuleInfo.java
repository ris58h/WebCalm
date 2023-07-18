package ris58h.antlrkit.grammar;

import java.util.HashMap;
import java.util.Map;

public class RuleInfo {
    public final String name;
    public final String prototypeName;
    public boolean isPrototype;
    // null|false - single; true - multiple.
    public final Map<String, Boolean> subRuleFrequencies = new HashMap<>();

    public RuleInfo(String name) {
        this(name, null);
    }

    public RuleInfo(String name, String prototypeName) {
        this.name = name;
        this.prototypeName = prototypeName;
    }

    @Override
    public String toString() {
        return "RuleInfo{" +
                "name='" + name + '\'' +
                ", prototypeName='" + prototypeName + '\'' +
                ", isPrototype=" + isPrototype +
                ", subRuleFrequencies=" + subRuleFrequencies +
                '}';
    }
}