package ris58h.webcalm.css.psi

import com.intellij.extapi.psi.ASTWrapperPsiElement
import com.intellij.lang.ASTNode
import com.intellij.psi.util.PsiTreeUtil

class CssBlock(node: ASTNode) : ASTWrapperPsiElement(node) {
    val statements: List<CssNestedStatement>
        get() = PsiTreeUtil.getChildrenOfTypeAsList(this, CssNestedStatement::class.java)
}