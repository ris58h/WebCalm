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
            val parent = identifier.parent
            return if (parent is JavaScriptIdentifierOwner) parent else identifier
        }
        return null
    }

    override fun isAcceptableNamedParent(parent: PsiElement): Boolean {
        // We rely on getNamedElement method.
        return false
    }
}