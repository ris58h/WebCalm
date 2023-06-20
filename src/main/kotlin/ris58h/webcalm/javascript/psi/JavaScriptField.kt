package ris58h.webcalm.javascript.psi

import com.intellij.extapi.psi.ASTWrapperPsiElement
import com.intellij.lang.ASTNode
import com.intellij.psi.util.PsiTreeUtil

class JavaScriptField(node: ASTNode) : ASTWrapperPsiElement(node), JavaScriptClassElement {
    override fun getName(): String? {
        val fieldDefinition = lastChild
        return PsiTreeUtil.getChildOfType(fieldDefinition, JavaScriptClassElementName::class.java)?.text
    }
}