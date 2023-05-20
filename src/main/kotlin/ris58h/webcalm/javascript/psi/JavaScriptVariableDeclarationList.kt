package ris58h.webcalm.javascript.psi

import com.intellij.extapi.psi.ASTWrapperPsiElement
import com.intellij.lang.ASTNode
import com.intellij.psi.util.PsiTreeUtil

class JavaScriptVariableDeclarationList(node: ASTNode) : ASTWrapperPsiElement(node) {
    val variableDeclarations: List<JavaScriptVariableDeclaration>
        get() = PsiTreeUtil.getChildrenOfTypeAsList(this, JavaScriptVariableDeclaration::class.java)
}