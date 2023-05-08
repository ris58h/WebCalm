package ris58h.webcalm.javascript.psi

import com.intellij.lang.ASTNode
import com.intellij.psi.search.LocalSearchScope
import com.intellij.psi.search.SearchScope
import com.intellij.psi.util.PsiTreeUtil

class JavaScriptAssignable(node: ASTNode) : JavaScriptIdentifierOwner(node) {
    override fun getUseScope(): SearchScope {
        if (parent is JavaScriptParameter) {
            val functionBody = PsiTreeUtil.getParentOfType(parent, JavaScriptFunctionDeclaration::class.java)?.body
            if (functionBody != null) {
                return LocalSearchScope(functionBody)
            }
        }
        if (parent is JavaScriptVariableDeclaration) {
            val statementsOwner = PsiTreeUtil.getParentOfType(parent, JavaScriptStatementsOwner::class.java)
            if (statementsOwner != null) {
                return LocalSearchScope(statementsOwner)
            }
        }
        return super.getUseScope()
    }
}