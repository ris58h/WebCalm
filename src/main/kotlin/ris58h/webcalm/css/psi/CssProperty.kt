package ris58h.webcalm.css.psi

import com.intellij.extapi.psi.ASTWrapperPsiElement
import com.intellij.lang.ASTNode
import com.intellij.psi.PsiElement
import org.antlr.intellij.adaptor.psi.Trees

class CssProperty(node: ASTNode) : ASTWrapperPsiElement(node) {
    // We override it to return all nodes not only composite ones.
    override fun getChildren(): Array<PsiElement> = Trees.getChildren(this)
}