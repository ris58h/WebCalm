package ris58h.webcalm.javascript.psi

import com.intellij.extapi.psi.ASTWrapperPsiElement
import com.intellij.lang.ASTNode
import com.intellij.psi.util.PsiTreeUtil

class JavaScriptMethod(node: ASTNode) : ASTWrapperPsiElement(node), JavaScriptClassElement {
    val body: JavaScriptFunctionBody?
        get() {
            val methodDefinition = lastChild ?: null
            return PsiTreeUtil.getChildOfType(methodDefinition, JavaScriptFunctionBody::class.java)
        }

    override fun getName(): String? {
        val methodDefinition = lastChild ?: null
        return PsiTreeUtil.getChildOfType(methodDefinition, JavaScriptPropertyName::class.java)?.text
    }
}