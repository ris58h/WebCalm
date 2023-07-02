package ris58h.webcalm.css.psi

import com.intellij.extapi.psi.ASTWrapperPsiElement
import com.intellij.lang.ASTNode
import com.intellij.psi.PsiElement
import com.intellij.psi.tree.IFileElementType
import org.antlr.intellij.adaptor.lexer.PSIElementTypeFactory
import ris58h.webcalm.css.CssLanguage

object CssTypes {
    init {
        PSIElementTypeFactory.defineLanguageIElementTypes(
            CssLanguage, css3Lexer.tokenNames, css3Parser.ruleNames
        )
    }

    val FILE = IFileElementType(CssLanguage)

    private val TOKENS = PSIElementTypeFactory.getTokenIElementTypes(CssLanguage)
    val UNEXPECTED_CHARACTER = TOKENS[css3Lexer.UnexpectedCharacter]!!
    val SPACE = TOKENS[css3Lexer.Space]!!
    val COMMENT = TOKENS[css3Lexer.Comment]!!
    val STRING = TOKENS[css3Lexer.String_]!!
    val NUMBER = TOKENS[css3Lexer.Number]!!
    val DIMENSION = TOKENS[css3Lexer.Dimension]!!
    val PERCENTAGE = TOKENS[css3Lexer.Percentage]!!
    val HASH = TOKENS[css3Lexer.Hash]!!
    val URI = TOKENS[css3Lexer.Uri]!!
    val VARIABLE = TOKENS[css3Lexer.Variable]!!
    val AT_IMPORT = TOKENS[css3Lexer.Import]!!
    val AT_PAGE = TOKENS[css3Lexer.Page]!!
    val AT_MEDIA = TOKENS[css3Lexer.Media]!!
    val AT_NAMESPACE = TOKENS[css3Lexer.Namespace]!!
    val AT_CHARSET = TOKENS[css3Lexer.Charset]!!
    val AT_FONT_FACE = TOKENS[css3Lexer.FontFace]!!
    val AT_SUPPORTS = TOKENS[css3Lexer.Supports]!!
    val AT_KEYFRAMES = TOKENS[css3Lexer.Keyframes]!!
    val AT_VIEWPORT = TOKENS[css3Lexer.Viewport]!!
    val AT_COUNTER_STYLE = TOKENS[css3Lexer.CounterStyle]!!
    val AT_FONT_FEATURE_VALUES = TOKENS[css3Lexer.FontFeatureValues]!!
    val IMPORTANT_KEYWORD = TOKENS[css3Lexer.Important]!!
    val AND_KEYWORD = TOKENS[css3Lexer.And]!!
    val OR_KEYWORD = TOKENS[css3Lexer.Or]!!
    val FROM_KEYWORD = TOKENS[css3Lexer.From]!!
    val TO_KEYWORD = TOKENS[css3Lexer.To]!!
    val PLUS_OP = TOKENS[css3Lexer.Plus]!!
    val MINUS_OP = TOKENS[css3Lexer.Minus]!!
    val DIVIDE_OP = TOKENS[css3Lexer.Divide]!!
    val MULTIPLY_OP = TOKENS[css3Lexer.Multiply]!!
    val OPEN_BRACE = TOKENS[css3Lexer.OpenBrace]!!
    val CLOSE_BRACE = TOKENS[css3Lexer.CloseBrace]!!
    val OPEN_BRACKET = TOKENS[css3Lexer.OpenBracket]!!
    val CLOSE_BRACKET = TOKENS[css3Lexer.CloseBracket]!!
    val OPEN_PAREN = TOKENS[css3Lexer.OpenParen]!!
    val CLOSE_PAREN = TOKENS[css3Lexer.CloseParen]!!
    val IDENTIFIER = TOKENS[css3Lexer.Ident]!!

