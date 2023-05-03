package ris58h.webcalm.javascript.psi

import com.intellij.extapi.psi.ASTWrapperPsiElement
import com.intellij.lang.ASTNode
import com.intellij.psi.PsiReference
import com.intellij.psi.util.PsiTreeUtil

class JavaScriptIdentifierExpression(node: ASTNode) : ASTWrapperPsiElement(node) {
    override fun getReference(): PsiReference? {
        val nameIdentifier = PsiTreeUtil.findChildOfType(this, JavaScriptIdentifier::class.java) ?: return null
        return JavaScriptIdentifierReference(nameIdentifier.text, this, nameIdentifier.textRangeInParent)
    }
}