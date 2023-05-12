package ris58h.webcalm.css

import css3Lexer
import ris58h.webcalm.antlr.FixedANTLRLexerAdaptor

class CssLexer : FixedANTLRLexerAdaptor(CssLanguage, css3Lexer(null))