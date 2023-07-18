package ris58h.webcalm.javascript.psi

import com.intellij.extapi.psi.ASTWrapperPsiElement
import com.intellij.lang.ASTNode
import com.intellij.psi.PsiNameIdentifierOwner

open class JavaScriptIdentifierOwner(node: ASTNode) : ASTWrapperPsiElement(node), PsiNameIdentifierOwner {
    val identifier: JavaScriptIdentifier?
        get() = this.findChildByClass(JavaScriptIdentifier::class.java)

    override fun getName() = identifier?.name

    override fun setName(name: String) = identifier?.setName(name) ?: this

    override fun getTextOffset() = identifier?.textOffset ?: super.getTextOffset()

    override fun getNameIdentifier() = identifier?.nameIdentifier
}