package ris58h.webcalm.javascript.psi

import com.intellij.extapi.psi.ASTWrapperPsiElement
import com.intellij.lang.ASTNode
import com.intellij.psi.util.PsiTreeUtil

class JavaScriptArray(node: ASTNode) : ASTWrapperPsiElement(node), JavaScriptAssignable {
    val elements: Array<out JavaScriptExpression>
        get() = PsiTreeUtil.getChildrenOfType(this, JavaScriptExpression::class.java).orEmpty()
}