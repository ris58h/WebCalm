package ris58h.webcalm.javascript.psi

import com.intellij.lang.ASTNode
import com.intellij.psi.PsiReference

class JavaScriptContinueStatement(node: ASTNode) : JavaScriptIdentifierOwner(node), JavaScriptStatement {
    override fun getReference(): PsiReference? {
        val identifier = identifier ?: return null
        return JavaScriptLabelReference(identifier.text, this, identifier.textRangeInParent)
    }
}