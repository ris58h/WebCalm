package ris58h.webcalm.javascript.psi

import com.intellij.openapi.util.TextRange
import com.intellij.psi.*
import com.intellij.util.SmartList
import com.jetbrains.rd.util.forEachReversed

class JavaScriptIdentifierReference(private val name: String, element: PsiElement, rangeInElement: TextRange) :
    PsiPolyVariantReferenceBase<PsiElement>(element, rangeInElement) {

    override fun multiResolve(incompleteCode: Boolean): Array<ResolveResult> {
        val declarations = SmartList<PsiElement>()
        processDeclarations {
            if (it.name == name) declarations.add(it)
        }
        return PsiElementResolveResult.createResults(declarations)
    }

    override fun handleElementRename(newElementName: String): PsiElement {
        val element = myElement
        if (element is JavaScriptIdentifierExpression) {
            return element.setName(newElementName)
        }
        return super.handleElementRename(newElementName)
    }

    private fun processDeclarations(callback: (PsiNamedElement) -> Unit) {
        var prev = myElement
        var current = myElement.parent
        while (current != null && prev !is JavaScriptFile) {
            processDeclarationsIteration(current, prev, callback)
            prev = current
            current = current.parent
        }
    }

    private fun processDeclarationsIteration(current: PsiElement, prev: PsiElement, callback: (PsiNamedElement) -> Unit) {
        if (current is JavaScriptStatementsOwner && prev is JavaScriptStatement) {
            processDeclarationsInScope(current, prev, callback)
        }
        if (current is JavaScriptFunctionDeclaration) {
            processDeclarationsInParameters(current, callback)
        }
    }

    private fun processDeclarationsInParameters(functionDeclaration: JavaScriptFunctionDeclaration, callback: (PsiNamedElement) -> Unit) {
        functionDeclaration.parameters?.parameters?.forEach { parameter ->
            //TODO: support other parameter types
            val assignable = parameter.assignable
            if (assignable != null) callback(assignable)
        }
    }

    private fun processDeclarationsInScope(scope: JavaScriptStatementsOwner, visited: JavaScriptStatement, callback: (PsiNamedElement) -> Unit) {
        val statements = scope.statements

        val visitedIndex = statements.indexOfLast { it === visited }
        for (i in visitedIndex - 1 downTo 0) {
            val statement = statements[i]
            if (statement is JavaScriptVariableStatement) {
                statement.variableDeclarationList?.variableDeclarations?.forEachReversed { variableDeclaration ->
                    //TODO: support other variable declaration types
                    val assignable = variableDeclaration.assignable
                    if (assignable != null) callback(assignable)
                }
            }
        }

        statements.forEach {
            val functionDeclaration = when (it) {
                is JavaScriptFunctionDeclaration -> it
                is JavaScriptExportStatement -> it.declaration
                else -> null
            }
            if (functionDeclaration != null) callback(functionDeclaration)
        }
    }
}