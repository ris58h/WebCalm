package ris58h.webcalm.css.completion

import com.intellij.codeInsight.completion.CompletionParameters
import com.intellij.codeInsight.completion.CompletionProvider
import com.intellij.codeInsight.completion.CompletionResultSet
import com.intellij.codeInsight.lookup.LookupElement
import com.intellij.codeInsight.lookup.LookupElementBuilder
import com.intellij.util.ProcessingContext

class CssAtKeywordCompletionProvider : CompletionProvider<CompletionParameters>() {
    override fun addCompletions(
        parameters: CompletionParameters,
        context: ProcessingContext,
        result: CompletionResultSet
    ) {
        result.addElement(atKeyword("charset"))
        result.addElement(atKeyword("container"))
        result.addElement(atKeyword("counter-style"))
        result.addElement(atKeyword("font-face"))
        result.addElement(atKeyword("font-feature-values"))
        result.addElement(atKeyword("import"))
        result.addElement(atKeyword("keyframes"))
        result.addElement(atKeyword("layer"))
        result.addElement(atKeyword("media"))
        result.addElement(atKeyword("namespace"))
        result.addElement(atKeyword("page"))
        result.addElement(atKeyword("property"))
        result.addElement(atKeyword("supports"))
    }

    private fun atKeyword(keyword: String): LookupElement {
        return LookupElementBuilder.create(keyword)
            .withPresentableText("@$keyword")
    }
}