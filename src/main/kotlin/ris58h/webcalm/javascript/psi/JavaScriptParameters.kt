package ris58h.webcalm.javascript.psi

import com.intellij.extapi.psi.ASTWrapperPsiElement
import com.intellij.lang.ASTNode

class JavaScriptParameters(node: ASTNode) : ASTWrapperPsiElement(node) {
    val parameters: Array<out JavaScriptParameter>
        get() = this.findChildrenByClass(JavaScriptParameter::class.java)
}