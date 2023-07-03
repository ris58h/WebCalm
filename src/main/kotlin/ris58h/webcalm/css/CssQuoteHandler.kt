package ris58h.webcalm.css

import com.intellij.codeInsight.editorActions.QuoteHandler
import com.intellij.openapi.editor.Editor
import com.intellij.openapi.editor.highlighter.HighlighterIterator
import ris58h.webcalm.css.psi.CssTypes

class CssQuoteHandler : QuoteHandler {
    override fun isClosingQuote(iterator: HighlighterIterator, offset: Int): Boolean {
        if (iterator.tokenType == CssTypes.STRING) {
            val start = iterator.start
            val end = iterator.end
            return end - start >= 1 && offset == end - 1
        }

        return false
    }

    override fun isOpeningQuote(iterator: HighlighterIterator, offset: Int): Boolean {
        // The lexer fails early so single (opening) quote token is considered as an error token.
        if (iterator.tokenType == CssTypes.UNEXPECTED_CHARACTER) {
            val char = iterator.document.charsSequence[iterator.start]
            if (char == '"' || char == '\'') {
                return true
            }
        }
        return false
    }

    override fun hasNonClosedLiteral(editor: Editor, iterator: HighlighterIterator, offset: Int) = true

    override fun isInsideLiteral(iterator: HighlighterIterator) = false
}