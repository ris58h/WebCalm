package ris58h.webcalm.javascript.psi

import com.intellij.extapi.psi.ASTWrapperPsiElement
import com.intellij.lang.ASTNode
import com.intellij.psi.PsiElement

interface JavaScriptExpression : PsiElement {
    class Other(node: ASTNode) : ASTWrapperPsiElement(node), JavaScriptExpression
}