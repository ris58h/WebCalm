package ris58h.webcalm.javascript

import com.intellij.codeInsight.daemon.LineMarkerInfo
import com.intellij.codeInsight.daemon.LineMarkerProvider
import com.intellij.icons.AllIcons
import com.intellij.openapi.editor.markup.GutterIconRenderer
import com.intellij.openapi.project.DumbAware
import com.intellij.psi.PsiDocumentManager
import com.intellij.psi.PsiElement
import com.intellij.psi.util.PsiTreeUtil
import com.intellij.util.FunctionUtil
import ris58h.webcalm.javascript.psi.JavaScriptCallExpression
import ris58h.webcalm.javascript.psi.JavaScriptFunctionDeclaration
import ris58h.webcalm.javascript.psi.JavaScriptIdentifierExpression
import java.util.function.Supplier

class JavaScriptRecursiveCallLineMarkerProvider : LineMarkerProvider, DumbAware {
    override fun getLineMarkerInfo(element: PsiElement): LineMarkerInfo<*>? = null

    override fun collectSlowLineMarkers(elements: MutableList<out PsiElement>, result: MutableCollection<in LineMarkerInfo<*>>) {
        val lines = HashSet<Int>()
        for (element in elements) {
            if (element is JavaScriptIdentifierExpression) {
                val name = element.name ?: continue
                val parent = element.parent
                if (parent is JavaScriptCallExpression) {
                    val functionDeclaration = PsiTreeUtil.getParentOfType(parent, JavaScriptFunctionDeclaration::class.java) ?: continue
                    if (name == functionDeclaration.name) {
                        val document = PsiDocumentManager.getInstance(element.getProject()).getDocument(element.getContainingFile()) ?: continue
                        val lineNumber = document.getLineNumber(element.getTextOffset())
                        if (!lines.contains(lineNumber)) {
                            lines.add(lineNumber)
                            result.add(RecursiveMethodCallMarkerInfo(element))
                        }
                    }
                }
            }
        }
    }

    private class RecursiveMethodCallMarkerInfo(e: PsiElement) : LineMarkerInfo<PsiElement>(
        e,
        e.textRange,
        AllIcons.Gutter.RecursiveMethod,
        FunctionUtil.constant("Recursive call"),
        null,
        GutterIconRenderer.Alignment.RIGHT,
        Supplier { "Recursive call" }
    )
}