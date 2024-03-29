package ris58h.webcalm.javascript.psi

import com.intellij.openapi.util.TextRange
import com.intellij.psi.*
import com.intellij.util.SmartList
import com.intellij.util.containers.SmartHashSet
import ris58h.webcalm.javascript.RelatedScriptsProcessor

class JavaScriptIdentifierReference(element: JavaScriptIdentifier, rangeInElement: TextRange) :
    PsiPolyVariantReferenceBase<JavaScriptIdentifier>(element, rangeInElement) {

    override fun multiResolve(incompleteCode: Boolean): Array<ResolveResult> {
        val declarations = SmartList<PsiElement>()
        val name = myElement.name
        processDeclarations {
            if (it.name == name) declarations.add(it)
        }
        return PsiElementResolveResult.createResults(declarations)
    }

    override fun getVariants(): Array<Any> {
        val variants = SmartHashSet<String>()
        processDeclarations {
            val name = it.name
            if (name != null) variants.add(name)
        }
        return variants.toTypedArray()
    }

    override fun handleElementRename(newElementName: String): PsiElement {
        return myElement.setName(newElementName)
    }

    private fun processDeclarations(callback: (PsiNamedElement) -> Unit) {
        var prev: PsiElement = myElement
        var current = myElement.parent
        while (current != null && prev !is JavaScriptFile) {
            processDeclarationsIteration(current, callback)
            prev = current
            current = current.parent
        }

        if (prev is JavaScriptFile) {
            RelatedScriptsProcessor.EP_NAME.extensionList.forEach {
                it.processRelatedScripts(prev) { relatedScript ->
                    processDeclarationsInScope(relatedScript, callback)
                }
            }
        }
    }

    private fun processDeclarationsIteration(current: PsiElement, callback: (PsiNamedElement) -> Unit) {
        when (current) {
            is JavaScriptStatementsOwner -> processDeclarationsInScope(current, callback)
            is JavaScriptFunctionDeclaration -> processDeclarationsInParameters(current, callback)
            is JavaScriptAnonymousFunction -> processDeclarationsInParameters(current, callback)
            is JavaScriptMethod -> processDeclarationsInParameters(current, callback)
            is JavaScriptCatch -> {
                val assignable = current.assignable
                if (assignable != null) processDeclarationsInAssignable(assignable, callback)
            }
            is JavaScriptIterationStatement -> {
                val variableDeclarationList = when (current) {
                    is JavaScriptDoWhileStatement -> null
                    is JavaScriptWhileStatement -> null
                    is JavaScriptForStatement -> current.variableDeclarationList
                    is JavaScriptForInStatement -> current.variableDeclarationList
                    is JavaScriptForOfStatement -> current.variableDeclarationList
                }
                if (variableDeclarationList != null) processDeclarationsInVariableDeclarationList(variableDeclarationList, callback)
            }
        }
    }

    private fun processDeclarationsInParameters(functionDeclaration: JavaScriptFunctionDeclaration, callback: (PsiNamedElement) -> Unit) {
        val parameters = functionDeclaration.parameters
        if (parameters != null) processDeclarationsInParameters(parameters, callback)
    }

    private fun processDeclarationsInParameters(anonymousFunction: JavaScriptAnonymousFunction, callback: (PsiNamedElement) -> Unit) {
        anonymousFunction.parameters?.doWhen(
            { processDeclarationsInParameters(it, callback) },
            { callback(it) }
        )
    }

    private fun processDeclarationsInParameters(method: JavaScriptMethod, callback: (PsiNamedElement) -> Unit) {
        val parameters = method.parameters
        if (parameters != null) processDeclarationsInParameters(parameters, callback)
    }

    private fun processDeclarationsInParameters(parameters: JavaScriptParameters, callback: (PsiNamedElement) -> Unit) {
        parameters.parameters.forEach { parameter ->
            when (parameter) {
                is JavaScriptFormalParameter -> {
                    val assignable = parameter.assignable
                    if (assignable != null) processDeclarationsInAssignable(assignable, callback)
                }
                is JavaScriptFormalRestParameter -> {
                    val identifier = parameter.identifierExpression?.identifier
                    if (identifier != null) callback(identifier)
                }
            }
        }
    }

    private fun processDeclarationsInScope(scope: JavaScriptStatementsOwner, callback: (PsiNamedElement) -> Unit) {
        scope.statements.forEach {
            when (it) {
                is JavaScriptVariableStatement -> {
                    val variableDeclarationList = it.variableDeclarationList
                    if (variableDeclarationList != null) processDeclarationsInVariableDeclarationList(variableDeclarationList, callback)
                }
                is JavaScriptFunctionDeclaration, is JavaScriptClassDeclaration -> {
                    val identifierOwner = it as JavaScriptIdentifierOwner
                    if (identifierOwner.identifier != myElement) callback(identifierOwner)
                }
                is JavaScriptExportStatement -> {
                    when (val declaration = it.declaration) {
                        is JavaScriptFunctionDeclaration -> callback(declaration)
                        is JavaScriptVariableDeclaration -> {
                            val assignable = declaration.assignable
                            if (assignable != null) processDeclarationsInAssignable(assignable, callback)
                        }
                        else -> Unit
                    }
                }
                else -> Unit
            }
        }
    }

    private fun processDeclarationsInVariableDeclarationList(variableDeclarationList: JavaScriptVariableDeclarationList, callback: (PsiNamedElement) -> Unit) {
        variableDeclarationList.variableDeclarations.forEach { variableDeclaration ->
            val assignable = variableDeclaration.assignable
            if (assignable != null) processDeclarationsInAssignable(assignable, callback)
        }
    }

    private fun processDeclarationsInAssignable(assignable: JavaScriptAssignable, callback: (PsiNamedElement) -> Unit) {
        assignable.doWhen(
            { identifier -> callback(identifier) },
            { array ->
                array.elements.forEach {
                    processDeclarationsInExpression(it, callback)
                }
            },
            { obj ->
                obj.propertyAssignments.forEach {
                    when (it) {
                        is JavaScriptPropertyShorthand -> {
                            val expression = it.expression
                            if (expression != null) processDeclarationsInExpression(expression, callback)
                        }
                        else -> {
                            // TODO: other property assignments
                        }
                    }
                }
            }
        )
    }

    private fun processDeclarationsInExpression(expression: JavaScriptExpression, callback: (PsiNamedElement) -> Unit) {
        when (expression) {
            is JavaScriptAssignmentExpression -> {
                val left = expression.left
                if (left != null) processDeclarationsInExpression(left, callback)
            }
            is JavaScriptIdentifierExpression -> callback(expression.identifier)
            else -> {
                // TODO: other expressions
            }
        }
    }
}