package ris58h.webcalm.javascript.psi

import com.intellij.extapi.psi.ASTWrapperPsiElement
import com.intellij.lang.ASTNode
import com.intellij.psi.PsiLiteralValue
import com.intellij.psi.PsiReference
import com.intellij.psi.PsiReferenceService
import com.intellij.psi.impl.source.resolve.reference.ReferenceProvidersRegistry
import com.intellij.util.ArrayUtil

class JavaScriptLiteral(node: ASTNode) : ASTWrapperPsiElement(node), PsiLiteralValue {
    override fun getReference(): PsiReference? = ArrayUtil.getFirstElement(references)

    override fun getReferences(): Array<PsiReference> =
        ReferenceProvidersRegistry.getReferencesFromProviders(this, PsiReferenceService.Hints.NO_HINTS)

    override fun getValue(): Any? {
        val firstChild = firstChild
        return when (firstChild.node.elementType) {
            JavaScriptTypes.STRING -> stripQuotes(firstChild.text)
            else -> null
        }
    }

    private fun stripQuotes(value: String): String {
        return value.substring(1, value.lastIndex)
    }
}