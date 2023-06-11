package ris58h.webcalm.html

import com.intellij.lang.injection.MultiHostInjector
import com.intellij.lang.injection.MultiHostRegistrar
import com.intellij.psi.PsiElement
import com.intellij.psi.PsiLanguageInjectionHost
import com.intellij.psi.templateLanguages.OuterLanguageElement
import com.intellij.psi.xml.XmlText
import com.intellij.util.containers.ContainerUtil
import com.intellij.xml.util.HtmlUtil
import ris58h.webcalm.javascript.JavaScriptLanguage

class HtmlScriptTagJavaScriptInjector : MultiHostInjector {
    override fun getLanguagesToInject(registrar: MultiHostRegistrar, host: PsiElement) {
        if (!host.isValid || host !is XmlText || !HtmlUtil.isHtmlTagContainingFile(host)) {
            return
        }
        if (!HtmlUtil.isScriptTag(host.parentTag)) {
            return
        }
        if (isJavaScriptScriptTag(host.parentTag!!)) {
            val elements: List<PsiElement> = ContainerUtil.filter(host.getChildren()) { it !is OuterLanguageElement }
            if (elements.isEmpty()) return
            registrar.startInjecting(JavaScriptLanguage)
            for (child in elements) {
                registrar.addPlace(null, null, host as PsiLanguageInjectionHost, child.textRangeInParent)
            }
            registrar.doneInjecting()
        }
    }

    override fun elementsToInjectIn(): List<Class<out PsiElement>> {
        return listOf(XmlText::class.java)
    }
}