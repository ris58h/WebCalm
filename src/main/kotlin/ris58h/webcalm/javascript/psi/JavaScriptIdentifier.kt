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
                val functionBody = PsiTreeUtil.getParentOfType(parent, JavaScriptFunctionDeclaration::class.java)?.body
                if (functionBody != null) {
                    return LocalSearchScope(functionBody)
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