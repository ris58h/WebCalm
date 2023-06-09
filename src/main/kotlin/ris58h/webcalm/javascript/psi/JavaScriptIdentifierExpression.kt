package ris58h.webcalm.javascript.psi

import com.intellij.lang.ASTNode
import com.intellij.psi.PsiReference

class JavaScriptIdentifierExpression(node: ASTNode) : JavaScriptIdentifierOwner(node), JavaScriptExpression {
    override fun getReference(): PsiReference? {
        val identifier = identifier
        if (identifier == null || identifier.introducesName()) return null
        return JavaScriptIdentifierReference(identifier.text, identifier, identifier.textRangeInParent)
    }
}