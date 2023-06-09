package ris58h.webcalm.css

import com.intellij.codeInsight.TargetElementEvaluatorEx2
import com.intellij.psi.PsiElement
import ris58h.webcalm.css.psi.CssVariable

class CssTargetElementEvaluator : TargetElementEvaluatorEx2() {
    override fun getNamedElement(element: PsiElement): PsiElement? {
        return if (element is CssVariable && element.isDeclaration) element else null
    }

    override fun isAcceptableNamedParent(parent: PsiElement): Boolean {
        // We rely on getNamedElement method.
        return false
    }
}