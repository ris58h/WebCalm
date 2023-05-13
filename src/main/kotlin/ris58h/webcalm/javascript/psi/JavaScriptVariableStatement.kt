package ris58h.webcalm.javascript.psi

import com.intellij.extapi.psi.ASTWrapperPsiElement
import com.intellij.lang.ASTNode

class JavaScriptVariableStatement(node: ASTNode) : ASTWrapperPsiElement(node), JavaScriptStatement {
    val variableDeclarationList: JavaScriptVariableDeclarationList?
        get() = this.findChildByClass(JavaScriptVariableDeclarationList::class.java)
}