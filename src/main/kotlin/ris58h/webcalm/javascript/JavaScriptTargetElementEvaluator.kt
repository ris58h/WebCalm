package ris58h.webcalm.javascript

import com.intellij.codeInsight.TargetElementEvaluatorEx2
import com.intellij.psi.PsiElement
import com.intellij.psi.util.PsiTreeUtil
import ris58h.webcalm.javascript.psi.JavaScriptIdentifier
import ris58h.webcalm.javascript.psi.JavaScriptIdentifierOwner

class JavaScriptTargetElementEvaluator : TargetElementEvaluatorEx2() {
    override fun getNamedElement(element: PsiElement): PsiElement? {
        val identifier = PsiTreeUtil.getParentOfType(element, JavaScriptIdentifier::class.java)
        if (identifier != null) {
            if (identifier.introducesName()) return identifier
            val parent = identifier.parent
            if (parent is JavaScriptIdentifierOwner) return parent
        }
        return null
    }

    // We rely on getNamedElement method.
    override fun isAcceptableNamedParent(parent: PsiElement) = false
}