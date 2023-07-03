package ris58h.webcalm.javascript

import com.intellij.icons.AllIcons
import com.intellij.openapi.fileTypes.LanguageFileType

class JavaScriptFileType : LanguageFileType(JavaScriptLanguage) {
    companion object {
        val INSTANCE = JavaScriptFileType()
    }

    override fun getName() = "JavaScript"

    override fun getDescription() = "JavaScript file"

    override fun getDefaultExtension() = "js"

    override fun getIcon() = AllIcons.FileTypes.JavaScript
}
