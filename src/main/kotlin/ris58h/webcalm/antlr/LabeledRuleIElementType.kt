package ris58h.webcalm.antlr

import com.intellij.lang.Language
import org.antlr.intellij.adaptor.lexer.RuleIElementType

class LabeledRuleIElementType(val label: String, ruleIndex: Int, debugName: String, language: Language?) :
    RuleIElementType(
        ruleIndex,
        debugName,
        language
    ) {
    companion object {
        fun create(label: String, prototype: RuleIElementType) =
            LabeledRuleIElementType(label, prototype.ruleIndex, label, prototype.language)
    }
}