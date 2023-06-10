package ris58h.webcalm.javascript.psi

import com.intellij.openapi.project.Project
import com.intellij.psi.PsiElement
import com.intellij.psi.PsiFileFactory
import com.intellij.psi.util.PsiTreeUtil
import ris58h.webcalm.javascript.JavaScriptFileType

object JavaScriptElementFactory {
    fun createIdentifierTokenFromText(project: Project, text: String) : PsiElement {
        return createIdentifierFromText(project, text)
            .node
            .findChildByType(JavaScriptTypes.IDENTIFIER)
            ?.psi!!
    }

    fun createIdentifierFromText(project: Project, text: String) : JavaScriptIdentifier {
        val javaScriptFile = createFileFromText(project, text)
        return PsiTreeUtil.findChildOfType(javaScriptFile, JavaScriptIdentifier::class.java, true)!!
    }

    fun createFileFromText(project: Project, text: String) : JavaScriptFile {
        return PsiFileFactory.getInstance(project)
            .createFileFromText("dummy.js", JavaScriptFileType.INSTANCE, text) as JavaScriptFile
    }
}