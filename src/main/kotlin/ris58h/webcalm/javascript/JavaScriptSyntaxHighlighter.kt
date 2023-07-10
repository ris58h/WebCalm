package ris58h.webcalm.javascript

import com.intellij.openapi.editor.colors.TextAttributesKey
import com.intellij.openapi.fileTypes.SyntaxHighlighterBase
import com.intellij.openapi.fileTypes.SyntaxHighlighterFactory
import com.intellij.openapi.project.Project
import com.intellij.openapi.vfs.VirtualFile
import com.intellij.psi.tree.IElementType
import ris58h.webcalm.javascript.psi.JavaScriptTokenSets
import ris58h.webcalm.javascript.psi.JavaScriptTypes

class JavaScriptSyntaxHighlighter : SyntaxHighlighterBase() {
    class Factory : SyntaxHighlighterFactory() {
        override fun getSyntaxHighlighter(project: Project?, virtualFile: VirtualFile?) = JavaScriptSyntaxHighlighter()
    }

    override fun getHighlightingLexer() = JavaScriptLexer()

    override fun getTokenHighlights(tokenType: IElementType): Array<TextAttributesKey> {
        val tokenColor = tokenColor(tokenType)
        return if (tokenColor == null) TextAttributesKey.EMPTY_ARRAY else arrayOf(tokenColor)
    }

    private fun tokenColor(tokenType: IElementType): TextAttributesKey? {
        if (JavaScriptTokenSets.COMMENTS.contains(tokenType)) {
            return JavaScriptHighlighterColors.COMMENT
        }
        if (JavaScriptTokenSets.STRINGS.contains(tokenType)) {
            return JavaScriptHighlighterColors.STRING
        }
        if (JavaScriptTokenSets.BRACES.contains(tokenType)) {
            return JavaScriptHighlighterColors.BRACES
        }
        if (JavaScriptTokenSets.BRACKETS.contains(tokenType)) {
            return JavaScriptHighlighterColors.BRACKETS
        }
        if (JavaScriptTokenSets.PARENTHESES.contains(tokenType)) {
            return JavaScriptHighlighterColors.PARENTHESES
        }
        if (JavaScriptTokenSets.KEYWORDS.contains(tokenType)) {
            return JavaScriptHighlighterColors.KEYWORD
        }
        if (JavaScriptTokenSets.OPERATIONS.contains(tokenType)) {
            return JavaScriptHighlighterColors.OPERATION_SIGN
        }
        if (JavaScriptTokenSets.ASSIGNMENTS.contains(tokenType)) {
            return JavaScriptHighlighterColors.OPERATION_SIGN
        }
        if (JavaScriptTokenSets.NUMBERS.contains(tokenType)) {
            return JavaScriptHighlighterColors.NUMBER
        }
        //TODO: other token types
        return when (tokenType) {
            JavaScriptTypes.NULL_LITERAL -> JavaScriptHighlighterColors.KEYWORD
            JavaScriptTypes.BOOLEAN_LITERAL -> JavaScriptHighlighterColors.KEYWORD
            JavaScriptTypes.IDENTIFIER -> JavaScriptHighlighterColors.IDENTIFIER
            JavaScriptTypes.DOT, JavaScriptTypes.QUESTION_MARK_DOT -> JavaScriptHighlighterColors.DOT
            JavaScriptTypes.SEMICOLON -> JavaScriptHighlighterColors.SEMICOLON
            JavaScriptTypes.COMMA -> JavaScriptHighlighterColors.COMMA
            JavaScriptTypes.REGEX_LITERAL -> JavaScriptHighlighterColors.STRING
            else -> null
        }
    }
}