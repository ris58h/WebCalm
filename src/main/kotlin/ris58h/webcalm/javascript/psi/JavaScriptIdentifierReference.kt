package ris58h.webcalm.javascript.psi

import com.intellij.openapi.util.TextRange
import com.intellij.psi.*
import com.intellij.util.SmartList
import com.intellij.util.containers.SmartHashSet
import ris58h.webcalm.javascript.RelatedScriptsProcessor

class JavaScriptIdentifierReference(private val name: String, element: PsiElement, rangeInElement: TextRange) :
    PsiPolyVariantReferenceBase<PsiElement>(element, rangeInElement) {

    override fun multiResolve(incompleteCode: Boolean): Array<ResolveResult> {
        val declarations = SmartList<PsiElement>()
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
                val variableDeclarationList = current.variableDeclarationList
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
                is JavaScriptFunctionDeclaration -> callback(it)
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
        when (assignable) {
            is JavaScriptIdentifier -> callback(assignable)
            is JavaScriptArray -> {
                assignable.elements.forEach {
                    if (it is JavaScriptIdentifierExpression) callback(it)
                }
            }
            is JavaScriptObject -> {
                assignable.propertyAssignments.forEach {
                    val propertyShorthand = it.propertyShorthand
                    if (propertyShorthand != null) callback(propertyShorthand)
                }
            }
        }
    }
}