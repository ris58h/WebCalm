package ris58h.webcalm.javascript.psi

import com.intellij.openapi.util.TextRange
import com.intellij.psi.*

class JavaScriptIdentifierReference(private val name: String, element: PsiElement, rangeInElement: TextRange) : PsiReferenceBase<PsiElement>(element, rangeInElement) {
    override fun resolve(): PsiElement? {
        //TODO: functions from other files
        return findDeclaration()
    }

    private fun findDeclaration(): PsiElement? {
        var current = element.parent
        while (current != null) {
            val declaration = findDeclarationIter(current)
            if (declaration != null) {
                return declaration
            }
            current = current.parent
        }
        return null
    }

    private fun findDeclarationIter(current: PsiElement): PsiElement? {
        if (current is JavaScriptStatementList) {
            return findDeclarationInScope(current)
        }
        return null
    }

    private fun findDeclarationInScope(scope: JavaScriptStatementList): PsiElement? {
        //TODO: variables
        val functionDeclaration = scope.statements.findLast {
            it is JavaScriptFunctionDeclaration && it.name == name
        }
        return functionDeclaration
    }
}