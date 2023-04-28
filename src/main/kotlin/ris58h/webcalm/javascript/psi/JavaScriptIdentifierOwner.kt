package ris58h.webcalm.javascript.psi

import com.intellij.lang.ASTNode
import com.intellij.psi.PsiElement
import com.intellij.psi.util.PsiTreeUtil
import ris58h.webcalm.psi.NameIdentifierSubtree

open class JavaScriptIdentifierOwner(node: ASTNode) : NameIdentifierSubtree(node) {
    override fun getNameIdentifier(): PsiElement? {
        return findJavaScriptIdentifier()
            ?.node
            ?.findChildByType(JavaScriptTypes.IDENTIFIER)
            ?.psi
    }

    open fun findJavaScriptIdentifier(): JavaScriptIdentifier? {
        return PsiTreeUtil.findChildOfType(this, JavaScriptIdentifier::class.java)
    }
}