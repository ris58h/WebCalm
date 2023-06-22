package ris58h.webcalm.javascript.completion

import com.intellij.codeInsight.completion.InsertHandler
import com.intellij.codeInsight.lookup.LookupElement
import com.intellij.codeInsight.lookup.LookupElementBuilder

object JavaScriptLookupElements {
    val IF = keywordWithParentheses("if")
    val WHILE = keywordWithParentheses("while")
    val FOR = keywordWithParentheses("for")
    val SWITCH = keywordWithParentheses("switch")

    private fun keywordWithParentheses(keyword: String): LookupElementBuilder {
        return LookupElementBuilder.create("$keyword ()")
            .withInsertHandler(moveCaretRelatively(-1, 0))
            .withPresentableText(keyword).bold()
            .withTailText(" (...)")
    }

    private fun moveCaretRelatively(columnShift: Int, lineShift: Int): InsertHandler<LookupElement> {
        return InsertHandler { context, _ -> context.editor.caretModel.moveCaretRelatively(columnShift, lineShift, false, false, true) }
    }
}