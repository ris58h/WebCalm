package ris58h.webcalm.javascript.psi

import com.intellij.extapi.psi.PsiFileBase
import com.intellij.psi.FileViewProvider
import ris58h.webcalm.javascript.JavaScriptFileType
import ris58h.webcalm.javascript.JavaScriptLanguage

class JavaScriptFile(viewProvider: FileViewProvider) :
    PsiFileBase(viewProvider, JavaScriptLanguage), JavaScriptStatementsOwner {
    override fun getFileType() = JavaScriptFileType.INSTANCE

    override fun toString() = "JavaScript file"
}