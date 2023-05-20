package ris58h.webcalm.javascript.psi

import com.intellij.extapi.psi.ASTWrapperPsiElement
import com.intellij.lang.ASTNode
import com.intellij.psi.util.PsiTreeUtil

class JavaScriptObject(node: ASTNode) : ASTWrapperPsiElement(node), JavaScriptAssignable {
    val propertyAssignments: List<JavaScriptPropertyAssignment>
        get() = PsiTreeUtil.getChildrenOfTypeAsList(this, JavaScriptPropertyAssignment::class.java)
}