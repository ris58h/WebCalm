package ris58h.webcalm.css.psi

import com.intellij.psi.tree.TokenSet

object CssTokenSets {
    val COMMENTS = TokenSet.create(CssTypes.COMMENT)
    val STRINGS = TokenSet.create(CssTypes.STRING)
    val BRACES = TokenSet.create(CssTypes.OPEN_BRACE, CssTypes.CLOSE_BRACE)
    val BRACKETS = TokenSet.create(CssTypes.OPEN_BRACKET, CssTypes.CLOSE_BRACKET)
    val PARENTHESES = TokenSet.create(CssTypes.OPEN_PAREN, CssTypes.CLOSE_PAREN)
    val AT_KEYWORDS = TokenSet.create(
        CssTypes.AT_IMPORT,
        CssTypes.AT_PAGE,
        CssTypes.AT_MEDIA,
        CssTypes.AT_NAMESPACE,
        CssTypes.AT_FONT_FACE,
        CssTypes.AT_SUPPORTS,
        CssTypes.AT_CHARSET,
        CssTypes.AT_KEYFRAMES,
        CssTypes.AT_VIEWPORT,
        CssTypes.AT_COUNTER_STYLE,
        CssTypes.AT_FONT_FEATURE_VALUES,
        CssTypes.AT_KEYWORD,
    )
    val KEYWORDS = TokenSet.create(
        CssTypes.IMPORTANT_KEYWORD,
        CssTypes.AND_KEYWORD,
        CssTypes.OR_KEYWORD,
        CssTypes.FROM_KEYWORD,
        CssTypes.TO_KEYWORD,
    )
    val OPERATIONS = TokenSet.create(
        CssTypes.PLUS_OP,
        CssTypes.MINUS_OP,
        CssTypes.DIVIDE_OP,
        CssTypes.MULTIPLY_OP,
    )
    val FUNCTIONS = TokenSet.create(
        CssTypes.VAR_,
        CssTypes.CALC_,
        CssTypes.URL_,
        CssTypes.FUNCTION_,
    )
}