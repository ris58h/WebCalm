package ris58h.webcalm.css

import com.intellij.lang.ASTNode
import com.intellij.lang.ParserDefinition
import com.intellij.openapi.project.Project
import com.intellij.psi.FileViewProvider
import com.intellij.psi.tree.TokenSet
import ris58h.webcalm.css.psi.CssFile
import ris58h.webcalm.css.psi.CssTokenSets
import ris58h.webcalm.css.psi.CssTypes

class CssParserDefinition : ParserDefinition {
    override fun createLexer(project: Project?) = CssLexer()

    override fun createParser(project: Project?) = CssParser()

    override fun getFileNodeType() = CssTypes.FILE

    override fun getWhitespaceTokens(): TokenSet = TokenSet.EMPTY // Space token is a part of the grammar

    override fun getCommentTokens() = CssTokenSets.COMMENTS

    override fun getStringLiteralElements() = CssTokenSets.STRINGS

    override fun createElement(node: ASTNode) = CssTypes.Factory.createElement(node)

    override fun createFile(viewProvider: FileViewProvider) = CssFile(viewProvider)
}