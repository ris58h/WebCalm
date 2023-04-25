package ris58h.webcalm.css

import com.intellij.icons.AllIcons
import com.intellij.openapi.fileTypes.LanguageFileType
import javax.swing.Icon

class CssFileType : LanguageFileType(CssLanguage) {
    companion object {
        @JvmField
        val INSTANCE = CssFileType()
    }

    override fun getName(): String = "CSS"

    override fun getDescription(): String = "CSS file"

    override fun getDefaultExtension(): String = "css"

    override fun getIcon(): Icon = AllIcons.FileTypes.Text
}
