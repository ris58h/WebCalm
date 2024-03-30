package ris58h.webcalm.javascript.psi

import JavaScriptLexer
import JavaScriptParser
import com.intellij.extapi.psi.ASTWrapperPsiElement
import com.intellij.lang.ASTNode
import com.intellij.openapi.diagnostic.Logger
import com.intellij.psi.PsiElement
import com.intellij.psi.tree.IElementType
import com.intellij.psi.tree.IFileElementType
import org.antlr.intellij.adaptor.lexer.PSIElementTypeFactory
import org.antlr.intellij.adaptor.lexer.RuleIElementType
import ris58h.webcalm.antlr.LabeledRuleIElementType
import ris58h.webcalm.javascript.JavaScriptLanguage

object JavaScriptTypes {
    init {
        PSIElementTypeFactory.defineLanguageIElementTypes(
            JavaScriptLanguage, JavaScriptLexer.tokenNames, JavaScriptParser.ruleNames
        )
    }

    val FILE = IFileElementType(JavaScriptLanguage)

    private val TOKENS = PSIElementTypeFactory.getTokenIElementTypes(JavaScriptLanguage)
    val UNEXPECTED_CHARACTER = TOKENS[JavaScriptLexer.UnexpectedCharacter]!!
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
    val QUESTION_MARK_DOT = TOKENS[JavaScriptLexer.QuestionMarkDot]!!
    val SEMICOLON = TOKENS[JavaScriptLexer.SemiColon]!!
    val COMMA = TOKENS[JavaScriptLexer.Comma]!!
    val QUESTION_MARK = TOKENS[JavaScriptLexer.QuestionMark]!!
    val COLON = TOKENS[JavaScriptLexer.Colon]!!
    val PLUS_PLUS_OP = TOKENS[JavaScriptLexer.PlusPlus]!!
    val MINUS_MINUS_OP = TOKENS[JavaScriptLexer.MinusMinus]!!
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
    val REGEX_LITERAL = TOKENS[JavaScriptLexer.RegularExpressionLiteral]!!
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
        private val LOGGER: Logger = Logger.getInstance(Factory::class.java)

        private val labeledRuleElementTypes: MutableMap<String, IElementType> = mutableMapOf()

        fun getLabeledRuleElementTypes(): Map<String, IElementType> = labeledRuleElementTypes

        private fun createLabeledRuleElement(label: String, prototype: RuleIElementType): LabeledRuleIElementType {
            val ruleElementType = LabeledRuleIElementType.create(label, prototype)
            labeledRuleElementTypes[label] = ruleElementType
            return ruleElementType
        }

