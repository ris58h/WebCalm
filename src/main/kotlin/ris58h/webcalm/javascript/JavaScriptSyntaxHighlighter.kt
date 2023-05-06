package ris58h.webcalm.javascript

import com.intellij.lexer.Lexer
import com.intellij.openapi.editor.DefaultLanguageHighlighterColors
import com.intellij.openapi.editor.colors.TextAttributesKey
import com.intellij.openapi.fileTypes.SyntaxHighlighter
import com.intellij.openapi.fileTypes.SyntaxHighlighterBase
import com.intellij.openapi.fileTypes.SyntaxHighlighterFactory
import com.intellij.openapi.project.Project
import com.intellij.openapi.vfs.VirtualFile
import com.intellij.psi.tree.IElementType
import ris58h.webcalm.javascript.psi.JavaScriptTokenSets
import ris58h.webcalm.javascript.psi.JavaScriptTypes

class JavaScriptSyntaxHighlighter : SyntaxHighlighterBase() {
    class Factory : SyntaxHighlighterFactory() {
        override fun getSyntaxHighlighter(project: Project?, virtualFile: VirtualFile?): SyntaxHighlighter =
            JavaScriptSyntaxHighlighter()
    }

    override fun getHighlightingLexer(): Lexer = JavaScriptLexer()

    override fun getTokenHighlights(tokenType: IElementType?): Array<TextAttributesKey> {
        //TODO: check if multiline comments work
        if (JavaScriptTokenSets.COMMENTS.contains(tokenType)) {
            return COMMENT
        }
        if (JavaScriptTokenSets.STRINGS.contains(tokenType)) {
            return STRING
        }
        if (JavaScriptTokenSets.BRACES.contains(tokenType)) {
            return BRACES
        }
        if (JavaScriptTokenSets.BRACKETS.contains(tokenType)) {
            return BRACKETS
        }
        if (JavaScriptTokenSets.PARENTHESES.contains(tokenType)) {
            return PARENTHESES
        }
        if (JavaScriptTokenSets.KEYWORDS.contains(tokenType)) {
            return KEYWORD
        }
        if (JavaScriptTokenSets.OPERATIONS.contains(tokenType)) {
            return OPERATION_SIGN
        }
        if (JavaScriptTokenSets.NUMBERS.contains(tokenType)) {
            return NUMBER
        }
        //TODO: other token types
        return when (tokenType) {
            JavaScriptTypes.IDENTIFIER -> IDENTIFIER
            JavaScriptTypes.DOT -> DOT
            JavaScriptTypes.SEMICOLON -> SEMICOLON
            JavaScriptTypes.COMMA -> COMMA
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
        private val IDENTIFIER = arrayOf(DefaultLanguageHighlighterColors.IDENTIFIER)
        private val NUMBER = arrayOf(DefaultLanguageHighlighterColors.NUMBER)
        private val DOT = arrayOf(DefaultLanguageHighlighterColors.DOT)
        private val SEMICOLON = arrayOf(DefaultLanguageHighlighterColors.SEMICOLON)
        private val COMMA = arrayOf(DefaultLanguageHighlighterColors.COMMA)
    }
}