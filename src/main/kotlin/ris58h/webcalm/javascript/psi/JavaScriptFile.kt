package ris58h.webcalm.javascript.psi

import com.intellij.extapi.psi.PsiFileBase
import com.intellij.openapi.fileTypes.FileType
import com.intellij.psi.FileViewProvider
import ris58h.webcalm.javascript.JavaScriptFileType
import ris58h.webcalm.javascript.JavaScriptLanguage

class JavaScriptFile(viewProvider: FileViewProvider) : PsiFileBase(viewProvider, JavaScriptLanguage) {
    override fun getFileType(): FileType = JavaScriptFileType.INSTANCE
}