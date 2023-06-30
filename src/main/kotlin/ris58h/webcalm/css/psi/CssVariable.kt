package ris58h.webcalm.css.psi

import com.intellij.openapi.util.TextRange
import com.intellij.psi.PsiElement
import com.intellij.psi.PsiNameIdentifierOwner
import com.intellij.psi.PsiNamedElement
import com.intellij.psi.PsiReference
import com.intellij.psi.impl.source.tree.LeafPsiElement
import com.intellij.psi.search.LocalSearchScope
import com.intellij.psi.search.SearchScope

class CssVariable(text: CharSequence) : LeafPsiElement(CssTypes.VARIABLE, text), PsiNamedElement, PsiNameIdentifierOwner {
    val isDeclaration: Boolean
        get() = parent is CssProperty

    override fun getReference(): PsiReference? {
        return if (isDeclaration) null else {
            val text = text
            CssVariableReference(text, this, TextRange.from(0, text.length))
        }
    }

    override fun getName(): String = text

    override fun setName(name: String): PsiElement {
        val newVariable: PsiElement = CssElementFactory.createVariableFromText(project, name)
        return replace(newVariable)
    }

    override fun toString(): String = "CSS_VARIABLE"

    override fun getUseScope(): SearchScope {
        return LocalSearchScope(containingFile)
    }

    // TODO a hack to make InplaceRefactoring.getSelectedInEditorElement work.
    override fun getNameIdentifier(): PsiElement = this
}