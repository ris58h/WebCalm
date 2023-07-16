package ris58h.webcalm.antlr

import com.intellij.lang.Language
import org.antlr.intellij.adaptor.lexer.RuleIElementType

class LabeledRuleIElementType(val labelName: String, ruleIndex: Int, debugName: String, language: Language?) :
    RuleIElementType(
        ruleIndex,
        debugName,
        language
    ) {
    companion object {
        fun create(labelName: String, prototype: RuleIElementType) =
            LabeledRuleIElementType(labelName, prototype.ruleIndex, labelName, prototype.language)
    }
}