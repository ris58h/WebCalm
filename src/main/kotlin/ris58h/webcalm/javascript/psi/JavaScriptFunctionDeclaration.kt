package ris58h.webcalm.javascript.psi

import com.intellij.lang.ASTNode
import com.intellij.psi.util.PsiTreeUtil

class JavaScriptFunctionDeclaration(node: ASTNode) : JavaScriptIdentifierOwner(node), JavaScriptStatement {
    val parameters: JavaScriptParameters?
        get() = PsiTreeUtil.findChildOfType(this, JavaScriptParameters::class.java)

    val body: JavaScriptFunctionBody?
        get() = PsiTreeUtil.findChildOfType(this, JavaScriptFunctionBody::class.java)
}