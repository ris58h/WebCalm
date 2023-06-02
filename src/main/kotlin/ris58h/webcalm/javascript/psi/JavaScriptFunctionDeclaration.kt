package ris58h.webcalm.javascript.psi

import com.intellij.lang.ASTNode

class JavaScriptFunctionDeclaration(node: ASTNode) : JavaScriptNamedIdentifierOwner(node), JavaScriptStatement, JavaScriptDeclaration {
    val parameters: JavaScriptParameters?
        get() = this.findChildByClass(JavaScriptParameters::class.java)

    val body: JavaScriptFunctionBody?
        get() = this.findChildByClass(JavaScriptFunctionBody::class.java)
}