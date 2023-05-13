package ris58h.webcalm.css

import com.intellij.lang.ASTNode
import com.intellij.lang.folding.FoldingBuilderEx
import com.intellij.lang.folding.FoldingDescriptor
import com.intellij.openapi.editor.Document
import com.intellij.openapi.util.TextRange
import com.intellij.psi.PsiElement
import com.intellij.psi.util.PsiTreeUtil
import ris58h.webcalm.css.psi.CssTypes

class CssFoldingBuilder : FoldingBuilderEx() {
    override fun buildFoldRegions(root: PsiElement, document: Document, quick: Boolean): Array<FoldingDescriptor> {
        val descriptors = mutableListOf<FoldingDescriptor>()
        PsiTreeUtil.processElements(root) {
            val node = it.node
            when (node.elementType) {
                CssTypes.COMMENT -> {
                    val textRange = node.textRange
                    if (isOnMultipleLines(textRange, document)) {
                        descriptors.add(FoldingDescriptor(node, textRange, null, "/*...*/"))
                    }
                }
                CssTypes.OPEN_BRACE -> {
                    val closeBrace = findValidCloseBrace(node)
                    if (closeBrace != null) {
                        val textRange = TextRange(node.textRange.startOffset, closeBrace.textRange.endOffset)
                        if (isOnMultipleLines(textRange, document)) {
                            descriptors.add(FoldingDescriptor(node, textRange, null, "{...}"))
                        }
                    }
                }
            }
            return@processElements true
        }
        return descriptors.toTypedArray()
    }

    private fun findValidCloseBrace(openBraceNode: ASTNode): ASTNode? {
        var next: ASTNode? = openBraceNode.treeNext
        while (next != null) {
            if (next.elementType == CssTypes.CLOSE_BRACE) return next
            if (next.elementType == CssTypes.OPEN_BRACE) return null
            next = next.treeNext
        }
        return null
    }

    override fun getPlaceholderText(node: ASTNode): String? = null

    override fun isCollapsedByDefault(node: ASTNode): Boolean = false

    private fun isOnMultipleLines(textRange: TextRange, document: Document): Boolean {
        val startLineNumber = document.getLineNumber(textRange.startOffset)
        val endLineNumber = document.getLineNumber(textRange.endOffset)
        return startLineNumber != endLineNumber
    }
}