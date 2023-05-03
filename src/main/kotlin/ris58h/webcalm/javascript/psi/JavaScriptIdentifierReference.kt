package ris58h.webcalm.javascript.psi

import com.intellij.openapi.util.TextRange
import com.intellij.psi.PsiElement
import com.intellij.psi.PsiElementResolveResult
import com.intellij.psi.PsiPolyVariantReferenceBase
import com.intellij.psi.ResolveResult
import com.intellij.psi.util.PsiTreeUtil

class JavaScriptIdentifierReference(private val name: String, element: PsiElement, rangeInElement: TextRange) : PsiPolyVariantReferenceBase<PsiElement>(element, rangeInElement) {
    override fun multiResolve(incompleteCode: Boolean): Array<ResolveResult> {
        //TODO: not only functions
        //TODO: functions from other files
        //TODO: take scopes into account
        val containingFile = myElement.containingFile ?: return ResolveResult.EMPTY_ARRAY
        val elements = PsiTreeUtil.collectElementsOfType(containingFile, JavaScriptFunctionDeclaration::class.java)
            .filter { it?.name == name }
        return PsiElementResolveResult.createResults(elements)
    }
}