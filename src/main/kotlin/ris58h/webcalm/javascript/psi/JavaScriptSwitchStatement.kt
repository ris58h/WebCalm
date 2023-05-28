package ris58h.webcalm.javascript.psi

import com.intellij.extapi.psi.ASTWrapperPsiElement
import com.intellij.lang.ASTNode

class JavaScriptSwitchStatement(node: ASTNode) : ASTWrapperPsiElement(node), JavaScriptStatement {
    val caseBlock: JavaScriptCaseBlock?
        get() = this.findChildByClass(JavaScriptCaseBlock::class.java)
}