package ris58h.webcalm.javascript.psi

import com.intellij.extapi.psi.ASTWrapperPsiElement
import com.intellij.lang.ASTNode

class JavaScriptFormalParameter(node: ASTNode) : ASTWrapperPsiElement(node), JavaScriptParameter {
    val assignable: JavaScriptAssignable?
        get() = this.findChildByClass(JavaScriptAssignable::class.java)
}