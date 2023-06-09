package ris58h.webcalm.css.psi

import com.intellij.openapi.util.TextRange
import com.intellij.psi.PsiElement
import com.intellij.psi.PsiNamedElement
import com.intellij.psi.PsiReference
import com.intellij.psi.impl.source.tree.LeafPsiElement

class CssVariable(text: CharSequence) : LeafPsiElement(CssTypes.VARIABLE, text), PsiNamedElement {
    val isDeclaration: Boolean
        get() = parent is CssProperty

    override fun getReference(): PsiReference? {
        return if (isDeclaration) null else {
            val text = text
            CssVariableReference(text, this, TextRange.from(0, text.length))
        }
    }

    override fun getName(): String {
        return text
    }

    override fun setName(name: String): PsiElement {
        TODO("Not yet implemented")
    }

    override fun toString(): String {
        return "CSS_VARIABLE"
    }
}