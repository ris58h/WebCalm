package ris58h.webcalm.javascript

import JavaScriptLexer as JavaScriptANTLRLexer
import ris58h.webcalm.antlr.FixedANTLRLexerAdaptor
import ris58h.webcalm.javascript.psi.JavaScriptTypes

class JavaScriptLexer : FixedANTLRLexerAdaptor(JavaScriptLanguage, JavaScriptANTLRLexer(null)) {
    init {
        // Ensure token types are initialized
        JavaScriptTypes
    }
}
