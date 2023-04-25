package ris58h.webcalm.css.psi

import com.intellij.lang.ASTNode
import com.intellij.psi.PsiElement
import com.intellij.psi.tree.IFileElementType
import org.antlr.intellij.adaptor.lexer.PSIElementTypeFactory
import org.antlr.intellij.adaptor.psi.ANTLRPsiNode
import ris58h.webcalm.css.CssLanguage

object CssTypes {
    init {
        PSIElementTypeFactory.defineLanguageIElementTypes(
            CssLanguage, css3Lexer.tokenNames, css3Parser.ruleNames
        )
    }

    val FILE = IFileElementType(CssLanguage)

    private val TOKENS = PSIElementTypeFactory.getTokenIElementTypes(CssLanguage)
    val WS = TOKENS[css3Lexer.Space]!!
    val COMMENT = TOKENS[css3Lexer.Comment]!!
    val STRING = TOKENS[css3Lexer.String_]!!

    object Factory {
        fun createElement(node: ASTNode): PsiElement {
            return ANTLRPsiNode(node)
        }
    }
}