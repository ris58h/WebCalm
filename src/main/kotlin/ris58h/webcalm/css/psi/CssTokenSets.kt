package ris58h.webcalm.css.psi

import com.intellij.psi.tree.TokenSet

object CssTokenSets {
    val WHITESPACE = TokenSet.create(CssTypes.WS)
    val COMMENTS = TokenSet.create(CssTypes.COMMENT)
    val STRINGS = TokenSet.create(CssTypes.STRING)
}