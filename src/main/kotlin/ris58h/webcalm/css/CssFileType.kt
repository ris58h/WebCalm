package ris58h.webcalm.css

import com.intellij.icons.AllIcons
import com.intellij.openapi.fileTypes.LanguageFileType

class CssFileType : LanguageFileType(CssLanguage) {
    companion object {
        val INSTANCE = CssFileType()
    }

    override fun getName() = "CSS"

    override fun getDescription() = "CSS file"

    override fun getDefaultExtension() = "css"

    override fun getIcon() = AllIcons.FileTypes.Css
}
