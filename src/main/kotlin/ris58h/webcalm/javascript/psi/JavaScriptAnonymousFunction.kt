package ris58h.webcalm.javascript.psi

import com.intellij.extapi.psi.ASTWrapperPsiElement
import com.intellij.lang.ASTNode
import ris58h.webcalm.psi.findChildUnionByClass
import ris58h.webcalm.util.Union2

class JavaScriptAnonymousFunction(node: ASTNode) : ASTWrapperPsiElement(node) {
    val parameters: Union2<JavaScriptParameters, JavaScriptIdentifier>?
        get() = this.findChildUnionByClass(
            JavaScriptParameters::class.java,
            JavaScriptIdentifier::class.java
        )

    val body: Union2<JavaScriptFunctionBody, JavaScriptExpression>?
        get() = this.findChildUnionByClass(
            JavaScriptFunctionBody::class.java,
            JavaScriptExpression::class.java
        )
}