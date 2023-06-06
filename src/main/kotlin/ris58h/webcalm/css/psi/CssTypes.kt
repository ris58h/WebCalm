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
        fun createElement(node: ASTNode): PsiElement {
            return ASTWrapperPsiElement(node)
        }
    }
}