package ris58h.webcalm.antlr

import com.intellij.lang.Language
import org.antlr.intellij.adaptor.lexer.ANTLRLexerAdaptor
import org.antlr.intellij.adaptor.lexer.ANTLRLexerState
import org.antlr.v4.runtime.Lexer

open class FixedANTLRLexerAdaptor(language: Language, lexer: Lexer) : ANTLRLexerAdaptor(language, lexer) {
    // TODO: it's a hack to prevent IndexOutOfBounds exception. See https://github.com/antlr/antlr4-intellij-adaptor/issues/31
    override fun toLexerState(state: Int): ANTLRLexerState {
        if (state == 0) {
            return initialState
        }
        return super.toLexerState(state)
    }
}