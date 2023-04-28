package ris58h.webcalm.javascript.psi

import com.intellij.lang.ASTNode
import com.intellij.psi.util.PsiTreeUtil

class JavaScriptCallExpression(node: ASTNode) : JavaScriptIdentifierOwner(node) {
    override fun findJavaScriptIdentifier(): JavaScriptIdentifier? {
        val expression = PsiTreeUtil.findChildOfType(this, JavaScriptExpression::class.java)
        return PsiTreeUtil.findChildOfType(expression, JavaScriptIdentifier::class.java)
    }
}