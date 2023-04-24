package ris58h.webcalm.javascript.psi

import com.intellij.psi.tree.IFileElementType
import org.antlr.intellij.adaptor.lexer.PSIElementTypeFactory
import ris58h.webcalm.javascript.JavaScriptLanguage

object JavaScriptTypes {
    init {
        PSIElementTypeFactory.defineLanguageIElementTypes(
            JavaScriptLanguage, JavaScriptParser.tokenNames, JavaScriptParser.ruleNames
        )
    }
    val FILE = IFileElementType(JavaScriptLanguage)
    private val TOKENS = PSIElementTypeFactory.getTokenIElementTypes(JavaScriptLanguage)
    val WS = TOKENS[JavaScriptLexer.WhiteSpaces]!!
    val EOL = TOKENS[JavaScriptLexer.LineTerminator]!!
    val LINE_COMMENT = TOKENS[JavaScriptLexer.SingleLineComment]!!
    val MULTILINE_COMMENT = TOKENS[JavaScriptLexer.MultiLineComment]!!
    val STRING = TOKENS[JavaScriptLexer.StringLiteral]!!
    val OPEN_BRACE = TOKENS[JavaScriptLexer.OpenBrace]!!
    val CLOSE_BRACE = TOKENS[JavaScriptLexer.CloseBrace]!!
    val OPEN_BRACKET = TOKENS[JavaScriptLexer.OpenBracket]!!
    val CLOSE_BRACKET = TOKENS[JavaScriptLexer.CloseBracket]!!
    val OPEN_PAREN = TOKENS[JavaScriptLexer.OpenParen]!!
    val CLOSE_PAREN = TOKENS[JavaScriptLexer.CloseParen]!!
    val BREAK_KEYWORD = TOKENS[JavaScriptLexer.Break]!!
    val DO_KEYWORD = TOKENS[JavaScriptLexer.Do]!!
    val INSTANCEOF_KEYWORD = TOKENS[JavaScriptLexer.Instanceof]!!
    val TYPEOF_KEYWORD = TOKENS[JavaScriptLexer.Typeof]!!
    val CASE_KEYWORD = TOKENS[JavaScriptLexer.Case]!!
    val ELSE_KEYWORD = TOKENS[JavaScriptLexer.Else]!!
    val NEW_KEYWORD = TOKENS[JavaScriptLexer.New]!!
    val VAR_KEYWORD = TOKENS[JavaScriptLexer.Var]!!
    val CATCH_KEYWORD = TOKENS[JavaScriptLexer.Catch]!!
    val FINALLY_KEYWORD = TOKENS[JavaScriptLexer.Finally]!!
    val RETURN_KEYWORD = TOKENS[JavaScriptLexer.Return]!!
    val VOID_KEYWORD = TOKENS[JavaScriptLexer.Void]!!
    val CONTINUE_KEYWORD = TOKENS[JavaScriptLexer.Continue]!!
    val FOR_KEYWORD = TOKENS[JavaScriptLexer.For]!!
    val SWITCH_KEYWORD = TOKENS[JavaScriptLexer.Switch]!!
    val WHILE_KEYWORD = TOKENS[JavaScriptLexer.While]!!
    val DEBUGGER_KEYWORD = TOKENS[JavaScriptLexer.Debugger]!!
    val FUNCTION_KEYWORD = TOKENS[JavaScriptLexer.Function_]!!
    val THIS_KEYWORD = TOKENS[JavaScriptLexer.This]!!
    val WiTH_KEYWORD = TOKENS[JavaScriptLexer.With]!!
    val DEFAULT_KEYWORD = TOKENS[JavaScriptLexer.Default]!!
    val IF_KEYWORD = TOKENS[JavaScriptLexer.If]!!
    val THROW_KEYWORD = TOKENS[JavaScriptLexer.Throw]!!
    val DELETE_KEYWORD = TOKENS[JavaScriptLexer.Delete]!!
    val IN_KEYWORD = TOKENS[JavaScriptLexer.In]!!
    val TRY_KEYWORD = TOKENS[JavaScriptLexer.Try]!!
    val AS_KEYWORD = TOKENS[JavaScriptLexer.As]!!
    val FROM_KEYWORD = TOKENS[JavaScriptLexer.From]!!
    val CLASS_KEYWORD = TOKENS[JavaScriptLexer.Class]!!
    val ENUM_KEYWORD = TOKENS[JavaScriptLexer.Enum]!!
    val EXTENDS_KEYWORD = TOKENS[JavaScriptLexer.Extends]!!
    val SUPER_KEYWORD = TOKENS[JavaScriptLexer.Super]!!
    val CONST_KEYWORD = TOKENS[JavaScriptLexer.Const]!!
    val EXPORT_KEYWORD = TOKENS[JavaScriptLexer.Export]!!
    val IMPORT_KEYWORD = TOKENS[JavaScriptLexer.Import]!!
    val ASYNC_KEYWORD = TOKENS[JavaScriptLexer.Async]!!
    val AWAIT_KEYWORD = TOKENS[JavaScriptLexer.Await]!!
    val YIELD_KEYWORD = TOKENS[JavaScriptLexer.Yield]!!
    val IMPLEMENTS_KEYWORD = TOKENS[JavaScriptLexer.Implements]!!
    val STRICT_LET_KEYWORD = TOKENS[JavaScriptLexer.StrictLet]!!
    val NON_STRICT_LET_KEYWORD = TOKENS[JavaScriptLexer.NonStrictLet]!!
    val PRIVATE_KEYWORD = TOKENS[JavaScriptLexer.Private]!!
    val PUBLIC_KEYWORD = TOKENS[JavaScriptLexer.Public]!!
    val INTERFACE_KEYWORD = TOKENS[JavaScriptLexer.Interface]!!
    val PACKAGE_KEYWORD = TOKENS[JavaScriptLexer.Package]!!
    val PROTECTED_KEYWORD = TOKENS[JavaScriptLexer.Protected]!!
    val STATIC_KEYWORD = TOKENS[JavaScriptLexer.Static]!!
    val IDENTIFIER = TOKENS[JavaScriptLexer.Identifier]!!
}