        private val RULES = PSIElementTypeFactory.getRuleIElementTypes(JavaScriptLanguage)
        private val IDENTIFIER = RULES[JavaScriptParser.RULE_identifier]
        private val IDENTIFIER_NAME = RULES[JavaScriptParser.RULE_identifierName]
        private val LITERAL = RULES[JavaScriptParser.RULE_literal]
        private val FUNCTION_DECLARATION = RULES[JavaScriptParser.RULE_functionDeclaration]
        private val FUNCTION_BODY = RULES[JavaScriptParser.RULE_functionBody]
        private val BLOCK = RULES[JavaScriptParser.RULE_block]
        private val VARIABLE_STATEMENT = RULES[JavaScriptParser.RULE_variableStatement]
        private val VARIABLE_DECLARATION_LIST = RULES[JavaScriptParser.RULE_variableDeclarationList]
        private val VARIABLE_DECLARATION = RULES[JavaScriptParser.RULE_variableDeclaration]
        private val IMPORT_STATEMENT = RULES[JavaScriptParser.RULE_importStatement]
        private val EXPORT_STATEMENT = RULES[JavaScriptParser.RULE_exportStatement]
        private val EXPRESSION_STATEMENT = RULES[JavaScriptParser.RULE_expressionStatement]
        private val RETURN_STATEMENT = RULES[JavaScriptParser.RULE_returnStatement]
        private val YIELD_STATEMENT = RULES[JavaScriptParser.RULE_yieldStatement]
        private val IF_STATEMENT = RULES[JavaScriptParser.RULE_ifStatement]
        private val ITERATION_STATEMENT = RULES[JavaScriptParser.RULE_iterationStatement]
        private val DO_STATEMENT = createLabeledRuleElement("DoStatement", ITERATION_STATEMENT)
        private val WHILE_STATEMENT = createLabeledRuleElement("WhileStatement", ITERATION_STATEMENT)
        private val FOR_STATEMENT = createLabeledRuleElement("ForStatement", ITERATION_STATEMENT)
        private val FOR_IN_STATEMENT = createLabeledRuleElement("ForInStatement", ITERATION_STATEMENT)
        private val FOR_OF_STATEMENT = createLabeledRuleElement("ForOfStatement", ITERATION_STATEMENT)
        private val WITH_STATEMENT = RULES[JavaScriptParser.RULE_withStatement]
        private val DEBUGGER_STATEMENT = RULES[JavaScriptParser.RULE_debuggerStatement]
        private val PARAMETERS = RULES[JavaScriptParser.RULE_formalParameterList]
        private val PARAMETER = RULES[JavaScriptParser.RULE_formalParameterArg]
        private val REST_PARAMETER = RULES[JavaScriptParser.RULE_lastFormalParameterArg]
        private val EXPRESSION_SEQUENCE = RULES[JavaScriptParser.RULE_expressionSequence]
        private val EXPRESSION = RULES[JavaScriptParser.RULE_singleExpression]
        private val ARGUMENTS_EXPRESSION = createLabeledRuleElement("ArgumentsExpression", EXPRESSION)
        private val POST_INCREMENT_EXPRESSION = createLabeledRuleElement("PostIncrementExpression", EXPRESSION)
        private val POST_DECREASE_EXPRESSION = createLabeledRuleElement("PostDecreaseExpression", EXPRESSION)
        private val PRE_INCREMENT_EXPRESSION = createLabeledRuleElement("PreIncrementExpression", EXPRESSION)
        private val PRE_DECREASE_EXPRESSION = createLabeledRuleElement("PreDecreaseExpression", EXPRESSION)
        private val IDENTIFIER_EXPRESSION = createLabeledRuleElement("IdentifierExpression", EXPRESSION)
        private val ASSIGNMENT_EXPRESSION = createLabeledRuleElement("AssignmentExpression", EXPRESSION)
        private val MEMBER_DOT_EXPRESSION = createLabeledRuleElement("MemberDotExpression", EXPRESSION)
        private val ARGUMENTS = RULES[JavaScriptParser.RULE_arguments]
        private val ARGUMENT = RULES[JavaScriptParser.RULE_argument]
        private val OBJECT = RULES[JavaScriptParser.RULE_objectLiteral]
        private val ARRAY = RULES[JavaScriptParser.RULE_arrayLiteral]
        private val PROPERTY_ASSIGNMENT = RULES[JavaScriptParser.RULE_propertyAssignment]
        private val PROPERTY_EXPRESSION_ASSIGNMENT = createLabeledRuleElement("PropertyExpressionAssignment", PROPERTY_ASSIGNMENT)
        private val COMPUTED_PROPERTY_EXPRESSION_ASSIGNMENT = createLabeledRuleElement("ComputedPropertyExpressionAssignment", PROPERTY_ASSIGNMENT)
        private val FUNCTION_PROPERTY = createLabeledRuleElement("FunctionProperty", PROPERTY_ASSIGNMENT)
        private val PROPERTY_GETTER = createLabeledRuleElement("PropertyGetter", PROPERTY_ASSIGNMENT)
        private val PROPERTY_SETTER = createLabeledRuleElement("PropertySetter", PROPERTY_ASSIGNMENT)
        private val PROPERTY_SHORTHAND = createLabeledRuleElement("PropertyShorthand", PROPERTY_ASSIGNMENT)
        private val TEMPLATE_STRING = RULES[JavaScriptParser.RULE_templateStringLiteral]
        private val ANONYMOUS_FUNCTION = RULES[JavaScriptParser.RULE_anonymousFunction]
        private val CLASS_DECLARATION = RULES[JavaScriptParser.RULE_classDeclaration]
        private val CLASS_ELEMENT = RULES[JavaScriptParser.RULE_classElement]
        private val CLASS_ELEMENT_NAME = RULES[JavaScriptParser.RULE_classElementName]
        private val METHOD_DEFINITION = RULES[JavaScriptParser.RULE_methodDefinition]
        private val FIELD_DEFINITION = RULES[JavaScriptParser.RULE_fieldDefinition]
        private val PROPERTY_NAME = RULES[JavaScriptParser.RULE_propertyName]
        private val LABELED_STATEMENT = RULES[JavaScriptParser.RULE_labelledStatement]
        private val BREAK_STATEMENT = RULES[JavaScriptParser.RULE_breakStatement]
        private val CONTINUE_STATEMENT = RULES[JavaScriptParser.RULE_continueStatement]
        private val THROW_STATEMENT = RULES[JavaScriptParser.RULE_throwStatement]
        private val TRY_STATEMENT = RULES[JavaScriptParser.RULE_tryStatement]
        private val CATCH = RULES[JavaScriptParser.RULE_catchProduction]
        private val FINALLY = RULES[JavaScriptParser.RULE_finallyProduction]
        private val SWITCH = RULES[JavaScriptParser.RULE_switchStatement]
        private val CASE_BLOCK = RULES[JavaScriptParser.RULE_caseBlock]
        private val CASE = RULES[JavaScriptParser.RULE_caseClause]
        private val DEFAULT_CASE = RULES[JavaScriptParser.RULE_defaultClause]

