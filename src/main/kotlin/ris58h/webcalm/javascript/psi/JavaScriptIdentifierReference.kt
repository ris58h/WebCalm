package ris58h.webcalm.javascript.psi

import com.intellij.openapi.util.TextRange
import com.intellij.psi.*
import com.intellij.psi.util.PsiTreeUtil
import com.jetbrains.rd.util.forEachReversed

class JavaScriptIdentifierReference(private val name: String, element: PsiElement, rangeInElement: TextRange) :
    PsiReferenceBase<PsiElement>(element, rangeInElement) {
    override fun resolve(): PsiElement? {
        //TODO: functions from other files
        return findDeclaration()
    }

    override fun handleElementRename(newElementName: String): PsiElement {
        val element = myElement
        if (element is JavaScriptIdentifierExpression) {
            return element.setName(newElementName)
        }
        return super.handleElementRename(newElementName)
    }

    private fun findDeclaration(): PsiElement? {
        var prev = myElement
        var current = myElement.parent
        while (current != null && prev !is JavaScriptFile) {
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

        val functionDeclarations = statements.map {
            when (it) {
                is JavaScriptFunctionDeclaration -> it
                is JavaScriptExportStatement -> {
                    // TODO: a method in JavaScriptExportStatement to obtain underlying declaration
                    PsiTreeUtil.findChildOfType(it, JavaScriptFunctionDeclaration::class.java)
                }

                else -> null
            }
        }
        return functionDeclarations.findLast { it?.name == name }
    }
}