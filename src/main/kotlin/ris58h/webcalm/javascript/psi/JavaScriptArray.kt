package ris58h.webcalm.javascript.psi

import com.intellij.extapi.psi.ASTWrapperPsiElement
import com.intellij.lang.ASTNode
import com.intellij.psi.util.PsiTreeUtil

class JavaScriptArray(node: ASTNode) : ASTWrapperPsiElement(node) {
    val elements: List<JavaScriptExpression>
        get() = PsiTreeUtil.getChildrenOfTypeAsList(this, JavaScriptExpression::class.java)
}