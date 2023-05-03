package ris58h.webcalm.javascript.psi

import com.intellij.lang.ASTNode
import com.intellij.psi.PsiElement
import com.intellij.psi.tree.IFileElementType
import org.antlr.intellij.adaptor.lexer.PSIElementTypeFactory
import org.antlr.intellij.adaptor.psi.ANTLRPsiNode
import ris58h.webcalm.javascript.JavaScriptLanguage

object JavaScriptTypes {
    init {
        PSIElementTypeFactory.defineLanguageIElementTypes(
            JavaScriptLanguage, JavaScriptLexer.tokenNames, JavaScriptParser.ruleNames
        )
    }

    val FILE = IFileElementType(JavaScriptLanguage)

    private val TOKENS = PSIElementTypeFactory.getTokenIElementTypes(JavaScriptLanguage)
    val WS = TOKENS[JavaScriptLexer.WhiteSpaces]!!
    val EOL = TOKENS[JavaScriptLexer.LineTerminator]!!
    val LINE_COMMENT = TOKENS[JavaScriptLexer.SingleLineComment]!!
    val MULTILINE_COMMENT = TOKENS[JavaScriptLexer.MultiLineComment]!!
    val STRING = TOKENS[JavaScriptLexer.StringLiteral]!!
    val BACKTICK = TOKENS[JavaScriptLexer.BackTick]!!
    val TEMPLATE_STRING_ATOM = TOKENS[JavaScriptLexer.TemplateStringAtom]!!
    val TEMPLATE_STRING_START_EXPR = TOKENS[JavaScriptLexer.TemplateStringStartExpression]!!
    val TEMPLATE_STRING_END_EXPR = TOKENS[JavaScriptLexer.TemplateCloseBrace]!!
    val OPEN_BRACE = TOKENS[JavaScriptLexer.OpenBrace]!!
    val CLOSE_BRACE = TOKENS[JavaScriptLexer.CloseBrace]!!
    val OPEN_BRACKET = TOKENS[JavaScriptLexer.OpenBracket]!!
    val CLOSE_BRACKET = TOKENS[JavaScriptLexer.CloseBracket]!!
    val OPEN_PAREN = TOKENS[JavaScriptLexer.OpenParen]!!
    val CLOSE_PAREN = TOKENS[JavaScriptLexer.CloseParen]!!
    val PLUS_PLUS_OP = TOKENS[JavaScriptLexer.PlusPlus]!!
    val MENUS_MINUS_OP = TOKENS[JavaScriptLexer.MinusMinus]!!
    val PLUS_OP = TOKENS[JavaScriptLexer.Plus]!!
    val MINUS_OP = TOKENS[JavaScriptLexer.Minus]!!
    val BIT_NOT_OP = TOKENS[JavaScriptLexer.BitNot]!!
    val NOT_OP = TOKENS[JavaScriptLexer.Not]!!
    val MULTIPLY_OP = TOKENS[JavaScriptLexer.Multiply]!!
    val DIVIDE_OP = TOKENS[JavaScriptLexer.Divide]!!
    val MODULUS_OP = TOKENS[JavaScriptLexer.Modulus]!!
    val POWER_OP = TOKENS[JavaScriptLexer.Power]!!
    val RIGHT_SHIFT_OP = TOKENS[JavaScriptLexer.RightShiftArithmetic]!!
    val LEFT_SHIFT_OP = TOKENS[JavaScriptLexer.LeftShiftArithmetic]!!
    val UNSIGNED_RIGHT_SHIFT = TOKENS[JavaScriptLexer.RightShiftLogical]!!
    val LESS_THAN_OP = TOKENS[JavaScriptLexer.LessThan]!!
    val MORE_THAN_OP = TOKENS[JavaScriptLexer.MoreThan]!!
    val LTE_OP = TOKENS[JavaScriptLexer.LessThanEquals]!!
    val GTE_OP = TOKENS[JavaScriptLexer.GreaterThanEquals]!!
    val EQUALS_OP = TOKENS[JavaScriptLexer.Equals_]!!
    val NOT_EQUALS_OP = TOKENS[JavaScriptLexer.NotEquals]!!
    val STRICT_EQUALS_OP = TOKENS[JavaScriptLexer.IdentityEquals]!!
    val STRICT_NOT_EQUALS_OP = TOKENS[JavaScriptLexer.IdentityNotEquals]!!
    val BIT_AND_OP = TOKENS[JavaScriptLexer.BitAnd]!!
    val BIT_XOR_OP = TOKENS[JavaScriptLexer.BitXOr]!!
    val BIT_OR_OP = TOKENS[JavaScriptLexer.BitOr]!!
    val AND_OP = TOKENS[JavaScriptLexer.And]!!
    val OR_OP = TOKENS[JavaScriptLexer.Or]!!
//    val ASSIGN = TOKENS[JavaScriptLexer.Assign]!!
//    val ASSIGN = TOKENS[JavaScriptLexer.MultiplyAssign]!!
//    val ASSIGN = TOKENS[JavaScriptLexer.DivideAssign]!!
//    val ASSIGN = TOKENS[JavaScriptLexer.ModulusAssign]!!
//    val ASSIGN = TOKENS[JavaScriptLexer.PlusAssign]!!
//    val ASSIGN = TOKENS[JavaScriptLexer.MinusAssign]!!
//    val ASSIGN = TOKENS[JavaScriptLexer.LeftShiftArithmeticAssign]!!
//    val ASSIGN = TOKENS[JavaScriptLexer.RightShiftArithmeticAssign]!!
//    val ASSIGN = TOKENS[JavaScriptLexer.RightShiftLogicalAssign]!!
//    val ASSIGN = TOKENS[JavaScriptLexer.BitAndAssign]!!
//    val ASSIGN = TOKENS[JavaScriptLexer.BitXorAssign]!!
//    val ASSIGN = TOKENS[JavaScriptLexer.BitOrAssign]!!
//    val ASSIGN = TOKENS[JavaScriptLexer.PowerAssign]!!
    val ARROW = TOKENS[JavaScriptLexer.ARROW]!!
    val NULL_LITERAL = TOKENS[JavaScriptLexer.NullLiteral]!!
    val BOOLEAN_LITERAL = TOKENS[JavaScriptLexer.BooleanLiteral]!!
    val DEC_INT_LITERAL = TOKENS[JavaScriptLexer.DecimalLiteral]!!
    val HEX_INT_LITERAL = TOKENS[JavaScriptLexer.HexIntegerLiteral]!!
    val OCT_INT_LITERAL = TOKENS[JavaScriptLexer.OctalIntegerLiteral]!!
    val OCT_INT_LITERAL2 = TOKENS[JavaScriptLexer.OctalIntegerLiteral2]!!
    val BIN_INT_LITERAL = TOKENS[JavaScriptLexer.BinaryIntegerLiteral]!!
    val BIG_HEX_INT_LITERAL = TOKENS[JavaScriptLexer.BigHexIntegerLiteral]!!
    val BIG_OCT_INT_LITERAL = TOKENS[JavaScriptLexer.BigOctalIntegerLiteral]!!
    val BIG_BIN_INT_LITERAL = TOKENS[JavaScriptLexer.BigBinaryIntegerLiteral]!!
    val BIG_DEC_INT_LITERAL = TOKENS[JavaScriptLexer.BigDecimalIntegerLiteral]!!
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

