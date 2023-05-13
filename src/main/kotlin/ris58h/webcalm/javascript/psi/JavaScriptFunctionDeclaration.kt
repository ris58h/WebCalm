package ris58h.webcalm.javascript.psi

import com.intellij.lang.ASTNode

class JavaScriptFunctionDeclaration(node: ASTNode) : JavaScriptIdentifierOwner(node), JavaScriptStatement {
    val parameters: JavaScriptParameters?
        get() = this.findChildByClass(JavaScriptParameters::class.java)

    val body: JavaScriptFunctionBody?
        get() = this.findChildByClass(JavaScriptFunctionBody::class.java)
}