package ris58h.webcalm.javascript.psi

import com.intellij.extapi.psi.ASTWrapperPsiElement
import com.intellij.lang.ASTNode

class JavaScriptForStatement(node: ASTNode) : ASTWrapperPsiElement(node), JavaScriptIterationStatement {
    val variableDeclarationList: JavaScriptVariableDeclarationList?
        get() = this.findChildByClass(JavaScriptVariableDeclarationList::class.java)
}