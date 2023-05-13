package ris58h.webcalm.javascript.psi

import com.intellij.lang.ASTNode
import com.intellij.psi.PsiElement
import com.intellij.psi.PsiReference

class JavaScriptIdentifierExpression(node: ASTNode) : JavaScriptIdentifierOwner(node) {
    override fun getReference(): PsiReference? {
        if (!isValidParent(parent)) return null
        val nameIdentifier = this.findChildByClass(JavaScriptIdentifier::class.java) ?: return null
        return JavaScriptIdentifierReference(nameIdentifier.text, this, nameIdentifier.textRangeInParent)
    }

    private fun isValidParent(parent: PsiElement): Boolean {
        // TODO: Could we just check if it's not a propertyAssignment?
        return parent is JavaScriptExpression
                || parent is JavaScriptParameter
                || parent is JavaScriptVariableDeclaration
    }
}