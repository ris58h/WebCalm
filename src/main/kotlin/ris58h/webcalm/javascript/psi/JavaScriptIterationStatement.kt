package ris58h.webcalm.javascript.psi

import com.intellij.extapi.psi.ASTWrapperPsiElement
import com.intellij.lang.ASTNode

// TODO: different classes for different iteration types
class JavaScriptIterationStatement(node: ASTNode) : ASTWrapperPsiElement(node), JavaScriptStatement {
    val variableDeclarationList: JavaScriptVariableDeclarationList?
        get() = this.findChildByClass(JavaScriptVariableDeclarationList::class.java)
}