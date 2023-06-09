package ris58h.webcalm.javascript.psi

import com.intellij.psi.tree.TokenSet

object JavaScriptTokenSets {
    val WHITESPACE = TokenSet.create(JavaScriptTypes.WS, JavaScriptTypes.EOL)
    val COMMENTS = TokenSet.create(JavaScriptTypes.LINE_COMMENT, JavaScriptTypes.MULTILINE_COMMENT)
    val STRINGS = TokenSet.create(
        JavaScriptTypes.STRING,
        JavaScriptTypes.BACKTICK,
        JavaScriptTypes.TEMPLATE_STRING_ATOM,
        JavaScriptTypes.TEMPLATE_STRING_START_EXPR,
        JavaScriptTypes.TEMPLATE_STRING_END_EXPR,
    )
    val BRACES = TokenSet.create(JavaScriptTypes.OPEN_BRACE, JavaScriptTypes.CLOSE_BRACE)
    val BRACKETS = TokenSet.create(JavaScriptTypes.OPEN_BRACKET, JavaScriptTypes.CLOSE_BRACKET)
    val PARENTHESES = TokenSet.create(JavaScriptTypes.OPEN_PAREN, JavaScriptTypes.CLOSE_PAREN)
    val KEYWORDS = TokenSet.create(
        JavaScriptTypes.ARROW,
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
    val OPERATIONS = TokenSet.create(
        JavaScriptTypes.PLUS_PLUS_OP,
        JavaScriptTypes.MINUS_MINUS_OP,
        JavaScriptTypes.PLUS_OP,
        JavaScriptTypes.MINUS_OP,
        JavaScriptTypes.BIT_NOT_OP,
        JavaScriptTypes.NOT_OP,
        JavaScriptTypes.MULTIPLY_OP,
        JavaScriptTypes.DIVIDE_OP,
        JavaScriptTypes.MODULUS_OP,
        JavaScriptTypes.POWER_OP,
        JavaScriptTypes.RIGHT_SHIFT_OP,
        JavaScriptTypes.LEFT_SHIFT_OP,
        JavaScriptTypes.UNSIGNED_RIGHT_SHIFT_OP,
        JavaScriptTypes.LESS_THAN_OP,
        JavaScriptTypes.MORE_THAN_OP,
        JavaScriptTypes.LTE_OP,
        JavaScriptTypes.GTE_OP,
        JavaScriptTypes.EQUALS_OP,
        JavaScriptTypes.NOT_EQUALS_OP,
        JavaScriptTypes.STRICT_EQUALS_OP,
        JavaScriptTypes.STRICT_NOT_EQUALS_OP,
        JavaScriptTypes.BIT_AND_OP,
        JavaScriptTypes.BIT_XOR_OP,
        JavaScriptTypes.BIT_OR_OP,
        JavaScriptTypes.AND_OP,
        JavaScriptTypes.OR_OP,
    )
    val ASSIGNMENTS = TokenSet.create(
        JavaScriptTypes.ASSIGN,
        JavaScriptTypes.MULTIPLY_ASSIGN,
        JavaScriptTypes.DIVIDE_ASSIGN,
        JavaScriptTypes.MODULUS_ASSIGN,
        JavaScriptTypes.PLUS_ASSIGN,
        JavaScriptTypes.MINUS_ASSIGN,
        JavaScriptTypes.LEFT_SHIFT_ASSIGN,
        JavaScriptTypes.RIGHT_SHIFT_ASSIGN,
        JavaScriptTypes.UNSIGNED_RIGHT_SHIFT_ASSIGN,
        JavaScriptTypes.BIT_AND_ASSIGN,
        JavaScriptTypes.BIT_XOR_ASSIGN,
        JavaScriptTypes.BIT_OR_ASSIGN,
        JavaScriptTypes.POWER_ASSIGN,
    )
    val NUMBERS = TokenSet.create(
        JavaScriptTypes.DEC_INT_LITERAL,
        JavaScriptTypes.HEX_INT_LITERAL,
        JavaScriptTypes.OCT_INT_LITERAL,
        JavaScriptTypes.OCT_INT_LITERAL2,
        JavaScriptTypes.BIN_INT_LITERAL,
        JavaScriptTypes.BIG_HEX_INT_LITERAL,
        JavaScriptTypes.BIG_OCT_INT_LITERAL,
        JavaScriptTypes.BIG_BIN_INT_LITERAL,
        JavaScriptTypes.BIG_DEC_INT_LITERAL,
    )
}
