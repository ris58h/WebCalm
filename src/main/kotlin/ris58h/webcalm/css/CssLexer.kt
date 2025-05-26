package ris58h.webcalm.css

import css3Lexer
import ris58h.webcalm.antlr.FixedANTLRLexerAdaptor
import ris58h.webcalm.css.psi.CssTypes

class CssLexer : FixedANTLRLexerAdaptor(CssLanguage, css3Lexer(null)) {
    init {
        // Ensure token types are initialized
        CssTypes
    }
}
