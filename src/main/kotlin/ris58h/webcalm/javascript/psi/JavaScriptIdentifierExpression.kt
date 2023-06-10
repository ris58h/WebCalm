package ris58h.webcalm.javascript.psi

import com.intellij.extapi.psi.ASTWrapperPsiElement
import com.intellij.lang.ASTNode

class JavaScriptIdentifierExpression(node: ASTNode) : ASTWrapperPsiElement(node), JavaScriptExpression {
    val identifier: JavaScriptIdentifier?
        get() = this.findChildByClass(JavaScriptIdentifier::class.java)
}