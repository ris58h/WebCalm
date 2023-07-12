package ris58h.webcalm.html

import com.intellij.openapi.util.TextRange
import com.intellij.patterns.PlatformPatterns.*
import com.intellij.psi.*
import com.intellij.psi.util.PsiTreeUtil
import com.intellij.psi.xml.XmlAttribute
import com.intellij.psi.xml.XmlElement
import com.intellij.util.ProcessingContext
import com.intellij.util.SmartList
import ris58h.webcalm.javascript.psi.*

class IdSelectorReferenceContributor : PsiReferenceContributor() {
    override fun registerReferenceProviders(registrar: PsiReferenceRegistrar) {
        registrar.registerReferenceProvider(CALL_EXPRESSION_STRING_ARG_PATTERN, object : PsiReferenceProvider() {
            override fun getReferencesByElement(element: PsiElement, context: ProcessingContext): Array<PsiReference> {
                if (element !is JavaScriptLiteral) return PsiReference.EMPTY_ARRAY
                val value = element.value
                if (value !is String || value.isEmpty()) return PsiReference.EMPTY_ARRAY

                if (injectionContext(element) == null) return PsiReference.EMPTY_ARRAY

                val callExpression = PsiTreeUtil.getParentOfType(element, JavaScriptCallExpression::class.java)
                if (callExpression != null && isQuerySelectorCall(callExpression)) {
                    return findHashRanges(value).map {
                        val startOffset = it.startOffset
                        val endOffset = it.endOffset
                        val id = value.substring(startOffset + 1, endOffset)
                        // We adjust text range because of quotes.
                        val textRange = TextRange(startOffset + 1, endOffset + 1)
                        ElementReference(id, element, textRange)
                    }.toTypedArray()
                }
                return PsiReference.EMPTY_ARRAY
            }
        })
    }
}

private val CALL_EXPRESSION_STRING_ARG_PATTERN = psiElement(JavaScriptLiteral::class.java)
    .inside(
        psiElement(JavaScriptArgument::class.java)
            .inside(psiElement(JavaScriptCallExpression::class.java))
    )

private fun injectionContext(element: PsiElement): XmlElement? = element.containingFile?.context as? XmlElement

private fun isQuerySelectorCall(callExpression: JavaScriptCallExpression): Boolean {
    val name = functionName(callExpression)
    return name == "querySelector" || name == "querySelectorAll"
}

private fun functionName(callExpression: JavaScriptCallExpression): String? {
    return when (val firstChild = callExpression.firstChild) {
        is JavaScriptIdentifierExpression -> firstChild.identifier.name
        is JavaScriptMemberDotExpression -> firstChild.member?.text
        else -> null
    }
}

private val ID_REGEX = Regex("#[_a-zA-Z0-9][_a-zA-Z0-9-]*")

private fun findHashRanges(string: String): List<TextRange> {
    return ID_REGEX.findAll(string).map {
        val range = it.range
        TextRange(range.first, range.last + 1)
    }.toList()
}

private class ElementReference(val id: String, element: PsiElement, rangeInElement: TextRange) :
    PsiPolyVariantReferenceBase<PsiElement>(element, rangeInElement) {
    override fun multiResolve(incompleteCode: Boolean): Array<ResolveResult> {
        val declarations = SmartList<PsiElement>()
        injectionContext(element)?.containingFile?.accept(object : XmlRecursiveElementVisitor() {
            override fun visitXmlAttribute(attribute: XmlAttribute) {
                if (attribute.name == "id" && attribute.value == id) {
                    val valueElement = attribute.valueElement
                    if (valueElement != null) {
                        declarations.add(valueElement)
                    }
                }
            }
        })
        return PsiElementResolveResult.createResults(declarations)
    }
}