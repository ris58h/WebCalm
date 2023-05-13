package ris58h.webcalm.javascript.psi

import com.intellij.extapi.psi.ASTWrapperPsiElement
import com.intellij.lang.ASTNode

class JavaScriptParameter(node: ASTNode) : ASTWrapperPsiElement(node) {
    val assignable: JavaScriptAssignable?
        get() = this.findChildByClass(JavaScriptAssignable::class.java)
}