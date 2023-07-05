package ris58h.webcalm.css.colorProvider

import com.intellij.psi.PsiElement
import com.intellij.psi.util.PsiTreeUtil
import ris58h.webcalm.css.CssColors
import ris58h.webcalm.css.psi.CssElementHelper
import ris58h.webcalm.css.psi.CssTerm
import ris58h.webcalm.intellij.ReplaceableElementColorProvider
import java.awt.Color

abstract class TokenCssColorProvider : ReplaceableElementColorProvider {
    override fun replaceColorTo(element: PsiElement, color: Color): PsiElement? {
        if (color == getColorFrom(element)) return null
        val term = PsiTreeUtil.getParentOfType(element, CssTerm::class.java) ?: return null
        val hexColor = CssColors.toHexColor(color)
        CssElementHelper.updateTermContent(term, hexColor)
        return getLeaf(term)
    }
}

private fun getLeaf(element: PsiElement): PsiElement {
    val firstChild = element.firstChild
    return if (firstChild == null) element else getLeaf(firstChild)
}