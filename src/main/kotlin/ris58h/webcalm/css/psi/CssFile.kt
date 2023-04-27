package ris58h.webcalm.css.psi

import com.intellij.extapi.psi.PsiFileBase
import com.intellij.openapi.fileTypes.FileType
import com.intellij.psi.FileViewProvider
import ris58h.webcalm.css.CssFileType
import ris58h.webcalm.css.CssLanguage

class CssFile(viewProvider: FileViewProvider) : PsiFileBase(viewProvider, CssLanguage) {
    override fun getFileType(): FileType = CssFileType.INSTANCE

    override fun toString(): String = "CSS file"
}