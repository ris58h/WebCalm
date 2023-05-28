package ris58h.webcalm.javascript

import com.intellij.openapi.extensions.ExtensionPointName
import ris58h.webcalm.javascript.psi.JavaScriptFile

interface RelatedScriptsProcessor {
    fun processRelatedScripts(file: JavaScriptFile, callback: (JavaScriptFile) -> Unit)

    companion object {
        val EP_NAME = ExtensionPointName.create<RelatedScriptsProcessor>("ris58h.webcalm.relatedScriptsProcessor")
    }
}