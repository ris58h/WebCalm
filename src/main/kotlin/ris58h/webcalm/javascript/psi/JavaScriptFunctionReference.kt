package ris58h.webcalm.javascript.psi

import com.intellij.openapi.util.TextRange
import com.intellij.psi.PsiElement
import com.intellij.psi.PsiReferenceBase
import com.intellij.psi.util.PsiTreeUtil

class JavaScriptFunctionReference(private val name: String, element: PsiElement, rangeInElement: TextRange) : PsiReferenceBase<PsiElement>(element, rangeInElement) {
    override fun resolve(): PsiElement? {
        val containingFile = myElement.containingFile ?: return null
        return PsiTreeUtil.collectElementsOfType(containingFile, JavaScriptFunctionDeclaration::class.java)
            .map { it.nameIdentifier }
            .firstOrNull { it?.text == name }
    }
}