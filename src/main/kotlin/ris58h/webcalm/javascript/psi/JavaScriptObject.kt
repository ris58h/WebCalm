package ris58h.webcalm.javascript.psi

import com.intellij.extapi.psi.ASTWrapperPsiElement
import com.intellij.lang.ASTNode
import com.intellij.psi.util.PsiTreeUtil

class JavaScriptObject(node: ASTNode) : ASTWrapperPsiElement(node), JavaScriptAssignable {
    val propertyAssignments: Array<out JavaScriptPropertyAssignment>
        get() = PsiTreeUtil.getChildrenOfType(this, JavaScriptPropertyAssignment::class.java).orEmpty()
}