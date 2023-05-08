package ris58h.webcalm.javascript

import JavaScriptLexer as JavaScriptANTLRLexer
import ris58h.webcalm.antlr.FixedANTLRLexerAdaptor

class JavaScriptLexer : FixedANTLRLexerAdaptor(JavaScriptLanguage, JavaScriptANTLRLexer(null))