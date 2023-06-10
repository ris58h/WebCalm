package ris58h.webcalm.javascript

import com.intellij.lang.refactoring.RefactoringSupportProvider
import com.intellij.psi.PsiElement
import ris58h.webcalm.javascript.psi.JavaScriptIdentifier
import ris58h.webcalm.javascript.psi.JavaScriptIdentifierOwner

class JavaScriptRefactoringSupportProvider : RefactoringSupportProvider() {
    override fun isInplaceRenameAvailable(element: PsiElement, context: PsiElement?): Boolean {
        return (element is JavaScriptIdentifier && element.introducesName())
                || element is JavaScriptIdentifierOwner
    }
}