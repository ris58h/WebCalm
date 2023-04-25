package ris58h.webcalm.css

import com.intellij.lexer.Lexer
import com.intellij.openapi.editor.DefaultLanguageHighlighterColors
import com.intellij.openapi.editor.colors.TextAttributesKey
import com.intellij.openapi.fileTypes.SyntaxHighlighter
import com.intellij.openapi.fileTypes.SyntaxHighlighterBase
import com.intellij.openapi.fileTypes.SyntaxHighlighterFactory
import com.intellij.openapi.project.Project
import com.intellij.openapi.vfs.VirtualFile
import com.intellij.psi.tree.IElementType
import css3Lexer
import org.antlr.intellij.adaptor.lexer.ANTLRLexerAdaptor
import ris58h.webcalm.css.psi.CssTokenSets

class CssSyntaxHighlighter : SyntaxHighlighterBase() {
    class Factory : SyntaxHighlighterFactory() {
        override fun getSyntaxHighlighter(project: Project?, virtualFile: VirtualFile?): SyntaxHighlighter =
            CssSyntaxHighlighter()
    }

    override fun getHighlightingLexer(): Lexer {
        return ANTLRLexerAdaptor(CssLanguage, css3Lexer(null))
    }

    override fun getTokenHighlights(tokenType: IElementType?): Array<TextAttributesKey> {
        if (CssTokenSets.COMMENTS.contains(tokenType)) {
            return COMMENT
        }
        if (CssTokenSets.STRINGS.contains(tokenType)) {
            return STRING
        }
//        if (CssTokenSets.BRACES.contains(tokenType)) {
//            return BRACES
//        }
//        if (CssTokenSets.BRACKETS.contains(tokenType)) {
//            return BRACKETS
//        }
//        if (CssTokenSets.PARENTHESES.contains(tokenType)) {
//            return PARENTHESES
//        }
//        if (CssTokenSets.KEYWORDS.contains(tokenType)) {
//            return KEYWORD
//        }
//        if (CssTokenSets.OPERATIONS.contains(tokenType)) {
//            return OPERATION_SIGN
//        }
//        if (CssTokenSets.NUMBERS.contains(tokenType)) {
//            return NUMBER
//        }
//        if (CssTypes.IDENTIFIER == tokenType) {
//            return IDENTIFIER
//        }
        //TODO: other token types
        return TextAttributesKey.EMPTY_ARRAY
    }

    companion object {
        private val COMMENT = arrayOf(DefaultLanguageHighlighterColors.LINE_COMMENT)
        private val STRING = arrayOf(DefaultLanguageHighlighterColors.STRING)
//        private val BRACES = arrayOf(DefaultLanguageHighlighterColors.BRACES)
//        private val BRACKETS = arrayOf(DefaultLanguageHighlighterColors.BRACKETS)
//        private val PARENTHESES = arrayOf(DefaultLanguageHighlighterColors.PARENTHESES)
//        private val KEYWORD = arrayOf(DefaultLanguageHighlighterColors.KEYWORD)
//        private val OPERATION_SIGN = arrayOf(DefaultLanguageHighlighterColors.OPERATION_SIGN)
//        private val IDENTIFIER = arrayOf(DefaultLanguageHighlighterColors.IDENTIFIER)
//        private val NUMBER = arrayOf(DefaultLanguageHighlighterColors.NUMBER)
    }
}