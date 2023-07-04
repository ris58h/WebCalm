package ris58h.webcalm.css.completion

import com.intellij.codeInsight.completion.CompletionParameters
import com.intellij.codeInsight.completion.CompletionProvider
import com.intellij.codeInsight.completion.CompletionResultSet
import com.intellij.psi.util.PsiTreeUtil
import com.intellij.util.ProcessingContext
import ris58h.webcalm.css.psi.CssDeclaration

class CssPropertyValueCompletionProvider : CompletionProvider<CompletionParameters>() {
    override fun addCompletions(
        parameters: CompletionParameters,
        context: ProcessingContext,
        result: CompletionResultSet
    ) {
        result.addElement(CssPropertyLookupElements.value("initial"))
        result.addElement(CssPropertyLookupElements.value("inherit"))
        result.addElement(CssPropertyLookupElements.value("unset"))

        val declaration = PsiTreeUtil.getParentOfType(parameters.position, CssDeclaration::class.java)
        val propertyName = declaration?.property?.text
        val lookupElements = CssPropertyLookupElements.PROPERTY_VALUES_LOOKUP_ELEMENTS[propertyName]
        if (lookupElements != null) {
            result.addAllElements(lookupElements)
        }

        result.addElement(CssPropertyLookupElements.function("calc"))
        result.addElement(CssPropertyLookupElements.function("var"))
    }
}
