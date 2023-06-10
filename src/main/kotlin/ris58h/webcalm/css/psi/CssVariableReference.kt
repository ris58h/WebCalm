package ris58h.webcalm.css.psi

import com.intellij.openapi.util.TextRange
import com.intellij.psi.*
import com.intellij.psi.util.PsiTreeUtil
import com.intellij.util.SmartList
import com.intellij.util.containers.SmartHashSet

class CssVariableReference(private val name: String, element: CssVariable, rangeInElement: TextRange) :
    PsiPolyVariantReferenceBase<CssVariable>(element, rangeInElement) {

    override fun multiResolve(incompleteCode: Boolean): Array<ResolveResult> {
        val declarations = SmartList<PsiElement>()
        processDeclarations {
            if (it.name == name) declarations.add(it)
        }
        return PsiElementResolveResult.createResults(declarations)
    }

    override fun getVariants(): Array<Any> {
        val variants = SmartHashSet<String>()
        processDeclarations {
            val name = it.name
            if (name != null) variants.add(name)
        }
        return variants.toTypedArray()
    }

    override fun handleElementRename(newElementName: String): PsiElement {
        return myElement.setName(newElementName)
    }

    private fun processDeclarations(callback: (PsiNamedElement) -> Unit) {
        val containingFile = myElement.containingFile
        PsiTreeUtil.processElements(containingFile, CssVariable::class.java) {
            if (it.isDeclaration) callback(it)
            return@processElements true
        }
    }
}