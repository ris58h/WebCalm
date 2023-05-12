package ris58h.webcalm.css

import com.intellij.lang.ASTNode
import com.intellij.lang.folding.FoldingBuilderEx
import com.intellij.lang.folding.FoldingDescriptor
import com.intellij.openapi.editor.Document
import com.intellij.openapi.util.TextRange
import com.intellij.psi.PsiElement
import com.intellij.psi.util.PsiTreeUtil
import ris58h.webcalm.css.psi.CssTokenSets
import ris58h.webcalm.css.psi.CssTypes

class CssFoldingBuilder : FoldingBuilderEx() {
    override fun buildFoldRegions(root: PsiElement, document: Document, quick: Boolean): Array<FoldingDescriptor> {
        val descriptors = mutableListOf<FoldingDescriptor>()
        PsiTreeUtil.processElements(root) {
            val node = it.node
            if (node.elementType == CssTypes.COMMENT) {
                val textRange = node.textRange
                if (isOnMultipleLines(textRange, document)) {
                    descriptors.add(FoldingDescriptor(node, textRange, null, "/*...*/"))
                }
            } else {
                val children = node.getChildren(CssTokenSets.BRACES)
                if (children.size == 2) {
                    val first = children[0]
                    val second = children[1]
                    if (first.elementType == CssTypes.OPEN_BRACE && second.elementType == CssTypes.CLOSE_BRACE) {
                        val textRange = TextRange(first.textRange.startOffset, second.textRange.endOffset)
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

    override fun getPlaceholderText(node: ASTNode): String? = null

    override fun isCollapsedByDefault(node: ASTNode): Boolean = false

    private fun isOnMultipleLines(textRange: TextRange, document: Document): Boolean {
        val startLineNumber = document.getLineNumber(textRange.startOffset)
        val endLineNumber = document.getLineNumber(textRange.endOffset)
        return startLineNumber != endLineNumber
    }
}