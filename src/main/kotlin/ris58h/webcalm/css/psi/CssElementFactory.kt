package ris58h.webcalm.css.psi

import com.intellij.openapi.project.Project
import com.intellij.psi.PsiFileFactory
import com.intellij.psi.util.PsiTreeUtil
import ris58h.webcalm.css.CssFileType

object CssElementFactory {
    fun createVariableFromText(project: Project, name: String): CssVariable {
        val cssFile = createFileFromText(project, "* {${name}: none}")
        return PsiTreeUtil.findChildOfType(cssFile, CssVariable::class.java)!!
    }

    fun createFileFromText(project: Project, text: String) : CssFile {
        return PsiFileFactory.getInstance(project)
            .createFileFromText("dummy.css", CssFileType.INSTANCE, text) as CssFile;
    }
}