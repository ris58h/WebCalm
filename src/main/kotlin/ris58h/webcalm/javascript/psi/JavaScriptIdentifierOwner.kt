package ris58h.webcalm.javascript.psi

import com.intellij.extapi.psi.ASTWrapperPsiElement
import com.intellij.lang.ASTNode
import com.intellij.psi.PsiElement
import com.intellij.psi.PsiNameIdentifierOwner
import com.intellij.psi.util.PsiTreeUtil

open class JavaScriptIdentifierOwner(node: ASTNode) : ASTWrapperPsiElement(node), PsiNameIdentifierOwner {
    override fun getName(): String? = nameIdentifier?.text

    override fun setName(name: String): PsiElement {
        val idLeaf = nameIdentifier
        return if (idLeaf != null) {
            val newIdLeaf: PsiElement = JavaScriptElementFactory.createIdentifierTokenFromText(project, name)
            idLeaf.replace(newIdLeaf)
        } else this
    }

    override fun getTextOffset(): Int = nameIdentifier?.textOffset ?: super.getTextOffset()

    override fun getNameIdentifier(): PsiElement? {
        return PsiTreeUtil.findChildOfType(this, JavaScriptIdentifier::class.java)
            ?.node
            ?.findChildByType(JavaScriptTypes.IDENTIFIER)
            ?.psi
    }
}