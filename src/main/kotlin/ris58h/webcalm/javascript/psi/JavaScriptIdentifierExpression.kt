package ris58h.webcalm.javascript.psi

import com.intellij.lang.ASTNode
import com.intellij.psi.PsiReference

class JavaScriptIdentifierExpression(node: ASTNode) : JavaScriptIdentifierOwner(node) {
    override fun getReference(): PsiReference? {
        val nameIdentifier = this.findChildByClass(JavaScriptIdentifier::class.java) ?: return null
        return JavaScriptIdentifierReference(nameIdentifier.text, this, nameIdentifier.textRangeInParent)
    }
}