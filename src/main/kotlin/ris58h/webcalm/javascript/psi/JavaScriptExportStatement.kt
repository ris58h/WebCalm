package ris58h.webcalm.javascript.psi

import com.intellij.extapi.psi.ASTWrapperPsiElement
import com.intellij.lang.ASTNode

class JavaScriptExportStatement(node: ASTNode) : ASTWrapperPsiElement(node), JavaScriptStatement {
    val declaration: JavaScriptDeclaration?
        get() = this.findChildByClass(JavaScriptDeclaration::class.java)
}