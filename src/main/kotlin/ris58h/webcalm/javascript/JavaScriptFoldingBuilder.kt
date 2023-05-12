package ris58h.webcalm.javascript

import com.intellij.lang.ASTNode
import com.intellij.lang.folding.FoldingBuilderEx
import com.intellij.lang.folding.FoldingDescriptor
import com.intellij.openapi.editor.Document
import com.intellij.openapi.util.TextRange
import com.intellij.psi.PsiElement
import com.intellij.psi.util.PsiTreeUtil
import ris58h.webcalm.javascript.psi.JavaScriptArray
import ris58h.webcalm.javascript.psi.JavaScriptBlock
import ris58h.webcalm.javascript.psi.JavaScriptFunctionBody
import ris58h.webcalm.javascript.psi.JavaScriptObject

class JavaScriptFoldingBuilder : FoldingBuilderEx() {
    override fun buildFoldRegions(root: PsiElement, document: Document, quick: Boolean): Array<FoldingDescriptor> {
        val descriptors = mutableListOf<FoldingDescriptor>()
        PsiTreeUtil.processElements(root) {
            val placeholderText = when (it) {
                is JavaScriptFunctionBody, is JavaScriptBlock, is JavaScriptObject -> "{...}"
                is JavaScriptArray -> "[...]"
                else -> null
            }
            if (placeholderText != null) {
                val textRange = it.textRange
                if (spanMultipleLines(textRange, document)) {
                    descriptors.add(FoldingDescriptor(it.node, textRange, null, placeholderText))
                }
            }
            return@processElements true
        }
        return descriptors.toTypedArray()
    }

    override fun getPlaceholderText(node: ASTNode): String? = null

    override fun isCollapsedByDefault(node: ASTNode): Boolean = false

    private fun spanMultipleLines(textRange: TextRange, document: Document): Boolean {
        val endOffset = textRange.endOffset
        val startLineNumber = document.getLineNumber(textRange.startOffset)
        val endLineNumber = if (endOffset < document.textLength) document.getLineNumber(endOffset) else document.lineCount - 1
        return startLineNumber < endLineNumber
    }
}