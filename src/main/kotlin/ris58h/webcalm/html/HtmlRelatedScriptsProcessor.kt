package ris58h.webcalm.html

import com.intellij.lang.injection.InjectedLanguageManager
import com.intellij.psi.PsiFile
import com.intellij.psi.PsiManager
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
            if (containingFile is XmlFile) processHtmlFile(containingFile) {
                if (it !== file) callback(it)
            }
        }
    }

    private fun processHtmlFile(htmlFile: XmlFile, callback: (JavaScriptFile) -> Unit) {
        val rootTag = htmlFile.rootTag
        val headTag = rootTag?.findFirstSubTag("head")
        if (headTag != null) processScripts(headTag, callback)
        val bodyTag = rootTag?.findFirstSubTag("body")
        if (bodyTag != null) processScripts(bodyTag, callback)
    }

    private fun processScripts(tag: XmlTag, callback: (JavaScriptFile) -> Unit) {
        val scripts = tag.findSubTags("script")
        scripts.filter { isJavaScriptScriptTag(it) }.forEach { script ->
            processInjectedScript(script, callback)
            processExternalScript(script, callback)
        }
    }

    private fun processInjectedScript(script: XmlTag, callback: (JavaScriptFile) -> Unit) {
        val injectedLanguageManager = InjectedLanguageManager.getInstance(script.project)
        script.value.textElements.forEach { textElement ->
            val injectedPsiFiles = injectedLanguageManager.getInjectedPsiFiles(textElement)
            injectedPsiFiles?.forEach {
                val element = it.first
                if (element is JavaScriptFile) callback(element)
            }
        }
    }

    private fun processExternalScript(script: XmlTag, callback: (JavaScriptFile) -> Unit) {
        val src = script.getAttributeValue("src")
        if (src != null) {
            val srcFile = resolveFile(script.containingFile, src)
            if (srcFile is JavaScriptFile) callback(srcFile)
        }
    }

    private fun resolveFile(file: PsiFile, path: String): PsiFile? {
        val resolvedVirtualFile = file.virtualFile.parent.findFileByRelativePath(path) ?: return null
        return PsiManager.getInstance(file.project).findFile(resolvedVirtualFile)
    }
}

fun isJavaScriptScriptTag(scriptTag: XmlTag): Boolean {
    val type = scriptTag.getAttributeValue("type")
    return type.isNullOrEmpty() || type == "text/javascript" || type == "module"
}