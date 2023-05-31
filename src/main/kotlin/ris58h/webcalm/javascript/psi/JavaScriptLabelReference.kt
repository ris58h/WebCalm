package ris58h.webcalm.javascript.psi

import com.intellij.openapi.util.TextRange
import com.intellij.psi.PsiElement
import com.intellij.psi.PsiReferenceBase
import com.intellij.psi.util.PsiTreeUtil
import com.intellij.util.containers.SmartHashSet

class JavaScriptLabelReference(private val label: String, element: PsiElement, rangeInElement: TextRange) : PsiReferenceBase<PsiElement>(element, rangeInElement) {
    override fun resolve(): PsiElement? {
        return PsiTreeUtil.findFirstParent(myElement) {
            it is JavaScriptLabeledStatement && it.name == label
        }
    }

    override fun getVariants(): Array<Any> {
        val variants = SmartHashSet<String>()
        var parent = myElement.parent
        while (parent != null) {
            if (parent is JavaScriptLabeledStatement) {
                val name = parent.name
                if (name != null) variants.add(name)
            }
            parent = parent.parent
        }
        return variants.toTypedArray()
    }

    override fun handleElementRename(newElementName: String): PsiElement {
        val element = myElement
        if (element is JavaScriptIdentifierOwner) {
            return element.setName(newElementName)
        }
        return super.handleElementRename(newElementName)
    }
}