package ris58h.webcalm.javascript.psi

import com.intellij.extapi.psi.ASTWrapperPsiElement
import com.intellij.lang.ASTNode
import com.intellij.psi.util.PsiTreeUtil

class JavaScriptVariableStatement(node: ASTNode) : ASTWrapperPsiElement(node), JavaScriptStatement {
    val variableDeclarationList: JavaScriptVariableDeclarationList?
        get() = PsiTreeUtil.findChildOfType(this, JavaScriptVariableDeclarationList::class.java)
}