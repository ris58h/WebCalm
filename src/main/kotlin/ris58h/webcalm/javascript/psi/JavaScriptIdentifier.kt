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
        when (parent) {
            is JavaScriptParameter -> {
                val parent2 = parent.parent
                if (parent2 is JavaScriptParameters) {
                    val scope = when (val parent3 = parent2.parent) {
                        is JavaScriptFunctionDeclaration -> parent3.body
                        is JavaScriptAnonymousFunction -> parent3.body?.doWhen({ it }, { it })
                        is JavaScriptMethod -> parent3.body
                        else -> null
                    }
                    if (scope != null) {
                        return LocalSearchScope(scope)
                    }
                }
            }
            is JavaScriptVariableDeclaration, is JavaScriptFunctionDeclaration -> {
                val statementsOwner = PsiTreeUtil.getParentOfType(parent, JavaScriptStatementsOwner::class.java)
                if (statementsOwner != null && statementsOwner !is JavaScriptFile) {
                    return LocalSearchScope(statementsOwner)
                }
            }
        }
        return super.getUseScope()
    }
}