package ris58h.webcalm.javascript.psi

import com.intellij.extapi.psi.ASTWrapperPsiElement
import com.intellij.lang.ASTNode

class JavaScriptBreakStatement(node: ASTNode) : ASTWrapperPsiElement(node), JavaScriptStatement {
    val identifier: JavaScriptIdentifier?
        get() = this.findChildByClass(JavaScriptIdentifier::class.java)
}