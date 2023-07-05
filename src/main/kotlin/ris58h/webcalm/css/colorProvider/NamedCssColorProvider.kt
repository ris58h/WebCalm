package ris58h.webcalm.css.colorProvider

import com.intellij.psi.PsiElement
import ris58h.webcalm.css.CssColors
import ris58h.webcalm.css.completion.CssCompletionContributor
import java.awt.Color

class NamedCssColorProvider : TokenCssColorProvider() {
    override fun getColorFrom(element: PsiElement): Color? {
        if (CssCompletionContributor.IDENTIFIER_TERM_PATTERN.accepts(element)) {
            return CssColors.COLORS_BY_NAME[element.text]
        }
        return null
    }
}