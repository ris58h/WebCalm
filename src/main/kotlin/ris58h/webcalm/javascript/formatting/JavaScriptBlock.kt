package ris58h.webcalm.javascript.formatting

import com.intellij.formatting.*
import com.intellij.lang.ASTNode
import com.intellij.psi.PsiComment
import com.intellij.psi.TokenType
import com.intellij.psi.formatter.common.AbstractBlock
import ris58h.webcalm.javascript.psi.*

class JavaScriptBlock(node: ASTNode, wrap: Wrap?, alignment: Alignment?) : AbstractBlock(node, wrap, alignment) {
    override fun getIndent(): Indent {
        val elementType = myNode.elementType
        val element = myNode.psi
        val parentElement = element.parent

        //TODO: it's a hack to indent dot expressions on new lines. It doesn't work for multiline code blocks (try to format arrow-functions.js).
        if (elementType == JavaScriptTypes.DOT || elementType == JavaScriptTypes.QUESTION_MARK_DOT) {
            return Indent.getNormalIndent()
        }
        //TODO: the same hack as above
        if (elementType == JavaScriptTypes.QUESTION_MARK || elementType == JavaScriptTypes.COLON) {
            return Indent.getNormalIndent()
        }

        if (parentElement is JavaScriptFile) {
            return Indent.getNoneIndent()
        }

        if (JavaScriptTokenSets.BRACES.contains(elementType) ||
            JavaScriptTokenSets.BRACKETS.contains(elementType) ||
            JavaScriptTokenSets.PARENTHESES.contains(elementType)
        ) {
            return Indent.getNoneIndent()
        }

        if ((parentElement is JavaScriptStatementsOwner && (element is JavaScriptStatement || element is PsiComment)) ||
            parentElement is JavaScriptObject ||
            parentElement is JavaScriptArray ||
            parentElement is JavaScriptArguments ||
            parentElement is JavaScriptCaseBlock
        ) {
            return Indent.getNormalIndent()
        }

        if (element is JavaScriptClassElement || (element is PsiComment && parentElement is JavaScriptClassDeclaration)) {
            return Indent.getNormalIndent()
        }

        return Indent.getNoneIndent()
    }

    override fun getSpacing(child1: Block?, child2: Block): Spacing? {
        return null//TODO
    }

    override fun isLeaf(): Boolean {
        val element = myNode.psi
        if (element is JavaScriptIdentifier ||
            element is JavaScriptIdentifierName ||
            element is JavaScriptIdentifierExpression ||
            element is JavaScriptLiteral
        ) return true
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
            val skip = child.textRange.length == 0 || child.elementType === TokenType.WHITE_SPACE
            if (!skip) {
                blocks.add(JavaScriptBlock(child, null, null))
            }
            child = child.treeNext
        }
        return blocks
    }

    override fun getChildIndent(): Indent? {
        //TODO: do not duplicate logic from getIndent
        val element = myNode.psi

        if (element is JavaScriptFile) {
            return Indent.getNoneIndent()
        }

        if (element is JavaScriptStatementsOwner ||
            element is JavaScriptObject ||
            element is JavaScriptArray ||
            element is JavaScriptArguments ||
            element is JavaScriptCaseBlock
        ) {
            return Indent.getNormalIndent()
        }

        if (element is JavaScriptClassDeclaration) {
            return Indent.getNormalIndent()
        }

        return super.getChildIndent()
    }
}