    object Factory {
        private val RULES = PSIElementTypeFactory.getRuleIElementTypes(CssLanguage)
        private val IDENTIFIER = RULES[css3Parser.RULE_ident]
        private val TYPE_SELECTOR = RULES[css3Parser.RULE_typeSelector]
        private val CLASS_NAME_SELECTOR = RULES[css3Parser.RULE_className]
        private val PSEUDO_SELECTOR = RULES[css3Parser.RULE_pseudo]
        private val NOT_SELECTOR = RULES[css3Parser.RULE_negation]
        private val ATTRIBUTE_SELECTOR = RULES[css3Parser.RULE_attrib]
        private val RULE_SET = RULES[css3Parser.RULE_ruleset]
        private val MEDIA_RULE = RULES[css3Parser.RULE_media]
        private val PAGE_RULE = RULES[css3Parser.RULE_page]
        private val FONT_FACE_RULE = RULES[css3Parser.RULE_fontFaceRule]
        private val KEYFRAMES_RULE = RULES[css3Parser.RULE_keyframesRule]
        private val KEYFRAME_BLOCK = RULES[css3Parser.RULE_keyframeBlock]
        private val SUPPORTS_RULE = RULES[css3Parser.RULE_supportsRule]
        private val VIEWPORT_RULE = RULES[css3Parser.RULE_viewport]
        private val COUNTER_STYLE_RULE = RULES[css3Parser.RULE_counterStyle]
        private val FONT_FEATURE_VALUES_RULE = RULES[css3Parser.RULE_fontFeatureValuesRule]
        private val FEATURE_VALUE_BLOCK = RULES[css3Parser.RULE_featureValueBlock]
        private val FEATURE_VALUE_DEFINITION = RULES[css3Parser.RULE_featureValueDefinition]
        private val AT_RULE = RULES[css3Parser.RULE_atRule]
        private val AT_KEYWORD = RULES[css3Parser.RULE_atKeyword]
        private val BLOCK = RULES[css3Parser.RULE_block]
        private val DECLARATION_LIST = RULES[css3Parser.RULE_declarationList]
        private val DECLARATION = RULES[css3Parser.RULE_declaration]
        private val PROPERTY = RULES[css3Parser.RULE_property_]
        private val VALUE = RULES[css3Parser.RULE_value]
        private val TERM = RULES[css3Parser.RULE_term]
        private val VAR_FUNCTION = RULES[css3Parser.RULE_var_]
        private val CALC_FUNCTION = RULES[css3Parser.RULE_calc]

        fun createElement(node: ASTNode): PsiElement {
            return when (node.elementType) {
                IDENTIFIER -> CssIdentifier(node)
                TYPE_SELECTOR -> CssTypeSelector(node)
                CLASS_NAME_SELECTOR -> CssClassNameSelector(node)
                PSEUDO_SELECTOR -> CssPseudoSelector(node)
                NOT_SELECTOR -> CssNotSelector(node)
                ATTRIBUTE_SELECTOR -> CssAttributeSelector(node)
                MEDIA_RULE -> CssMediaRule(node)
                PAGE_RULE -> CssPageRule(node)
                FONT_FACE_RULE -> CssFontFaceRule(node)
                KEYFRAMES_RULE -> CssKeyframesRule(node)
                KEYFRAME_BLOCK -> CssKeyframeBlock(node)
                SUPPORTS_RULE -> CssSupportsRule(node)
                VIEWPORT_RULE -> CssViewportRule(node)
                COUNTER_STYLE_RULE -> CssCounterStyleRule(node)
                FONT_FEATURE_VALUES_RULE -> CssFontFeatureValuesRule(node)
                FEATURE_VALUE_BLOCK -> CssFeatureValueBlock(node)
                FEATURE_VALUE_DEFINITION -> CssFeatureValueDefinition(node)
                AT_RULE -> CssAtRule(node)
                AT_KEYWORD -> CssAtKeyword(node)
                BLOCK -> CssBlock(node)
                RULE_SET -> CssRuleSet(node)
                DECLARATION_LIST -> CssDeclarationList(node)
                DECLARATION -> CssDeclaration(node)
                PROPERTY -> CssProperty(node)
                VALUE -> CssValue(node)
                TERM -> CssTerm(node)
                VAR_FUNCTION -> CssVarFunction(node)
                CALC_FUNCTION -> CssCalcFunction(node)
                else -> return ASTWrapperPsiElement(node)
            }
        }
    }
}