package ris58h.webcalm.javascript.psi

import com.intellij.extapi.psi.ASTWrapperPsiElement
import com.intellij.lang.ASTNode
import com.intellij.openapi.util.TextRange
import com.intellij.psi.PsiElement
import com.intellij.psi.PsiNameIdentifierOwner
import com.intellij.psi.PsiReference
import com.intellij.psi.search.LocalSearchScope
import com.intellij.psi.search.SearchScope
import com.intellij.psi.util.PsiTreeUtil

class JavaScriptIdentifier(node: ASTNode) : ASTWrapperPsiElement(node), PsiNameIdentifierOwner {
    override fun getReference(): PsiReference? {
        if (introducesName()) return null
        val rangeInElement = TextRange(0, name!!.length)
        return when (parent) {
            is JavaScriptBreakStatement, is JavaScriptContinueStatement -> return JavaScriptLabelReference(this, rangeInElement)
            else -> JavaScriptIdentifierReference(this, rangeInElement)
        }
    }

    override fun getName(): String? = nameIdentifier?.text

    override fun setName(name: String): PsiElement {
        val idLeaf = nameIdentifier
        return if (idLeaf != null) {
            val newIdLeaf: PsiElement = JavaScriptElementFactory.createIdentifierTokenFromText(project, name)
            idLeaf.replace(newIdLeaf)
        } else this
    }

    override fun getTextOffset(): Int = nameIdentifier?.textOffset ?: super.getTextOffset()

    override fun getNameIdentifier(): PsiElement? = this.node.findChildByType(JavaScriptTypes.IDENTIFIER)?.psi

    /**
     * Only elements that introduce a name should implement PsiNamedElement interface (https://plugins.jetbrains.com/docs/intellij/references-and-resolve.html#psireference).
     * @see ris58h.webcalm.javascript.psi.JavaScriptIdentifier.getUseScope
     */
    fun introducesName(): Boolean {
        return when (val parent = parent) {
            is JavaScriptIdentifierOwner -> false // The name is introduced by parent element.
            is JavaScriptCatch -> true // TODO: catch may contain destructuring assignment
            is JavaScriptAnonymousFunction -> true
            else -> {
                val parameter = PsiTreeUtil.getParentOfType(parent, JavaScriptParameter::class.java, false)
                if (parameter != null) true
                else variableDeclarationFromLeftHand() != null
            }
        }
    }

    /**
     * @see ris58h.webcalm.javascript.psi.JavaScriptIdentifier.introducesName
     */
    override fun getUseScope(): SearchScope {
        return when (val parent = parent) {
            is JavaScriptIdentifierOwner -> null
            is JavaScriptCatch -> LocalSearchScope(parent)  // TODO: catch may contain destructuring assignment
            is JavaScriptAnonymousFunction -> LocalSearchScope(parent)
            else -> {
                val parameter = PsiTreeUtil.getParentOfType(parent, JavaScriptParameter::class.java, false)
                if (parameter != null) useScopeForParameter(parameter)
                else {
                    val variableDeclaration = variableDeclarationFromLeftHand()
                    val statementsOwner = PsiTreeUtil.getParentOfType(variableDeclaration, JavaScriptStatementsOwner::class.java)
                    if (statementsOwner != null && statementsOwner !is JavaScriptFile) LocalSearchScope(statementsOwner)
                    else null
                }
            }
        } ?: super.getUseScope()
    }

    /**
     * @return a variable declaration if this element is in the left hand of the variable declaration.
     */
    private fun variableDeclarationFromLeftHand(): JavaScriptVariableDeclaration? {
        var prev: PsiElement = this
        var current: PsiElement? = parent
        while (current != null && current !is JavaScriptVariableDeclaration) {
            prev = current
            current = current.parent
        }
        if (current != null && prev.prevSibling == null) {
            return current as JavaScriptVariableDeclaration
        }
        return null
    }

    private fun useScopeForParameter(parameter: JavaScriptParameter): LocalSearchScope? {
        val scopeElement = when (val context = (parameter.parent as? JavaScriptParameters)?.parent) {
            is JavaScriptFunctionDeclaration -> context
            is JavaScriptAnonymousFunction -> context
            is JavaScriptMethod -> context
            else -> null
        }
        return if (scopeElement == null) null else LocalSearchScope(scopeElement)
    }
}