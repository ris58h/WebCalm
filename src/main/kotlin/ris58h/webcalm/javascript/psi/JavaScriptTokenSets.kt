package ris58h.webcalm.javascript.psi

import com.intellij.psi.tree.TokenSet

object JavaScriptTokenSets {
    val WHITESPACE: TokenSet = TokenSet.create(JavaScriptTypes.WS, JavaScriptTypes.EOL)
    val COMMENTS: TokenSet = TokenSet.create(JavaScriptTypes.LINE_COMMENT, JavaScriptTypes.MULTILINE_COMMENT)
    val STRINGS: TokenSet = TokenSet.create(JavaScriptTypes.STRING)
    val BRACES: TokenSet = TokenSet.create(JavaScriptTypes.OPEN_BRACE, JavaScriptTypes.CLOSE_BRACE)
    val BRACKETS: TokenSet = TokenSet.create(JavaScriptTypes.OPEN_BRACKET, JavaScriptTypes.CLOSE_BRACKET)
    val PARENTHESES: TokenSet = TokenSet.create(JavaScriptTypes.OPEN_PAREN, JavaScriptTypes.CLOSE_PAREN)
    val KEYWORDS: TokenSet = TokenSet.create(
        JavaScriptTypes.BREAK_KEYWORD,
        JavaScriptTypes.DO_KEYWORD,
        JavaScriptTypes.INSTANCEOF_KEYWORD,
        JavaScriptTypes.TYPEOF_KEYWORD,
        JavaScriptTypes.CASE_KEYWORD,
        JavaScriptTypes.ELSE_KEYWORD,
        JavaScriptTypes.NEW_KEYWORD,
        JavaScriptTypes.VAR_KEYWORD,
        JavaScriptTypes.CATCH_KEYWORD,
        JavaScriptTypes.FINALLY_KEYWORD,
        JavaScriptTypes.RETURN_KEYWORD,
        JavaScriptTypes.VOID_KEYWORD,
        JavaScriptTypes.CONTINUE_KEYWORD,
        JavaScriptTypes.FOR_KEYWORD,
        JavaScriptTypes.SWITCH_KEYWORD,
        JavaScriptTypes.WHILE_KEYWORD,
        JavaScriptTypes.DEBUGGER_KEYWORD,
        JavaScriptTypes.FUNCTION_KEYWORD,
        JavaScriptTypes.THIS_KEYWORD,
        JavaScriptTypes.WiTH_KEYWORD,
        JavaScriptTypes.DEFAULT_KEYWORD,
        JavaScriptTypes.IF_KEYWORD,
        JavaScriptTypes.THROW_KEYWORD,
        JavaScriptTypes.DELETE_KEYWORD,
        JavaScriptTypes.IN_KEYWORD,
        JavaScriptTypes.TRY_KEYWORD,
        JavaScriptTypes.AS_KEYWORD,
        JavaScriptTypes.FROM_KEYWORD,
        JavaScriptTypes.CLASS_KEYWORD,
        JavaScriptTypes.ENUM_KEYWORD,
        JavaScriptTypes.EXTENDS_KEYWORD,
        JavaScriptTypes.SUPER_KEYWORD,
        JavaScriptTypes.CONST_KEYWORD,
        JavaScriptTypes.EXPORT_KEYWORD,
        JavaScriptTypes.IMPORT_KEYWORD,
        JavaScriptTypes.ASYNC_KEYWORD,
        JavaScriptTypes.AWAIT_KEYWORD,
        JavaScriptTypes.YIELD_KEYWORD,
        JavaScriptTypes.IMPLEMENTS_KEYWORD,
        JavaScriptTypes.STRICT_LET_KEYWORD,
        JavaScriptTypes.NON_STRICT_LET_KEYWORD,
        JavaScriptTypes.PRIVATE_KEYWORD,
        JavaScriptTypes.PUBLIC_KEYWORD,
        JavaScriptTypes.INTERFACE_KEYWORD,
        JavaScriptTypes.PACKAGE_KEYWORD,
        JavaScriptTypes.PROTECTED_KEYWORD,
        JavaScriptTypes.STATIC_KEYWORD,
    )
}
