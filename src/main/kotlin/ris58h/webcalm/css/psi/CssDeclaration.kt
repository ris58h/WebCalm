package ris58h.webcalm.css.psi

import com.intellij.extapi.psi.ASTWrapperPsiElement
import com.intellij.lang.ASTNode

class CssDeclaration(node: ASTNode) : ASTWrapperPsiElement(node) {
    val property: CssProperty?
        get() = this.findChildByClass(CssProperty::class.java)
}