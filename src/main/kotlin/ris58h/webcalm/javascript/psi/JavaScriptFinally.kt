package ris58h.webcalm.javascript.psi

import com.intellij.extapi.psi.ASTWrapperPsiElement
import com.intellij.lang.ASTNode

class JavaScriptFinally(node: ASTNode) : ASTWrapperPsiElement(node) {
    val block: JavaScriptBlock?
        get() = this.findChildByClass(JavaScriptBlock::class.java)
}