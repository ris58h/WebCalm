package ris58h.webcalm.css

import com.intellij.openapi.editor.colors.TextAttributesKey
import com.intellij.openapi.fileTypes.SyntaxHighlighterBase
import com.intellij.openapi.fileTypes.SyntaxHighlighterFactory
import com.intellij.openapi.project.Project
import com.intellij.openapi.vfs.VirtualFile
import com.intellij.psi.tree.IElementType
import ris58h.webcalm.css.psi.CssTokenSets
import ris58h.webcalm.css.psi.CssTypes

class CssSyntaxHighlighter : SyntaxHighlighterBase() {
    class Factory : SyntaxHighlighterFactory() {
        override fun getSyntaxHighlighter(project: Project?, virtualFile: VirtualFile?) = CssSyntaxHighlighter()
    }

    override fun getHighlightingLexer() = CssLexer()

    override fun getTokenHighlights(tokenType: IElementType): Array<TextAttributesKey> {
        val tokenColor = tokenColor(tokenType)
        return if (tokenColor == null) TextAttributesKey.EMPTY_ARRAY else arrayOf(tokenColor)
    }

    private fun tokenColor(tokenType: IElementType): TextAttributesKey? {
        //TODO: other token types
        return when (tokenType) {
            CssTypes.NUMBER, CssTypes.DIMENSION, CssTypes.PERCENTAGE -> CssSyntaxHighlighterColors.NUMBER
            CssTypes.HASH -> CssSyntaxHighlighterColors.CONSTANT
            CssTypes.VARIABLE -> CssSyntaxHighlighterColors.VARIABLE
            else -> tokenColorFromTokenSets(tokenType)
        }
    }

    private fun tokenColorFromTokenSets(tokenType: IElementType): TextAttributesKey? {
        if (CssTokenSets.COMMENTS.contains(tokenType)) {
            return CssSyntaxHighlighterColors.COMMENT
        }
        if (CssTokenSets.STRINGS.contains(tokenType)) {
            return CssSyntaxHighlighterColors.STRING
        }
        if (CssTokenSets.BRACES.contains(tokenType)) {
            return CssSyntaxHighlighterColors.BRACES
        }
        if (CssTokenSets.BRACKETS.contains(tokenType)) {
            return CssSyntaxHighlighterColors.BRACKETS
        }
        if (CssTokenSets.PARENTHESES.contains(tokenType)) {
            return CssSyntaxHighlighterColors.PARENTHESES
        }
        if (CssTokenSets.AT_KEYWORDS.contains(tokenType)) {
            return CssSyntaxHighlighterColors.KEYWORD
        }
        if (CssTokenSets.KEYWORDS.contains(tokenType)) {
            return CssSyntaxHighlighterColors.KEYWORD
        }
        if (CssTokenSets.OPERATIONS.contains(tokenType)) {
            return CssSyntaxHighlighterColors.OPERATION_SIGN
        }
        if (CssTokenSets.FUNCTIONS.contains(tokenType)) {
            return CssSyntaxHighlighterColors.FUNCTION_CALL
        }
        return null
    }
}