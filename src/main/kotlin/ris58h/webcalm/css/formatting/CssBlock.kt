package ris58h.webcalm.css.formatting

import com.intellij.formatting.*
import com.intellij.lang.ASTNode
import com.intellij.psi.formatter.common.AbstractBlock
import org.antlr.intellij.adaptor.lexer.TokenIElementType
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

        if (element is CssFeatureValueBlock) {
            return Indent.getNormalIndent()
        }
        if (element is CssFeatureValueDefinition) {
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
            val skip = child.textRange.length == 0 || child.elementType === CssTypes.SPACE
            if (!skip) {
                blocks.add(CssBlock(child, null, null))
            }
            child = child.treeNext
        }
        return blocks
    }

    override fun getChildAttributes(newChildIndex: Int): ChildAttributes {
        // TODO: it's a hack to indent insertion after '{' and before '}'
        val subBlocks = subBlocks
        fun isBraceSubBlock(index: Int, braceElementType: TokenIElementType): Boolean {
            if (index >= 0 && index < subBlocks.size) {
                val subBlock = subBlocks[index]
                if (subBlock.isLeaf && subBlock.textRange.length == 1) {
                    val node = myNode.findLeafElementAt(subBlock.textRange.startOffset - myNode.startOffset)
                    if (node?.elementType == braceElementType) {
                        return true
                    }
                }
            }
            return false
        }
        if (isBraceSubBlock(newChildIndex - 1, CssTypes.OPEN_BRACE) || isBraceSubBlock(newChildIndex, CssTypes.CLOSE_BRACE)) {
            return ChildAttributes(Indent.getNormalIndent(), null)
        }

        return super.getChildAttributes(newChildIndex)
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