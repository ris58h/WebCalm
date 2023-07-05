package ris58h.webcalm.css.psi

import com.intellij.extapi.psi.ASTWrapperPsiElement
import com.intellij.lang.ASTNode
import com.intellij.psi.util.PsiTreeUtil

open class CssFunctionBase(node: ASTNode) : ASTWrapperPsiElement(node) {
    val functionName: String
        get() = firstChild.text

    val arguments: List<CssTerm>
        get() = PsiTreeUtil.getChildrenOfTypeAsList(this, CssTerm::class.java)
}