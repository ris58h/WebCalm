package ris58h.webcalm.css.completion

import com.intellij.codeInsight.completion.CompletionParameters
import com.intellij.codeInsight.completion.CompletionProvider
import com.intellij.codeInsight.completion.CompletionResultSet
import com.intellij.codeInsight.completion.InsertHandler
import com.intellij.codeInsight.lookup.LookupElement
import com.intellij.codeInsight.lookup.LookupElementBuilder
import com.intellij.psi.util.PsiTreeUtil
import com.intellij.util.ProcessingContext
import ris58h.webcalm.css.psi.CssDeclaration

class CssPropertyValueCompletionProvider : CompletionProvider<CompletionParameters>() {
    override fun addCompletions(
        parameters: CompletionParameters,
        context: ProcessingContext,
        result: CompletionResultSet
    ) {
        result.addElement(LookupElementBuilder.create("initial"))
        result.addElement(LookupElementBuilder.create("inherit"))

        val declaration = PsiTreeUtil.getParentOfType(parameters.position, CssDeclaration::class.java)
        val propertyName = declaration?.property?.text
        val lookupElements = CssPropertyLookupElements.PROPERTY_VALUES_LOOKUP_ELEMENTS[propertyName]
        if (lookupElements != null) {
            result.addAllElements(lookupElements)
        }

        result.addElement(keywordWithParentheses("calc"))
        result.addElement(keywordWithParentheses("var"))
    }
}

private fun keywordWithParentheses(keyword: String): LookupElement {
    return LookupElementBuilder.create("$keyword()")
        .withInsertHandler(moveCaretRelatively(-1, 0))
        .withPresentableText(keyword).bold()
        .withTailText("(...)")
}

private fun moveCaretRelatively(columnShift: Int, lineShift: Int): InsertHandler<LookupElement> {
    return InsertHandler { context, _ -> context.editor.caretModel.moveCaretRelatively(columnShift, lineShift, false, false, true) }
}
