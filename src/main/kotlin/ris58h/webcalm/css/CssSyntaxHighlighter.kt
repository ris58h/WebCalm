package ris58h.webcalm.css

import com.intellij.openapi.editor.DefaultLanguageHighlighterColors
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

    override fun getTokenHighlights(tokenType: IElementType?): Array<TextAttributesKey> {
        if (CssTokenSets.COMMENTS.contains(tokenType)) {
            return COMMENT
        }
        if (CssTokenSets.STRINGS.contains(tokenType)) {
            return STRING
        }
        if (CssTokenSets.BRACES.contains(tokenType)) {
            return BRACES
        }
        if (CssTokenSets.BRACKETS.contains(tokenType)) {
            return BRACKETS
        }
        if (CssTokenSets.PARENTHESES.contains(tokenType)) {
            return PARENTHESES
        }
        if (CssTokenSets.AT_KEYWORDS.contains(tokenType)) {
            return KEYWORD
        }
        if (CssTokenSets.KEYWORDS.contains(tokenType)) {
            return KEYWORD
        }
        if (CssTokenSets.OPERATIONS.contains(tokenType)) {
            return OPERATION_SIGN
        }
        if (CssTokenSets.FUNCTIONS.contains(tokenType)) {
            return FUNCTION_CALL
        }
        //TODO: other token types
        return when (tokenType) {
            CssTypes.NUMBER, CssTypes.DIMENSION, CssTypes.PERCENTAGE -> NUMBER
            CssTypes.HASH -> CONSTANT
            CssTypes.URI -> STRING
            CssTypes.VARIABLE -> LOCAL_VARIABLE
            else -> TextAttributesKey.EMPTY_ARRAY
        }
    }

    companion object {
        private val COMMENT = arrayOf(DefaultLanguageHighlighterColors.LINE_COMMENT)
        private val STRING = arrayOf(DefaultLanguageHighlighterColors.STRING)
        private val BRACES = arrayOf(DefaultLanguageHighlighterColors.BRACES)
        private val BRACKETS = arrayOf(DefaultLanguageHighlighterColors.BRACKETS)
        private val PARENTHESES = arrayOf(DefaultLanguageHighlighterColors.PARENTHESES)
        private val KEYWORD = arrayOf(DefaultLanguageHighlighterColors.KEYWORD)
        private val OPERATION_SIGN = arrayOf(DefaultLanguageHighlighterColors.OPERATION_SIGN)
        private val NUMBER = arrayOf(DefaultLanguageHighlighterColors.NUMBER)
        private val CONSTANT = arrayOf(DefaultLanguageHighlighterColors.CONSTANT)
        private val LOCAL_VARIABLE = arrayOf(DefaultLanguageHighlighterColors.LOCAL_VARIABLE)
        private val FUNCTION_CALL = arrayOf(DefaultLanguageHighlighterColors.FUNCTION_CALL)
    }
}