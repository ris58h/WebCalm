package ris58h.webcalm.javascript.psi

import com.intellij.lang.ASTNode
import com.intellij.psi.util.PsiTreeUtil

class JavaScriptArgument(node: ASTNode) : JavaScriptIdentifierOwner(node) {
    override fun findJavaScriptIdentifier(): JavaScriptIdentifier? {
        return PsiTreeUtil.findChildOfType(this, JavaScriptAssignable::class.java)
            ?.findJavaScriptIdentifier()
    }
}