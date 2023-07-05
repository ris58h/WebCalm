package ris58h.webcalm.css.colorProvider

import com.intellij.patterns.PlatformPatterns
import com.intellij.psi.PsiElement
import ris58h.webcalm.css.CssColors
import ris58h.webcalm.css.psi.CssTerm
import ris58h.webcalm.css.psi.CssTypes
import java.awt.Color

class HexCssColorProvider : TokenCssColorProvider() {
    override fun getColorFrom(element: PsiElement): Color? {
        if (HASH_TERM_PATTERN.accepts(element)) {
            return CssColors.parseHexColor(element.text)
        }
        return null
    }
}

private val HASH_TERM_PATTERN = PlatformPatterns.psiElement(CssTypes.HASH)
    .inside(PlatformPatterns.psiElement(CssTerm::class.java))
