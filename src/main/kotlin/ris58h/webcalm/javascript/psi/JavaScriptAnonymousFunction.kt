package ris58h.webcalm.javascript.psi

import com.intellij.extapi.psi.ASTWrapperPsiElement
import com.intellij.lang.ASTNode

class JavaScriptAnonymousFunction(node: ASTNode) : ASTWrapperPsiElement(node) {
    //TODO: support single expression parameter
    val parameters: JavaScriptParameters?
        get() = this.findChildByClass(JavaScriptParameters::class.java)

    val body: JavaScriptFunctionBody?
        get() = this.findChildByClass(JavaScriptFunctionBody::class.java)
}