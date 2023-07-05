package ris58h.webcalm.css

import com.intellij.lang.ASTNode
import com.intellij.lang.ParserDefinition
import com.intellij.lang.PsiBuilder
import com.intellij.lang.PsiParser
import com.intellij.openapi.project.Project
import com.intellij.psi.FileViewProvider
import com.intellij.psi.tree.IElementType
import com.intellij.psi.tree.IFileElementType
import com.intellij.psi.tree.TokenSet
import css3Parser
import org.antlr.intellij.adaptor.parser.ANTLRParseTreeToPSIConverter
import org.antlr.intellij.adaptor.parser.ANTLRParserAdaptor
import org.antlr.v4.runtime.Parser
import org.antlr.v4.runtime.tree.ParseTree
import ris58h.webcalm.antlr.DropRuleNodeParseTreeToPsiConverter
import ris58h.webcalm.css.psi.CssFile
import ris58h.webcalm.css.psi.CssTokenSets
import ris58h.webcalm.css.psi.CssTypes

class CssParserDefinition : ParserDefinition {
    override fun createLexer(project: Project?) = CssLexer()

    override fun createParser(project: Project?): PsiParser {
        return object : ANTLRParserAdaptor(CssLanguage, css3Parser(null)) {
            override fun parse(parser: Parser, root: IElementType): ParseTree {
                if (root is IFileElementType) {
                    return (parser as css3Parser).stylesheet()
                }
                throw UnsupportedOperationException("Unsupported root: ${root.javaClass.name}")
            }

            override fun createListener(parser: Parser, root: IElementType, builder: PsiBuilder): ANTLRParseTreeToPSIConverter {
                val rulesToDrop = setOf(
                    css3Parser.RULE_stylesheet,
                    css3Parser.RULE_ws,
                    css3Parser.RULE_nestedStatement,
                    css3Parser.RULE_groupRuleBody,
                    css3Parser.RULE_hexcolor,
                    css3Parser.RULE_expr,
                )
                return DropRuleNodeParseTreeToPsiConverter(rulesToDrop, language, parser, builder)
            }
        }
    }

    override fun getFileNodeType() = CssTypes.FILE

    override fun getWhitespaceTokens(): TokenSet = TokenSet.EMPTY // Space token is a part of the grammar

    override fun getCommentTokens() = CssTokenSets.COMMENTS

    override fun getStringLiteralElements() = CssTokenSets.STRINGS

    override fun createElement(node: ASTNode) = CssTypes.Factory.createElement(node)

    override fun createFile(viewProvider: FileViewProvider) = CssFile(viewProvider)
}