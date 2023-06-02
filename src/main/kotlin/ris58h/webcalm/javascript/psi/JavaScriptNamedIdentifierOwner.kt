package ris58h.webcalm.javascript.psi

import com.intellij.lang.ASTNode
import com.intellij.psi.PsiElement
import com.intellij.psi.PsiNameIdentifierOwner

open class JavaScriptNamedIdentifierOwner(node: ASTNode) : JavaScriptIdentifierOwner(node), PsiNameIdentifierOwner {
    override fun getName(): String? = identifier?.name

    override fun setName(name: String): PsiElement = identifier?.setName(name) ?: this

    override fun getTextOffset(): Int = identifier?.textOffset ?: super.getTextOffset()

    override fun getNameIdentifier(): PsiElement? = identifier?.nameIdentifier
}