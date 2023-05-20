package ris58h.webcalm.javascript.psi

import com.intellij.psi.PsiElement
import com.intellij.psi.util.PsiTreeUtil

interface JavaScriptStatementsOwner : PsiElement {
    val statements: List<JavaScriptStatement>
        get() = PsiTreeUtil.getChildrenOfTypeAsList(this, JavaScriptStatement::class.java)
}