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
    val SPACE = TOKENS[css3Lexer.Space]!!
    val COMMENT = TOKENS[css3Lexer.Comment]!!
    val STRING = TOKENS[css3Lexer.String_]!!
    val NUMBER = TOKENS[css3Lexer.Number]!!
    val DIMENSION = TOKENS[css3Lexer.Dimension]!!
    val PERCENTAGE = TOKENS[css3Lexer.Percentage]!!
    val HASH = TOKENS[css3Lexer.Hash]!!
    val OPEN_BRACE = TOKENS[css3Lexer.tokenNames.indexOfFirst { it == "'{'"}]!!
    val CLOSE_BRACE = TOKENS[css3Lexer.tokenNames.indexOfFirst { it == "'}'"}]!!
    val OPEN_BRACKET = TOKENS[css3Lexer.tokenNames.indexOfFirst { it == "'['"}]!!
    val CLOSE_BRACKET = TOKENS[css3Lexer.tokenNames.indexOfFirst { it == "']'"}]!!
    val OPEN_PAREN = TOKENS[css3Lexer.tokenNames.indexOfFirst { it == "'('"}]!!
    val CLOSE_PAREN = TOKENS[css3Lexer.tokenNames.indexOfFirst { it == "')'"}]!!

    object Factory {
        private val RULES = PSIElementTypeFactory.getRuleIElementTypes(CssLanguage)
        private val IDENTIFIER = RULES[css3Parser.RULE_ident]
        private val TYPE_SELECTOR = RULES[css3Parser.RULE_typeSelector]
        private val CLASS_NAME_SELECTOR = RULES[css3Parser.RULE_className]
        private val PSEUDO_SELECTOR = RULES[css3Parser.RULE_pseudo]
        private val NOT_SELECTOR = RULES[css3Parser.RULE_negation]
        private val ATTRIBUTE_SELECTOR = RULES[css3Parser.RULE_attrib]

        fun createElement(node: ASTNode): PsiElement {
            return when (node.elementType) {
                IDENTIFIER -> CssIdentifier(node)
                TYPE_SELECTOR -> CssTypeSelector(node)
                CLASS_NAME_SELECTOR -> CssClassNameSelector(node)
                PSEUDO_SELECTOR -> CssPseudoSelector(node)
                NOT_SELECTOR -> CssNotSelector(node)
                ATTRIBUTE_SELECTOR -> CssAttributeSelector(node)
                else -> return ASTWrapperPsiElement(node)
            }
        }
    }
}