package ris58h.webcalm.javascript.psi

import com.intellij.extapi.psi.ASTWrapperPsiElement
import com.intellij.lang.ASTNode

class JavaScriptAssignmentExpression(node: ASTNode) : ASTWrapperPsiElement(node), JavaScriptExpression {
    val left: JavaScriptExpression?
        get() = firstChild as? JavaScriptExpression

    val right: JavaScriptExpression?
        get() = if (firstChild == lastChild) null else lastChild as? JavaScriptExpression
}