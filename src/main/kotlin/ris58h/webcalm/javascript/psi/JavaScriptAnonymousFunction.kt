package ris58h.webcalm.javascript.psi

import com.intellij.extapi.psi.ASTWrapperPsiElement
import com.intellij.lang.ASTNode
import ris58h.webcalm.util.Union2

class JavaScriptAnonymousFunction(node: ASTNode) : ASTWrapperPsiElement(node) {
    val parameters: Union2<JavaScriptParameters, JavaScriptIdentifier>?
        get() {
            val parameters = this.findChildByClass(JavaScriptParameters::class.java)
            if (parameters != null) {
                return Union2.first(parameters)
            }
            val identifier = this.findChildByClass(JavaScriptIdentifier::class.java)
            if (identifier != null) {
                return Union2.second(identifier)
            }
            return null
        }

    val body: Union2<JavaScriptFunctionBody, JavaScriptExpression>?
        get() {
            val body = this.findChildByClass(JavaScriptFunctionBody::class.java)
            if (body != null) {
                return Union2.first(body)
            }
            val expression = this.findChildByClass(JavaScriptExpression::class.java)
            if (expression != null) {
                return Union2.second(expression)
            }
            return null
        }
}