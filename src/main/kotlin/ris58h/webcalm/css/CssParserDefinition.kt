package ris58h.webcalm.css

import com.intellij.lang.ASTNode
import com.intellij.lang.ParserDefinition
import com.intellij.lang.PsiBuilder
import com.intellij.lang.PsiParser
import com.intellij.lexer.Lexer
import com.intellij.openapi.project.Project
import com.intellij.psi.FileViewProvider
import com.intellij.psi.PsiElement
import com.intellij.psi.PsiFile
import com.intellij.psi.tree.IElementType
import com.intellij.psi.tree.IFileElementType
import com.intellij.psi.tree.TokenSet
import css3Parser
import org.antlr.intellij.adaptor.parser.ANTLRParseTreeToPSIConverter
import org.antlr.intellij.adaptor.parser.ANTLRParserAdaptor
import org.antlr.v4.runtime.Parser
import org.antlr.v4.runtime.tree.ParseTree
import ris58h.webcalm.antlr.SkipRuleNodeParseTreeToPsiConverter
import ris58h.webcalm.css.psi.CssFile
import ris58h.webcalm.css.psi.CssTokenSets
import ris58h.webcalm.css.psi.CssTypes

class CssParserDefinition : ParserDefinition {
    override fun createLexer(project: Project?): Lexer = CssLexer()

    override fun createParser(project: Project?): PsiParser {
        return object : ANTLRParserAdaptor(CssLanguage, css3Parser(null)) {
            override fun parse(parser: Parser, root: IElementType): ParseTree {
                if (root is IFileElementType) {
                    return (parser as css3Parser).stylesheet()
                }
                throw UnsupportedOperationException("Unsupported root: ${root.javaClass.name}")
            }

            override fun createListener(parser: Parser, root: IElementType, builder: PsiBuilder): ANTLRParseTreeToPSIConverter {
                val rulesToSkip = setOf(
                    css3Parser.RULE_stylesheet,
//                    css3Parser.RULE_ws//TODO skipping ws rule leads to wrong PSI tree sometimes
                )
                return SkipRuleNodeParseTreeToPsiConverter(rulesToSkip, language, parser, builder)
            }
        }
    }

    override fun getFileNodeType(): IFileElementType = CssTypes.FILE

    override fun getWhitespaceTokens(): TokenSet = TokenSet.EMPTY // Space token is a part of the grammar

    override fun getCommentTokens(): TokenSet = CssTokenSets.COMMENTS

    override fun getStringLiteralElements(): TokenSet = CssTokenSets.STRINGS

    override fun createElement(node: ASTNode): PsiElement = CssTypes.Factory.createElement(node)

    override fun createFile(viewProvider: FileViewProvider): PsiFile = CssFile(viewProvider)
}