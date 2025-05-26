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
    override fun getLineMarkerInfo(element: PsiElement) = null

    override fun collectSlowLineMarkers(elements: List<PsiElement>, result: MutableCollection<in LineMarkerInfo<*>>) {
        val lines = HashSet<Int>()
        for (element in elements) {
            if (element is JavaScriptIdentifierExpression) {
                val parent = element.parent
                if (parent is JavaScriptCallExpression) {
                    val functionDeclaration = PsiTreeUtil.getParentOfType(parent, JavaScriptFunctionDeclaration::class.java) ?: continue
                    if (element.identifier.name == functionDeclaration.name) {
                        val document = PsiDocumentManager.getInstance(element.project).getDocument(element.containingFile) ?: continue
                        val lineNumber = document.getLineNumber(element.textOffset)
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