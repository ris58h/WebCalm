package ris58h.webcalm.javascript.psi

import com.intellij.lang.ASTNode
import com.intellij.psi.PsiElement
import com.intellij.psi.PsiReference
import com.intellij.psi.util.PsiTreeUtil
import com.intellij.psi.util.elementType

class JavaScriptIdentifierExpression(node: ASTNode) : JavaScriptIdentifierOwner(node), JavaScriptExpression {
    override fun getReference(): PsiReference? {
        if (!isValidParentForReference(parent)) return null
        val identifier = identifier ?: return null
        return JavaScriptIdentifierReference(identifier.text, this, identifier.textRangeInParent)
    }

    private fun isValidParentForReference(parent: PsiElement): Boolean {
        // TODO: Could we just check if it's not a propertyAssignment?
        return parent is JavaScriptExpression
                || parent is JavaScriptArgument
                || parent is JavaScriptVariableDeclaration
                || (parent is JavaScriptPropertyAssignment && isValidPropertyAssignmentForReference(parent))
    }

    private fun isValidPropertyAssignmentForReference(parent: JavaScriptPropertyAssignment): Boolean {
        return PsiTreeUtil.prevVisibleLeaf(this).elementType == JavaScriptTypes.COLON
    }
}