package ris58h.webcalm.css.formatting

import com.intellij.formatting.*
import com.intellij.lang.ASTNode
import com.intellij.psi.formatter.common.AbstractBlock
import ris58h.webcalm.css.psi.*

class CssBlock(node: ASTNode, wrap: Wrap?, alignment: Alignment?) : AbstractBlock(node, wrap, alignment) {
    override fun getIndent(): Indent {
        val elementType = myNode.elementType
        val element = myNode.psi
        val parentElement = element.parent

        if (parentElement is CssFile) {
            return Indent.getNoneIndent()
        }

        if (element is CssNestedStatement) {
            return Indent.getNormalIndent()
        }

        if (CssTokenSets.BRACES.contains(elementType) ||
            CssTokenSets.BRACKETS.contains(elementType) ||
            CssTokenSets.PARENTHESES.contains(elementType)) {
            return Indent.getNoneIndent()
        }

        if (parentElement is CssDeclarationList) {
            return Indent.getNormalIndent()
        }

        return Indent.getNoneIndent()
    }

    override fun getSpacing(child1: Block?, child2: Block): Spacing? {
        return null//TODO
    }

    override fun isLeaf(): Boolean {
        return myNode.firstChildNode == null
    }

    override fun buildChildren(): List<Block> {
        if (isLeaf) return emptyList()
        return buildSubBlocks()
    }

    private fun buildSubBlocks(): List<Block> {
        val blocks = mutableListOf<Block>()
        var child = myNode.firstChildNode
        while (child != null) {
            val childType = child.elementType
            if (child.textRange.length == 0) {
                child = child.treeNext
                continue
            }
            if (childType === CssTypes.SPACE) {
                child = child.treeNext
                continue
            }
            blocks.add(CssBlock(child, null, null))
            child = child.treeNext
        }
        return blocks
    }

    override fun getChildIndent(): Indent? {
        //TODO: do not duplicate logic from getIndent
        val element = myNode.psi

        if (element is CssFile) {
            return Indent.getNoneIndent()
        }

        if (element is CssDeclarationList) {
            return Indent.getNormalIndent()
        }

        return super.getChildIndent()
    }
}