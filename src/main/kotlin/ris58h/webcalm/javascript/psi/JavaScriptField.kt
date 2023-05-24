package ris58h.webcalm.javascript.psi

import com.intellij.extapi.psi.ASTWrapperPsiElement
import com.intellij.lang.ASTNode

class JavaScriptField(node: ASTNode) : ASTWrapperPsiElement(node), JavaScriptClassElement {
    override fun getName(): String? {
        return this.findChildByClass(JavaScriptPropertyName::class.java)?.text
    }
}