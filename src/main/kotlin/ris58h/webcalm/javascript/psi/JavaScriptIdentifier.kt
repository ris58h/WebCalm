package ris58h.webcalm.javascript.psi

import com.intellij.extapi.psi.ASTWrapperPsiElement
import com.intellij.lang.ASTNode
import com.intellij.psi.PsiElement
import com.intellij.psi.PsiNameIdentifierOwner
import com.intellij.psi.search.LocalSearchScope
import com.intellij.psi.search.SearchScope
import com.intellij.psi.util.PsiTreeUtil

class JavaScriptIdentifier(node: ASTNode) : ASTWrapperPsiElement(node), JavaScriptAssignable, PsiNameIdentifierOwner {
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

    override fun getUseScope(): SearchScope {
        return when (val parent = parent) {
            is JavaScriptFormalParameter -> searchScopeForParameter(parent)
            is JavaScriptIdentifierExpression -> {
                val parent2 = parent.parent
                if (parent2 is JavaScriptFormalRestParameter) searchScopeForParameter(parent2)
                else null
            }
            is JavaScriptVariableDeclaration, is JavaScriptFunctionDeclaration -> {
                val statementsOwner = PsiTreeUtil.getParentOfType(parent, JavaScriptStatementsOwner::class.java)
                if (statementsOwner != null && statementsOwner !is JavaScriptFile) LocalSearchScope(statementsOwner)
                else null
            }
            else -> null
        } ?: super.getUseScope()
    }

    private fun searchScopeForParameter(parameter: JavaScriptParameter): LocalSearchScope? {
        val scopeElement = when (val context = (parameter.parent as? JavaScriptParameters)?.parent) {
            is JavaScriptFunctionDeclaration -> context.body
            is JavaScriptAnonymousFunction -> context.body?.doWhen({ it }, { it })
            is JavaScriptMethod -> context.body
            else -> null
        }
        return if (scopeElement == null) null else LocalSearchScope(scopeElement)
    }
}