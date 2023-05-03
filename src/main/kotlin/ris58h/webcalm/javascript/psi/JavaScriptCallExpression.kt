package ris58h.webcalm.javascript.psi

import com.intellij.lang.ASTNode
import com.intellij.psi.PsiReference
import com.intellij.psi.util.PsiTreeUtil
import org.antlr.intellij.adaptor.psi.ANTLRPsiNode

class JavaScriptCallExpression(node: ASTNode) : ANTLRPsiNode(node) {
    override fun getReference(): PsiReference? {
        val expression = PsiTreeUtil.findChildOfType(this, JavaScriptExpression::class.java)
        val nameIdentifier = PsiTreeUtil.findChildOfType(expression, JavaScriptIdentifier::class.java) ?: return null
        return JavaScriptFunctionReference(nameIdentifier.text, this, nameIdentifier.textRangeInParent)
    }
}