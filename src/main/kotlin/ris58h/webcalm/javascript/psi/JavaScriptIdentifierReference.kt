package ris58h.webcalm.javascript.psi

import com.intellij.openapi.util.TextRange
import com.intellij.psi.*
import com.jetbrains.rd.util.forEachReversed

class JavaScriptIdentifierReference(private val name: String, element: PsiElement, rangeInElement: TextRange) : PsiReferenceBase<PsiElement>(element, rangeInElement) {
    override fun resolve(): PsiElement? {
        //TODO: functions from other files
        return findDeclaration()
    }

    private fun findDeclaration(): PsiElement? {
        var prev = element
        var current = element.parent
        while (current != null) {
            val declaration = findDeclarationIteration(current, prev)
            if (declaration != null) return declaration
            prev = current
            current = current.parent
        }
        return null
    }

    private fun findDeclarationIteration(current: PsiElement, prev: PsiElement): PsiElement? {
        if (current is JavaScriptStatementsOwner && prev is JavaScriptStatement) {
            val declaration = findDeclarationInScope(current, prev)
            if (declaration != null) return declaration
        }
        if (current is JavaScriptFunctionDeclaration) {
            val declaration = findDeclarationInParameters(current)
            if (declaration != null) return declaration
        }
        return null
    }

    private fun findDeclarationInParameters(functionDeclaration: JavaScriptFunctionDeclaration): PsiElement? {
        functionDeclaration.parameters?.parameters?.forEach { parameter ->
            //TODO: support other parameter types
            val assignable = parameter.assignable
            if (assignable?.name == name) return assignable
        }
        return null
    }

    private fun findDeclarationInScope(scope: JavaScriptStatementsOwner, visited: JavaScriptStatement): PsiElement? {
        val statements = scope.statements

        val visitedIndex = statements.indexOfLast { it === visited }
        for (i in visitedIndex - 1 downTo 0) {
            val statement = statements[i]
            if (statement is JavaScriptVariableStatement) {
                statement.variableDeclarationList?.variableDeclarations?.forEachReversed { variableDeclaration ->
                    //TODO: support other variable declaration types
                    val assignable = variableDeclaration.assignable
                    if (assignable?.name == name) return assignable
                }
            }
        }

        return statements.findLast {
            it is JavaScriptFunctionDeclaration && it.name == name
        }
    }
}