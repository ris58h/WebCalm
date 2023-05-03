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
        if (current is JavaScriptFunctionBody || current is JavaScriptFile) {
            return findDeclarationInScope(current)
        }
        return null
    }

    private fun findDeclarationInScope(scope: PsiElement): PsiElement? {
        //TODO: variables
        val functionDeclaration = findSiblingBackward(scope.lastChild) {
            it is JavaScriptFunctionDeclaration && it.name == name
        }
        return functionDeclaration
    }

    private fun findSiblingBackward(element: PsiElement, predicate: (PsiElement) -> Boolean): PsiElement? {
        var current = element.prevSibling
        while (current != null) {
            if (predicate(current)) {
                return current
            }
            current = current.prevSibling
        }
        return null
    }
}