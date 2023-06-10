package ris58h.webcalm.javascript.psi

import com.intellij.extapi.psi.ASTWrapperPsiElement
import com.intellij.lang.ASTNode
import com.intellij.psi.PsiElement
import com.intellij.psi.PsiNameIdentifierOwner

open class JavaScriptIdentifierOwner(node: ASTNode) : ASTWrapperPsiElement(node), PsiNameIdentifierOwner {
    val identifier: JavaScriptIdentifier?
        get() = this.findChildByClass(JavaScriptIdentifier::class.java)

    override fun getName(): String? = identifier?.name

    override fun setName(name: String): PsiElement = identifier?.setName(name) ?: this

    override fun getTextOffset(): Int = identifier?.textOffset ?: super.getTextOffset()

    override fun getNameIdentifier(): PsiElement? = identifier?.nameIdentifier
}