package ris58h.webcalm.css.psi

import com.intellij.psi.tree.TokenSet

object CssTokenSets {
    // TODO: Space token is a part of grammar so it's a hack to make it work.
    val WHITESPACE: TokenSet = TokenSet.EMPTY
    val COMMENTS = TokenSet.create(CssTypes.COMMENT)
    val STRINGS = TokenSet.create(CssTypes.STRING)
    val BRACES = TokenSet.create(CssTypes.OPEN_BRACE, CssTypes.CLOSE_BRACE)
    val BRACKETS = TokenSet.create(CssTypes.OPEN_BRACKET, CssTypes.CLOSE_BRACKET)
    val PARENTHESES = TokenSet.create(CssTypes.OPEN_PAREN, CssTypes.CLOSE_PAREN)
}