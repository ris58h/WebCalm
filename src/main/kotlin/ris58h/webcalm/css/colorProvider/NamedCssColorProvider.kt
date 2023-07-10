package ris58h.webcalm.css.colorProvider

import com.intellij.patterns.PlatformPatterns
import com.intellij.psi.PsiElement
import ris58h.webcalm.css.CssColors
import ris58h.webcalm.css.psi.CssIdentifier
import ris58h.webcalm.css.psi.CssTerm
import ris58h.webcalm.css.psi.CssTypes
import java.awt.Color

class NamedCssColorProvider : TokenCssColorProvider() {
    override fun getColorFrom(element: PsiElement): Color? {
        if (IDENTIFIER_TERM_PATTERN.accepts(element)) {
            return CssColors.COLORS_BY_NAME[element.text]
        }
        return null
    }
}

private val IDENTIFIER_TERM_PATTERN = PlatformPatterns.psiElement(CssTypes.IDENTIFIER)
    .withParent(
        PlatformPatterns.psiElement(CssIdentifier::class.java)
            .withParent(PlatformPatterns.psiElement(CssTerm::class.java))
    )