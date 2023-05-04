package ris58h.webcalm.javascript.psi

import com.intellij.psi.PsiElement
import com.intellij.psi.util.PsiTreeUtil

interface JavaScriptStatementsOwner : PsiElement {
    val statements: Array<out JavaScriptStatement>
        get() = PsiTreeUtil.getChildrenOfType(this, JavaScriptStatement::class.java).orEmpty()
}