package ris58h.webcalm.css

import com.intellij.patterns.ElementPattern
import com.intellij.patterns.PlatformPatterns
import com.intellij.psi.PsiElement
import com.intellij.refactoring.rename.RenameInputValidator
import com.intellij.util.ProcessingContext
import ris58h.webcalm.css.psi.CssVariable

class CssNameValidator : RenameInputValidator {
    override fun getPattern(): ElementPattern<out PsiElement> = PlatformPatterns.psiElement(CssVariable::class.java)

    override fun isInputValid(newName: String, element: PsiElement, context: ProcessingContext): Boolean {
        if (element is CssVariable) {
            //TODO: check the whole name not only the prefix
            return newName.startsWith("--")
        }
        return false
    }
}