package ris58h.webcalm.html

import com.intellij.lang.injection.MultiHostInjector
import com.intellij.lang.injection.MultiHostRegistrar
import com.intellij.psi.PsiElement
import com.intellij.psi.PsiLanguageInjectionHost
import com.intellij.psi.xml.XmlAttribute
import com.intellij.psi.xml.XmlAttributeValue
import com.intellij.psi.xml.XmlTokenType
import ris58h.webcalm.javascript.JavaScriptLanguage

class HtmlAttributeJavaScriptInjector : MultiHostInjector {
    override fun getLanguagesToInject(registrar: MultiHostRegistrar, context: PsiElement) {
        if (context !is XmlAttributeValue || !isEventHandlerAttribute(context.parent as? XmlAttribute?)) {
            return
        }
        val attributeValueToken = context.node.findChildByType(XmlTokenType.XML_ATTRIBUTE_VALUE_TOKEN) ?: return
        if (attributeValueToken.text.isNotBlank()) {
            registrar.startInjecting(JavaScriptLanguage)
            registrar.addPlace("(function () {", ";})", context as PsiLanguageInjectionHost, attributeValueToken.psi.textRangeInParent)
            registrar.doneInjecting()
        }
    }

    override fun elementsToInjectIn() = listOf(XmlAttributeValue::class.java)

    private fun isEventHandlerAttribute(attribute: XmlAttribute?): Boolean {
        return attribute != null && HtmlAttributes.isEventHandlerAttribute(attribute.localName)
    }
}