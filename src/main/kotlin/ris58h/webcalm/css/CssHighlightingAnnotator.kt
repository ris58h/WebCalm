package ris58h.webcalm.css

import com.intellij.lang.annotation.AnnotationHolder
import com.intellij.lang.annotation.Annotator
import com.intellij.lang.annotation.HighlightSeverity
import com.intellij.openapi.editor.DefaultLanguageHighlighterColors
import com.intellij.openapi.project.DumbAware
import com.intellij.openapi.util.TextRange
import com.intellij.psi.PsiElement
import ris58h.webcalm.css.psi.*

class CssHighlightingAnnotator : Annotator, DumbAware {
    override fun annotate(element: PsiElement, holder: AnnotationHolder) {
        val color = when (element) {
            is CssTypeSelector -> DefaultLanguageHighlighterColors.IDENTIFIER
            is CssClassNameSelector -> DefaultLanguageHighlighterColors.CLASS_REFERENCE
            is CssPseudoSelector -> DefaultLanguageHighlighterColors.CLASS_REFERENCE
            is CssNotSelector -> DefaultLanguageHighlighterColors.CLASS_REFERENCE
            is CssAttributeSelector -> DefaultLanguageHighlighterColors.KEYWORD
            is CssProperty -> {
                if (element.firstChild is CssVariable) null
                else DefaultLanguageHighlighterColors.MARKUP_ATTRIBUTE
            }
            is CssTerm -> {
                if (element.firstChild is CssIdentifier) DefaultLanguageHighlighterColors.MARKUP_ENTITY
                else null
            }
            else -> {
                when (element.node.elementType) {
                    CssTypes.STRING -> CssSyntaxHighlighterColors.STRING
                    CssTypes.URL -> {
                        // URL token highlighting is complex, so we highlight it and return early.
                        highlightUrlToken(element, holder)
                        return
                    }
                    else -> null
                }
            }
        }

        if (color != null) {
            holder.newSilentAnnotation(HighlightSeverity.INFORMATION)
                .textAttributes(color)
                .create()
        }
    }

    private fun highlightUrlToken(element: PsiElement, holder: AnnotationHolder) {
        val textRange = element.textRange
        holder.newSilentAnnotation(HighlightSeverity.INFORMATION)
            .textAttributes(CssSyntaxHighlighterColors.FUNCTION_CALL)
            .range(TextRange(textRange.startOffset, textRange.startOffset + 4))
            .create()
        // Check for non-empty URL in `url()` token.
        if (textRange.length > 5) {
            holder.newSilentAnnotation(HighlightSeverity.INFORMATION)
                .textAttributes(CssSyntaxHighlighterColors.STRING)
                .range(TextRange(textRange.startOffset + 4, textRange.endOffset - 1))
                .create()
        }
    }
}