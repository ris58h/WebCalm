package ris58h.webcalm.javascript.completion

import com.intellij.codeInsight.lookup.LookupElement
import com.intellij.codeInsight.lookup.LookupElementBuilder
import com.intellij.codeInsight.lookup.LookupElementPresentation
import com.intellij.codeInsight.lookup.LookupElementRenderer

object JavaScriptLookupElements {
    val IF = create("if (...)", "if ()", -1, 0)
    val WHILE = create("while (...)", "while ()", -1, 0)
    val FOR = create("for (...)", "for ()", -1, 0)

    // TODO: is there more elegant way to do it?
    private fun create(presentationText: String, insertionText: String, columnShift: Int, lineShift: Int): LookupElementBuilder {
        return LookupElementBuilder.create(insertionText)
            .withRenderer(object : LookupElementRenderer<LookupElement>() {
                override fun renderElement(element: LookupElement, presentation: LookupElementPresentation) {
                    presentation.itemText = presentationText
                }
            })
            .withInsertHandler { insertionContext, _ ->
                insertionContext.editor.caretModel.moveCaretRelatively(columnShift, lineShift, false, false, true)
            }
    }
}