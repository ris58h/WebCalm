package ris58h.webcalm.javascript.psi

import com.intellij.extapi.psi.ASTWrapperPsiElement
import com.intellij.lang.ASTNode

class JavaScriptPropertyAssignment(node: ASTNode) : ASTWrapperPsiElement(node) {
    //TODO: support other types of assignment
    val propertyShorthand: JavaScriptIdentifierExpression?
        get() = this.findChildByClass(JavaScriptIdentifierExpression::class.java)
}