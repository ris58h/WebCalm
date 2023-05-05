package ris58h.webcalm.javascript

import com.intellij.icons.AllIcons
import com.intellij.openapi.fileTypes.LanguageFileType
import javax.swing.Icon

class JavaScriptFileType : LanguageFileType(JavaScriptLanguage) {
    companion object {
        val INSTANCE = JavaScriptFileType()
    }

    override fun getName(): String = "JavaScript"

    override fun getDescription(): String = "JavaScript file"

    override fun getDefaultExtension(): String = "js"

    override fun getIcon(): Icon = AllIcons.FileTypes.JavaScript
}
