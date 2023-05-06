package ris58h.webcalm.javascript

import JavaScriptLexer as JavaScriptANTLRLexer
import org.antlr.intellij.adaptor.lexer.ANTLRLexerAdaptor

class JavaScriptLexer : ANTLRLexerAdaptor(JavaScriptLanguage, JavaScriptANTLRLexer(null))