        fun createElement(node: ASTNode): PsiElement {
            return when (node.elementType) {
                IDENTIFIER -> JavaScriptIdentifier(node)
                IDENTIFIER_NAME -> JavaScriptIdentifierName(node)
                LITERAL -> JavaScriptLiteral(node)
                FUNCTION_DECLARATION -> JavaScriptFunctionDeclaration(node)
                FUNCTION_BODY -> JavaScriptFunctionBody(node)
                BLOCK -> JavaScriptBlock(node)
                VARIABLE_STATEMENT -> JavaScriptVariableStatement(node)
                VARIABLE_DECLARATION_LIST -> JavaScriptVariableDeclarationList(node)
                VARIABLE_DECLARATION -> JavaScriptVariableDeclaration(node)
                IMPORT_STATEMENT -> JavaScriptImportStatement(node)
                EXPORT_STATEMENT -> JavaScriptExportStatement(node)
                EXPRESSION_STATEMENT -> JavaScriptExpressionStatement(node)
                RETURN_STATEMENT -> JavaScriptReturnStatement(node)
                YIELD_STATEMENT -> JavaScriptYieldStatement(node)
                IF_STATEMENT -> JavaScriptIfStatement(node)
                ITERATION_STATEMENT -> unexpectedNode(node)
                DO_STATEMENT -> JavaScriptDoWhileStatement(node)
                WHILE_STATEMENT -> JavaScriptWhileStatement(node)
                FOR_STATEMENT -> JavaScriptForStatement(node)
                FOR_IN_STATEMENT -> JavaScriptForInStatement(node)
                FOR_OF_STATEMENT -> JavaScriptForOfStatement(node)
                WITH_STATEMENT -> JavaScriptWithStatement(node)
                DEBUGGER_STATEMENT -> JavaScriptDebuggerStatement(node)
                PARAMETERS -> JavaScriptParameters(node)
                PARAMETER -> JavaScriptFormalParameter(node)
                REST_PARAMETER -> JavaScriptFormalRestParameter(node)
                EXPRESSION_SEQUENCE -> JavaScriptExpressionSequence(node)
                EXPRESSION -> JavaScriptExpression.Other(node)
                ARGUMENTS_EXPRESSION -> JavaScriptCallExpression(node)
                POST_INCREMENT_EXPRESSION -> JavaScriptUpdateExpression(node)
                POST_DECREASE_EXPRESSION -> JavaScriptUpdateExpression(node)
                PRE_INCREMENT_EXPRESSION -> JavaScriptUpdateExpression(node)
                PRE_DECREASE_EXPRESSION -> JavaScriptUpdateExpression(node)
                IDENTIFIER_EXPRESSION -> JavaScriptIdentifierExpression(node)
                ASSIGNMENT_EXPRESSION -> JavaScriptAssignmentExpression(node)
                MEMBER_DOT_EXPRESSION -> JavaScriptMemberDotExpression(node)
                ARGUMENTS -> JavaScriptArguments(node)
                ARGUMENT -> JavaScriptArgument(node)
                OBJECT -> JavaScriptObject(node)
                ARRAY -> JavaScriptArray(node)
                PROPERTY_ASSIGNMENT -> unexpectedNode(node)
                PROPERTY_EXPRESSION_ASSIGNMENT -> JavaScriptPropertyExpressionAssignment(node)
                COMPUTED_PROPERTY_EXPRESSION_ASSIGNMENT -> JavaScriptComputedPropertyExpressionAssignment(node)
                FUNCTION_PROPERTY -> JavaScriptFunctionProperty(node)
                PROPERTY_GETTER -> JavaScriptPropertyGetter(node)
                PROPERTY_SETTER -> JavaScriptPropertySetter(node)
                PROPERTY_SHORTHAND -> JavaScriptPropertyShorthand(node)
                TEMPLATE_STRING -> JavaScriptTemplateString(node)
                ANONYMOUS_FUNCTION -> JavaScriptAnonymousFunction(node)
                CLASS_DECLARATION -> JavaScriptClassDeclaration(node)
                CLASS_ELEMENT -> createClassElement(node)
                CLASS_ELEMENT_NAME -> JavaScriptClassElementName(node)
                METHOD_DEFINITION -> JavaScriptMethodDefinition(node)
                FIELD_DEFINITION -> JavaScriptFieldDefinition(node)
                PROPERTY_NAME -> JavaScriptPropertyName(node)
                LABELED_STATEMENT -> JavaScriptLabeledStatement(node)
                BREAK_STATEMENT -> JavaScriptBreakStatement(node)
                CONTINUE_STATEMENT -> JavaScriptContinueStatement(node)
                THROW_STATEMENT -> JavaScriptThrowStatement(node)
                TRY_STATEMENT -> JavaScriptTryStatement(node)
                CATCH -> JavaScriptCatch(node)
                FINALLY -> JavaScriptFinally(node)
                SWITCH -> JavaScriptSwitchStatement(node)
                CASE_BLOCK -> JavaScriptCaseBlock(node)
                CASE -> JavaScriptCaseClause(node)
                DEFAULT_CASE -> JavaScriptDefaultClause(node)
                //TODO: other rules
                else -> ASTWrapperPsiElement(node)
            }
        }

        private fun unexpectedNode(node: ASTNode): PsiElement {
            LOGGER.warn("Unexpected node $node")
            return ASTWrapperPsiElement(node)
        }

        private fun createClassElement(node: ASTNode): PsiElement {
            return when (node.lastChildNode?.psi) {
                is JavaScriptMethodDefinition -> JavaScriptMethod(node)
                is JavaScriptFieldDefinition -> JavaScriptField(node)
                is JavaScriptBlock -> JavaScriptClassStaticBlock(node)
                else -> ASTWrapperPsiElement(node)
            }
        }
    }
}