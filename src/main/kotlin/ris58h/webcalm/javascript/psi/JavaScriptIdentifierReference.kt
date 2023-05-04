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
        val statements = scope.statements
        //TODO: search for variable should start at some point - not from the last child
        statements.forEachReversed { statement ->
            if (statement is JavaScriptVariableStatement) {
                statement.variableDeclarationList?.variableDeclarations?.forEachReversed { variableDeclaration ->
                    //TODO: support other types of a variable declaration
                    val assignable = variableDeclaration.assignable
                    if (assignable?.name == name) {
                        return assignable
                    }
                }
            }
        }

        return statements.findLast {
            it is JavaScriptFunctionDeclaration && it.name == name
        }
    }
}