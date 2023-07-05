package ris58h.webcalm.css

import com.intellij.patterns.PlatformPatterns
import com.intellij.psi.PsiElement
import com.intellij.psi.util.PsiTreeUtil
import ris58h.webcalm.css.completion.CssCompletionContributor
import ris58h.webcalm.css.psi.CssElementFactory
import ris58h.webcalm.css.psi.CssTerm
import ris58h.webcalm.css.psi.CssTypes
import ris58h.webcalm.intellij.ReplaceableElementColorProvider
import java.awt.Color

class CssColorProvider : ReplaceableElementColorProvider {
    override fun getColorFrom(element: PsiElement): Color? {
        if (CssCompletionContributor.IDENTIFIER_TERM_PATTERN.accepts(element)) {
            return CssColors.COLORS_BY_NAME[element.text]
        }
        if (HASH_TERM.accepts(element)) {
            return CssColors.parseHexColor(element.text)
        }
        return null
    }

    override fun replaceColorTo(element: PsiElement, color: Color): PsiElement? {
        if (color == getColorFrom(element)) return null
        val term = PsiTreeUtil.getParentOfType(element, CssTerm::class.java) ?: return null
        val hexColor = CssColors.toHexColor(color)
        var newTerm = CssElementFactory.createTermFromText(element.project, hexColor)
        newTerm = term.replace(newTerm) as CssTerm
        return getLeaf(newTerm)
    }
}

private val HASH_TERM = PlatformPatterns.psiElement(CssTypes.HASH)
    .inside(PlatformPatterns.psiElement(CssTerm::class.java))

private fun getLeaf(element: PsiElement): PsiElement {
    val firstChild = element.firstChild
    return if (firstChild == null) element else getLeaf(firstChild)
}
