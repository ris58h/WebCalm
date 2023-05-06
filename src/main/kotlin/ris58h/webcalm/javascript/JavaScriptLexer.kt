package ris58h.webcalm.javascript

import org.antlr.intellij.adaptor.lexer.ANTLRLexerAdaptor
import JavaScriptLexer as JavaScriptANTLRLexer

class JavaScriptLexer : ANTLRLexerAdaptor(JavaScriptLanguage, JavaScriptANTLRLexer(null))