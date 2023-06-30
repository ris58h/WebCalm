package ris58h.webcalm.css

import com.intellij.lang.refactoring.RefactoringSupportProvider
import com.intellij.psi.PsiElement
import ris58h.webcalm.css.psi.CssVariable

class CssRefactoringSupportProvider : RefactoringSupportProvider() {
    override fun isInplaceRenameAvailable(element: PsiElement, context: PsiElement?): Boolean {
        //TODO: inplace rename fails in InplaceRefactoring
        return element is CssVariable
    }
}