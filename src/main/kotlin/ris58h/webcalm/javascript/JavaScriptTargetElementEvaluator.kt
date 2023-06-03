package ris58h.webcalm.javascript

import com.intellij.codeInsight.TargetElementEvaluatorEx2
import com.intellij.psi.PsiElement
import com.intellij.psi.util.PsiTreeUtil
import ris58h.webcalm.javascript.psi.JavaScriptIdentifier
import ris58h.webcalm.javascript.psi.JavaScriptNamedIdentifierOwner

class JavaScriptTargetElementEvaluator : TargetElementEvaluatorEx2() {
    override fun getNamedElement(element: PsiElement): PsiElement? {
        val identifier = PsiTreeUtil.getParentOfType(element, JavaScriptIdentifier::class.java)
        if (identifier != null) {
            if (identifier.introducesName()) return identifier
            val parent = identifier.parent
            if (parent is JavaScriptNamedIdentifierOwner) return parent
        }
        return null
    }

    override fun isAcceptableNamedParent(parent: PsiElement): Boolean {
        // We rely on getNamedElement method.
        return false
    }
}