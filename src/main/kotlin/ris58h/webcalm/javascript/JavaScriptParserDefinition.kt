package ris58h.webcalm.javascript

import com.intellij.lang.ASTNode
import com.intellij.lang.ParserDefinition
import com.intellij.openapi.project.Project
import com.intellij.psi.FileViewProvider
import ris58h.webcalm.javascript.psi.*

class JavaScriptParserDefinition : ParserDefinition {
    override fun createLexer(project: Project?) = JavaScriptLexer()

    override fun createParser(project: Project?) = JavaScriptParser()

    override fun getFileNodeType() = JavaScriptTypes.FILE

    override fun getWhitespaceTokens() = JavaScriptTokenSets.WHITESPACE

    override fun getCommentTokens() = JavaScriptTokenSets.COMMENTS

    override fun getStringLiteralElements() = JavaScriptTokenSets.STRINGS

    override fun createElement(node: ASTNode) = JavaScriptTypes.Factory.createElement(node)

    override fun createFile(viewProvider: FileViewProvider) = JavaScriptFile(viewProvider)
}