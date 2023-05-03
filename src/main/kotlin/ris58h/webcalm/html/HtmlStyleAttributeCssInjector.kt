package ris58h.webcalm.html

import com.intellij.lang.injection.MultiHostInjector
import com.intellij.lang.injection.MultiHostRegistrar
import com.intellij.psi.PsiElement
import com.intellij.psi.PsiLanguageInjectionHost
import com.intellij.psi.xml.XmlAttribute
import com.intellij.psi.xml.XmlAttributeValue
import com.intellij.psi.xml.XmlTokenType
import com.intellij.xml.util.HtmlUtil
import ris58h.webcalm.css.CssLanguage

class HtmlStyleAttributeCssInjector : MultiHostInjector {
    override fun getLanguagesToInject(registrar: MultiHostRegistrar, context: PsiElement) {
        if (context !is XmlAttributeValue || !isStyleAttribute(context.parent as? XmlAttribute?)) {
            return
        }
        val attributeValueToken = context.node.findChildByType(XmlTokenType.XML_ATTRIBUTE_VALUE_TOKEN) ?: return
        if (attributeValueToken.text.isNotBlank()) {
            registrar.startInjecting(CssLanguage)
            registrar.addPlace(":not(*) {", ";}", context as PsiLanguageInjectionHost, attributeValueToken.psi.textRangeInParent)
            registrar.doneInjecting()
        }
    }

    override fun elementsToInjectIn(): MutableList<out Class<out PsiElement>> {
        return mutableListOf(XmlAttributeValue::class.java)
    }

    private fun isStyleAttribute(attribute: XmlAttribute?): Boolean {
        return attribute != null && attribute.localName.equals(HtmlUtil.STYLE_ATTRIBUTE_NAME, ignoreCase = true)
    }
}