package ris58h.webcalm.javascript.psi

import com.intellij.extapi.psi.ASTWrapperPsiElement
import com.intellij.lang.ASTNode
import com.intellij.psi.PsiElement

sealed interface JavaScriptClassElement : PsiElement {
    // TODO: a hack to skip unsupported classElements.
    class NotClassElement(node: ASTNode) : ASTWrapperPsiElement(node)
}