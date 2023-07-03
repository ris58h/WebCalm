package ris58h.webcalm.css

import com.intellij.lang.findUsages.EmptyFindUsagesProvider
import com.intellij.psi.PsiElement
import ris58h.webcalm.css.psi.CssVariable

class CssFindUsagesProvider : EmptyFindUsagesProvider() {
    override fun getWordsScanner() = CssWordsScanner()

    override fun canFindUsagesFor(element: PsiElement): Boolean {
        return element is CssVariable && element.isDeclaration
    }
}