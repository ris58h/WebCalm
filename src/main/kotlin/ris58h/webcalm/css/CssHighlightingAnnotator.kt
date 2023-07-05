package ris58h.webcalm.css

import com.intellij.lang.annotation.AnnotationHolder
import com.intellij.lang.annotation.Annotator
import com.intellij.lang.annotation.HighlightSeverity
import com.intellij.openapi.editor.DefaultLanguageHighlighterColors
import com.intellij.openapi.project.DumbAware
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
                if (element.node.elementType == CssTypes.STRING) DefaultLanguageHighlighterColors.STRING
                else null
            }
        }

        if (color != null) {
            holder.newSilentAnnotation(HighlightSeverity.INFORMATION)
                .textAttributes(color)
                .create()
        }
    }
}