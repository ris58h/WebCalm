package ris58h.webcalm.javascript.psi

import com.intellij.extapi.psi.ASTWrapperPsiElement
import com.intellij.lang.ASTNode
import com.intellij.psi.util.PsiTreeUtil

class JavaScriptParameters(node: ASTNode) : ASTWrapperPsiElement(node) {
    val parameters: List<JavaScriptParameter>
        get() = PsiTreeUtil.getChildrenOfTypeAsList(this, JavaScriptParameter::class.java)
}