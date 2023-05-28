package ris58h.webcalm.javascript.psi

import com.intellij.extapi.psi.ASTWrapperPsiElement
import com.intellij.lang.ASTNode

class JavaScriptFormalRestParameter(node: ASTNode) : ASTWrapperPsiElement(node), JavaScriptParameter {
    val identifierExpression: JavaScriptIdentifierExpression?
        get() = this.findChildByClass(JavaScriptIdentifierExpression::class.java)
}