package ris58h.webcalm.javascript.psi

import com.intellij.extapi.psi.ASTWrapperPsiElement
import com.intellij.lang.ASTNode

class JavaScriptVariableDeclaration(node: ASTNode) : ASTWrapperPsiElement(node), JavaScriptDeclaration {
    val assignable: JavaScriptAssignable?
        get() = findAssignableChild(this)
}