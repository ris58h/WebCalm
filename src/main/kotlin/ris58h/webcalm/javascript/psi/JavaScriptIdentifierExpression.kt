package ris58h.webcalm.javascript.psi

import com.intellij.lang.ASTNode
import com.intellij.psi.PsiReference
import com.intellij.psi.util.PsiTreeUtil
import org.antlr.intellij.adaptor.psi.ANTLRPsiNode

class JavaScriptIdentifierExpression(node: ASTNode) : ANTLRPsiNode(node) {
    override fun getReference(): PsiReference? {
        val nameIdentifier = PsiTreeUtil.findChildOfType(this, JavaScriptIdentifier::class.java) ?: return null
        return JavaScriptIdentifierReference(nameIdentifier.text, this, nameIdentifier.textRangeInParent)
    }
}