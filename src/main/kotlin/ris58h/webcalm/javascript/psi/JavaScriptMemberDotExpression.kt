package ris58h.webcalm.javascript.psi

import com.intellij.extapi.psi.ASTWrapperPsiElement
import com.intellij.lang.ASTNode

class JavaScriptMemberDotExpression(node: ASTNode) : ASTWrapperPsiElement(node), JavaScriptExpression {
    val member: JavaScriptIdentifierName?
        get() = this.findChildByClass(JavaScriptIdentifierName::class.java)
}