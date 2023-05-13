package ris58h.webcalm.javascript.psi

import com.intellij.extapi.psi.ASTWrapperPsiElement
import com.intellij.lang.ASTNode

class JavaScriptVariableDeclarationList(node: ASTNode) : ASTWrapperPsiElement(node) {
    val variableDeclarations: Array<out JavaScriptVariableDeclaration>
        get() = this.findChildrenByClass(JavaScriptVariableDeclaration::class.java)
}