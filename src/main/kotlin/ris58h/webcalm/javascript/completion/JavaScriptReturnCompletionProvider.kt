package ris58h.webcalm.javascript.completion

import com.intellij.codeInsight.completion.CompletionParameters
import com.intellij.codeInsight.completion.CompletionProvider
import com.intellij.codeInsight.completion.CompletionResultSet
import com.intellij.util.ProcessingContext

class JavaScriptReturnCompletionProvider : CompletionProvider<CompletionParameters>() {
    override fun addCompletions(
        parameters: CompletionParameters,
        context: ProcessingContext,
        result: CompletionResultSet
    ) {
        result.addElement(JavaScriptLookupElements.NULL)
        result.addElement(JavaScriptLookupElements.TRUE)
        result.addElement(JavaScriptLookupElements.FALSE)
        result.addElement(JavaScriptLookupElements.IF)
        result.addElement(JavaScriptLookupElements.FUNCTION)
        result.addElement(JavaScriptLookupElements.NEW)
        result.addElement(JavaScriptLookupElements.AWAIT)
        result.addElement(JavaScriptLookupElements.VOID)
    }
}