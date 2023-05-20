package ris58h.webcalm.javascript.psi

import com.intellij.extapi.psi.ASTWrapperPsiElement
import com.intellij.lang.ASTNode
import com.intellij.psi.PsiElement
import com.intellij.psi.tree.IFileElementType
import com.intellij.psi.tree.TokenSet
import org.antlr.intellij.adaptor.lexer.PSIElementTypeFactory
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
    val DOT = TOKENS[JavaScriptLexer.Dot]!!
    val SEMICOLON = TOKENS[JavaScriptLexer.SemiColon]!!
    val COMMA = TOKENS[JavaScriptLexer.Comma]!!
    val COLON = TOKENS[JavaScriptLexer.Colon]!!
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
    val UNSIGNED_RIGHT_SHIFT_OP = TOKENS[JavaScriptLexer.RightShiftLogical]!!
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
    val ASSIGN = TOKENS[JavaScriptLexer.Assign]!!
    val MULTIPLY_ASSIGN = TOKENS[JavaScriptLexer.MultiplyAssign]!!
    val DIVIDE_ASSIGN = TOKENS[JavaScriptLexer.DivideAssign]!!
    val MODULUS_ASSIGN = TOKENS[JavaScriptLexer.ModulusAssign]!!
    val PLUS_ASSIGN = TOKENS[JavaScriptLexer.PlusAssign]!!
    val MINUS_ASSIGN = TOKENS[JavaScriptLexer.MinusAssign]!!
    val LEFT_SHIFT_ASSIGN = TOKENS[JavaScriptLexer.LeftShiftArithmeticAssign]!!
    val RIGHT_SHIFT_ASSIGN = TOKENS[JavaScriptLexer.RightShiftArithmeticAssign]!!
    val UNSIGNED_RIGHT_SHIFT_ASSIGN = TOKENS[JavaScriptLexer.RightShiftLogicalAssign]!!
    val BIT_AND_ASSIGN = TOKENS[JavaScriptLexer.BitAndAssign]!!
    val BIT_XOR_ASSIGN = TOKENS[JavaScriptLexer.BitXorAssign]!!
    val BIT_OR_ASSIGN = TOKENS[JavaScriptLexer.BitOrAssign]!!
    val POWER_ASSIGN = TOKENS[JavaScriptLexer.PowerAssign]!!
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
        private val FUNCTION_BODY = RULES[JavaScriptParser.RULE_functionBody]
        private val BLOCK = RULES[JavaScriptParser.RULE_block]
        private val VARIABLE_STATEMENT = RULES[JavaScriptParser.RULE_variableStatement]
        private val VARIABLE_DECLARATION_LIST = RULES[JavaScriptParser.RULE_variableDeclarationList]
        private val VARIABLE_DECLARATION = RULES[JavaScriptParser.RULE_variableDeclaration]
        private val EXPORT_STATEMENT = RULES[JavaScriptParser.RULE_exportStatement]
        private val EXPRESSION_STATEMENT = RULES[JavaScriptParser.RULE_expressionStatement]
        private val RETURN_STATEMENT = RULES[JavaScriptParser.RULE_returnStatement]
        private val IF_STATEMENT = RULES[JavaScriptParser.RULE_ifStatement]
        private val ITERATION_STATEMENT = RULES[JavaScriptParser.RULE_iterationStatement]
        private val PARAMETERS = RULES[JavaScriptParser.RULE_formalParameterList]
        private val PARAMETER = RULES[JavaScriptParser.RULE_formalParameterArg]
        private val EXPRESSION_SEQUENCE = RULES[JavaScriptParser.RULE_expressionSequence]
        private val EXPRESSION = RULES[JavaScriptParser.RULE_singleExpression]
        private val ARGUMENTS = RULES[JavaScriptParser.RULE_arguments]
        private val ARGUMENT = RULES[JavaScriptParser.RULE_argument]
        private val OBJECT = RULES[JavaScriptParser.RULE_objectLiteral]
        private val ARRAY = RULES[JavaScriptParser.RULE_arrayLiteral]
        private val PROPERTY_ASSIGNMENT = RULES[JavaScriptParser.RULE_propertyAssignment]
        private val TEMPLATE_STRING = RULES[JavaScriptParser.RULE_templateStringLiteral]
        private val ANONYMOUS_FUNCTION = RULES[JavaScriptParser.RULE_anonymousFunction]
        private val CLASS_DECLARATION = RULES[JavaScriptParser.RULE_classDeclaration]

        fun createElement(node: ASTNode): PsiElement {
            return when (node.elementType) {
                IDENTIFIER -> JavaScriptIdentifier(node)
                FUNCTION_DECLARATION -> JavaScriptFunctionDeclaration(node)
                FUNCTION_BODY -> JavaScriptFunctionBody(node)
                BLOCK -> JavaScriptBlock(node)
                VARIABLE_STATEMENT -> JavaScriptVariableStatement(node)
                VARIABLE_DECLARATION_LIST -> JavaScriptVariableDeclarationList(node)
                VARIABLE_DECLARATION -> JavaScriptVariableDeclaration(node)
                EXPORT_STATEMENT -> JavaScriptExportStatement(node)
                EXPRESSION_STATEMENT -> JavaScriptExpressionStatement(node)
                RETURN_STATEMENT -> JavaScriptReturnStatement(node)
                IF_STATEMENT -> JavaScriptIfStatement(node)
                ITERATION_STATEMENT -> JavaScriptIterationStatement(node)
                PARAMETERS -> JavaScriptParameters(node)
                PARAMETER -> JavaScriptParameter(node)
                EXPRESSION_SEQUENCE -> JavaScriptExpressionSequence(node)
                EXPRESSION -> createExpression(node)
                ARGUMENTS -> JavaScriptArguments(node)
                ARGUMENT -> JavaScriptArgument(node)
                OBJECT -> JavaScriptObject(node)
                ARRAY -> JavaScriptArray(node)
                PROPERTY_ASSIGNMENT -> JavaScriptPropertyAssignment(node)
                TEMPLATE_STRING -> JavaScriptTemplateString(node)
                ANONYMOUS_FUNCTION -> JavaScriptAnonymousFunction(node)
                CLASS_DECLARATION -> JavaScriptClassDeclaration(node)
                //TODO: other rules
                else -> ASTWrapperPsiElement(node)
            }
        }

        private fun createExpression(node: ASTNode): PsiElement {
            val children = node.getChildren(TokenSet.andNot(TokenSet.ANY, TokenSet.WHITE_SPACE))
            if (children.size == 1 && children[0].elementType == IDENTIFIER) {
                return JavaScriptIdentifierExpression(node)
            }
            if (children.size == 2 && children[0].elementType == EXPRESSION && children[1].elementType == ARGUMENTS) {
                return JavaScriptCallExpression(node)
            }
            if (children.size == 3 && JavaScriptTokenSets.ASSIGNMENTS.contains(children[1].elementType)) {
                return JavaScriptAssignmentExpression(node)
            }
            return JavaScriptExpression.Other(node)
        }
    }
}