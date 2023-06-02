package ris58h.webcalm.javascript.psi

import com.intellij.extapi.psi.ASTWrapperPsiElement
import com.intellij.lang.ASTNode

open class JavaScriptIdentifierOwner(node: ASTNode) : ASTWrapperPsiElement(node) {
    val identifier: JavaScriptIdentifier?
        get() = this.findChildByClass(JavaScriptIdentifier::class.java)
}