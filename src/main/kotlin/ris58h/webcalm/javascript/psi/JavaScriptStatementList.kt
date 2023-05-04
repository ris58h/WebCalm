package ris58h.webcalm.javascript.psi

import com.intellij.psi.PsiElement
import com.intellij.psi.util.PsiTreeUtil

interface JavaScriptStatementList : PsiElement {
    val statements: Array<out JavaScriptStatement>
        get() = PsiTreeUtil.getChildrenOfType(this, JavaScriptStatement::class.java).orEmpty()
}