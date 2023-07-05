package ris58h.webcalm.css.psi

import com.intellij.extapi.psi.ASTWrapperPsiElement
import com.intellij.lang.ASTNode

class CssTerm(node: ASTNode) : ASTWrapperPsiElement(node) {
    val value: String
        // Term may end with Space token
        get() = this.text.trim()
}