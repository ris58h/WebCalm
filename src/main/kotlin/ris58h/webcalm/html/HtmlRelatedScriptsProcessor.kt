package ris58h.webcalm.html

import com.intellij.lang.injection.InjectedLanguageManager
import com.intellij.psi.xml.XmlElement
import com.intellij.psi.xml.XmlFile
import com.intellij.psi.xml.XmlTag
import ris58h.webcalm.javascript.RelatedScriptsProcessor
import ris58h.webcalm.javascript.psi.JavaScriptFile

class HtmlRelatedScriptsProcessor : RelatedScriptsProcessor {
    override fun processRelatedScripts(file: JavaScriptFile, callback: (JavaScriptFile) -> Unit) {
        val context = file.context
        if (context is XmlElement) {
            val containingFile = context.containingFile
            if (containingFile is XmlFile) processHtmlFile(containingFile, file, callback)
        }
    }

    private fun processHtmlFile(htmlFile: XmlFile, processed: JavaScriptFile, callback: (JavaScriptFile) -> Unit) {
        val rootTag = htmlFile.rootTag
        val headTag = rootTag?.findFirstSubTag("head")
        if (headTag != null) processScripts(headTag, processed, callback)
        val bodyTag = rootTag?.findFirstSubTag("body")
        if (bodyTag != null) processScripts(bodyTag, processed, callback)
    }

    private fun processScripts(tag: XmlTag, processed: JavaScriptFile, callback: (JavaScriptFile) -> Unit) {
        val injectedLanguageManager = InjectedLanguageManager.getInstance(tag.project)
        val scripts = tag.findSubTags("script")
        scripts.forEach { script ->
            script.value.textElements.forEach { textElement ->
                val injectedPsiFiles = injectedLanguageManager.getInjectedPsiFiles(textElement)
                injectedPsiFiles?.forEach {
                    val element = it.first
                    if (element is JavaScriptFile && element !== processed) {
                        callback(element)
                    }
                }
            }
        }
    }
}