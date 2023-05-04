package ris58h.webcalm.javascript.psi

import com.intellij.extapi.psi.ASTWrapperPsiElement
import com.intellij.lang.ASTNode
import com.intellij.psi.util.PsiTreeUtil

class JavaScriptVariableDeclaration(node: ASTNode) : ASTWrapperPsiElement(node) {
    val assignable: JavaScriptAssignable?
        get() = PsiTreeUtil.findChildOfType(this, JavaScriptAssignable::class.java)
}