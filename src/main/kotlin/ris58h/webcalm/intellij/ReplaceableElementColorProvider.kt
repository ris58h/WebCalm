package ris58h.webcalm.intellij

import com.intellij.openapi.editor.ElementColorProvider
import com.intellij.openapi.util.Key
import com.intellij.psi.PsiElement
import java.awt.Color

// See https://youtrack.jetbrains.com/issue/IDEA-294585
interface ReplaceableElementColorProvider : ElementColorProvider {
    override fun setColorTo(element: PsiElement, color: Color) {
        val actualElement = getActualElement(element)
        val newActualElement = replaceColorTo(actualElement, color) ?: return
        setActualElement(element, newActualElement)
    }

    fun replaceColorTo(element: PsiElement, color: Color): PsiElement?
}

private val ACTUAL_ELEMENT = Key.create<PsiElement>("ReplaceableElementColorProvider.actualElement")

private fun getActualElement(element: PsiElement): PsiElement {
    return element.getUserData(ACTUAL_ELEMENT) ?: element
}

private fun setActualElement(element: PsiElement, actualElement: PsiElement) {
    element.putUserData(ACTUAL_ELEMENT, actualElement)
}
