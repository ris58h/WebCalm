package ris58h.webcalm.css

import com.intellij.lang.refactoring.NamesValidator
import com.intellij.openapi.project.Project
import com.intellij.patterns.ElementPattern
import com.intellij.patterns.PlatformPatterns
import com.intellij.psi.PsiElement
import com.intellij.refactoring.rename.RenameInputValidator
import com.intellij.util.ProcessingContext
import ris58h.webcalm.css.psi.CssVariable

//TODO: better check. See https://drafts.csswg.org/css-variables/#defining-variables
private fun isVariableName(name: String): Boolean {
    return name.startsWith("--") && name.length > 2 && !name.elementAt(2).isDigit()
}

class CssRenameInputValidator : RenameInputValidator {
    override fun getPattern(): ElementPattern<out PsiElement> = PlatformPatterns.psiElement(CssVariable::class.java)

    override fun isInputValid(newName: String, element: PsiElement, context: ProcessingContext): Boolean {
        if (element is CssVariable) {
            return isVariableName(newName)
        }
        return false
    }
}

class CssNamesValidator : NamesValidator {
    override fun isKeyword(name: String, project: Project?): Boolean = false

    override fun isIdentifier(name: String, project: Project?): Boolean = isVariableName(name)
}