    object Factory {
        private val RULES = PSIElementTypeFactory.getRuleIElementTypes(JavaScriptLanguage)
        private val IDENTIFIER = RULES[JavaScriptParser.RULE_identifier]
        private val FUNCTION_DECLARATION = RULES[JavaScriptParser.RULE_functionDeclaration]
        private val VARIABLE_DECLARATION = RULES[JavaScriptParser.RULE_variableDeclaration]
        private val PARAMETERS = RULES[JavaScriptParser.RULE_formalParameterList]
        private val PARAMETER = RULES[JavaScriptParser.RULE_formalParameterArg]
        private val ASSIGNABLE = RULES[JavaScriptParser.RULE_assignable]
        private val EXPRESSION = RULES[JavaScriptParser.RULE_singleExpression]
        private val ARGUMENTS = RULES[JavaScriptParser.RULE_arguments]
        private val ARGUMENT = RULES[JavaScriptParser.RULE_argument]

        fun createElement(node: ASTNode): PsiElement {
            return when (node.elementType) {
                IDENTIFIER -> JavaScriptIdentifier(node)
                FUNCTION_DECLARATION -> JavaScriptFunctionDeclaration(node)
                VARIABLE_DECLARATION -> JavaScriptVariableDeclaration(node)
                PARAMETERS -> JavaScriptParameters(node)
                PARAMETER -> JavaScriptParameter(node)
                ASSIGNABLE -> JavaScriptAssignable(node)
                EXPRESSION -> createExpression(node)
                ARGUMENTS -> JavaScriptArguments(node)
                ARGUMENT -> JavaScriptArgument(node)
                //TODO: other rules
                else -> ANTLRPsiNode(node)
            }
        }

        private fun createExpression(node: ASTNode): PsiElement {
            val children = node.getChildren(null)
            if (children.size == 1 && children[0].elementType == IDENTIFIER) {
                return JavaScriptIdentifierExpression(node)
            }
            if (children.size == 2 && children[0].elementType == EXPRESSION && children[1].elementType == ARGUMENTS) {
                return JavaScriptCallExpression(node)
            }
            return JavaScriptExpression(node)
        }
    }
}