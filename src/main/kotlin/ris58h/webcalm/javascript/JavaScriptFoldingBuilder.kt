package ris58h.webcalm.javascript

import com.intellij.lang.ASTNode
import com.intellij.lang.folding.FoldingBuilderEx
import com.intellij.lang.folding.FoldingDescriptor
import com.intellij.openapi.editor.Document
import com.intellij.openapi.util.TextRange
import com.intellij.psi.PsiComment
import com.intellij.psi.PsiElement
import com.intellij.psi.util.PsiTreeUtil
import ris58h.webcalm.javascript.psi.*

class JavaScriptFoldingBuilder : FoldingBuilderEx() {
    override fun buildFoldRegions(root: PsiElement, document: Document, quick: Boolean): Array<FoldingDescriptor> {
        val descriptors = mutableListOf<FoldingDescriptor>()
        PsiTreeUtil.processElements(root) {
            val textRangeAndPlaceholderText = when (it) {
                is JavaScriptFunctionBody, is JavaScriptBlock, is JavaScriptObject, is JavaScriptCaseBlock -> Pair(it.textRange, "{...}")
                is JavaScriptArray -> Pair(it.textRange, "[...]")
                is JavaScriptClassDeclaration -> {
                    val openBraceOffset = it.node.findChildByType(JavaScriptTypes.OPEN_BRACE)?.startOffset
                    if (openBraceOffset == null) null
                    else Pair(TextRange(openBraceOffset, it.textRange.endOffset), "{...}")
                }
                is PsiComment -> {
                    if (it.node.elementType == JavaScriptTypes.MULTILINE_COMMENT) Pair(it.textRange, "/*...*/")
                    else null
                }
                else -> null
            }
            if (textRangeAndPlaceholderText != null) {
                val textRange = textRangeAndPlaceholderText.first
                val placeholderText = textRangeAndPlaceholderText.second
                if (isOnMultipleLines(textRange, document)) {
                    descriptors.add(FoldingDescriptor(it.node, textRange, null, placeholderText))
                }
            }
            return@processElements true
        }
        return descriptors.toTypedArray()
    }

    override fun getPlaceholderText(node: ASTNode) = null

    override fun isCollapsedByDefault(node: ASTNode) = false

    private fun isOnMultipleLines(textRange: TextRange, document: Document): Boolean {
        val startLineNumber = document.getLineNumber(textRange.startOffset)
        val endLineNumber = document.getLineNumber(textRange.endOffset)
        return startLineNumber != endLineNumber
    }
}