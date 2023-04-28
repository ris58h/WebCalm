package ris58h.webcalm.psi

import com.intellij.lang.ASTNode
import com.intellij.psi.PsiElement
import com.intellij.psi.PsiNameIdentifierOwner
import com.intellij.psi.PsiNamedElement
import org.antlr.intellij.adaptor.psi.ANTLRPsiNode

abstract class NameIdentifierSubtree(node: ASTNode) : ANTLRPsiNode(node), PsiNameIdentifierOwner {
    override fun getName(): String? = nameIdentifier?.text

    override fun getTextOffset(): Int = nameIdentifier?.textOffset ?: super.getTextOffset()

    override fun setName(name: String): PsiElement? {
        val idNode = nameIdentifier as PsiNamedElement?
        return if (idNode != null) {
            idNode.setName(name)
        } else this
    }
}