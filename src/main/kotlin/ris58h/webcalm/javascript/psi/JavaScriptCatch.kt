package ris58h.webcalm.javascript.psi

import com.intellij.extapi.psi.ASTWrapperPsiElement
import com.intellij.lang.ASTNode

class JavaScriptCatch(node: ASTNode) : ASTWrapperPsiElement(node) {
    val assignable: JavaScriptAssignable?
        get() = findAssignableChild(this)

    val block: JavaScriptBlock?
        get() = this.findChildByClass(JavaScriptBlock::class.java)
}