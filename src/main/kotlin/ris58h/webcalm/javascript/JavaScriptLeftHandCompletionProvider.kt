package ris58h.webcalm.javascript

import com.intellij.codeInsight.completion.CompletionParameters
import com.intellij.codeInsight.completion.CompletionProvider
import com.intellij.codeInsight.completion.CompletionResultSet
import com.intellij.codeInsight.lookup.LookupElementBuilder
import com.intellij.util.ProcessingContext

class JavaScriptLeftHandCompletionProvider : CompletionProvider<CompletionParameters>() {
    override fun addCompletions(
        parameters: CompletionParameters,
        context: ProcessingContext,
        result: CompletionResultSet
    ) {
        result.addElement(LookupElementBuilder.create("true").bold())
        result.addElement(LookupElementBuilder.create("false").bold())
        result.addElement(LookupElementBuilder.create("if").bold())
        result.addElement(LookupElementBuilder.create("function").bold())
        result.addElement(LookupElementBuilder.create("new").bold())
        result.addElement(LookupElementBuilder.create("await").bold())
        result.addElement(LookupElementBuilder.create("void").bold())
    }
}