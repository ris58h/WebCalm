package ris58h.webcalm.css

import com.intellij.codeInsight.daemon.LineMarkerInfo
import com.intellij.codeInsight.daemon.LineMarkerProvider
import com.intellij.openapi.editor.markup.GutterIconRenderer
import com.intellij.openapi.project.DumbAware
import com.intellij.patterns.PlatformPatterns
import com.intellij.psi.PsiElement
import com.intellij.util.ui.ColorIcon
import ris58h.webcalm.css.completion.CssCompletionContributor
import ris58h.webcalm.css.psi.CssTerm
import ris58h.webcalm.css.psi.CssTypes
import java.awt.Color
import java.util.function.Supplier

class CssColorLineMarkerProvider : LineMarkerProvider, DumbAware {
    override fun getLineMarkerInfo(element: PsiElement): LineMarkerInfo<*>? {
        if (CssCompletionContributor.IDENTIFIER_TERM_PATTERN.accepts(element)) {
            val text = element.text
            val color = CssColors.COLORS_BY_NAME[text]
            if (color != null) {
                return ColorMarkerInfo(element, color)
            }
        }
        if (HASH_TERM.accepts(element)) {
            val text = element.text
            val color = Color.decode(text)
            return ColorMarkerInfo(element, color)
        }
        return null
    }

    private class ColorMarkerInfo(e: PsiElement, color: Color) : LineMarkerInfo<PsiElement>(
        e,
        e.textRange,
        ColorIcon(8, color),
        null,
        null,
        GutterIconRenderer.Alignment.RIGHT,
        Supplier { "Color" }
    )
}

private val HASH_TERM = PlatformPatterns.psiElement(CssTypes.HASH)
    .inside(PlatformPatterns.psiElement(CssTerm::class.java))
