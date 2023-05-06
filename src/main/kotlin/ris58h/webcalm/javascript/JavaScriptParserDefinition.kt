package ris58h.webcalm.javascript

import com.intellij.lang.ASTNode
import com.intellij.lang.ParserDefinition
import com.intellij.lang.PsiParser
import com.intellij.lexer.Lexer
import com.intellij.openapi.project.Project
import com.intellij.psi.FileViewProvider
import com.intellij.psi.PsiElement
import com.intellij.psi.PsiFile
import com.intellij.psi.tree.IFileElementType
import com.intellij.psi.tree.TokenSet
import ris58h.webcalm.javascript.psi.*

class JavaScriptParserDefinition : ParserDefinition {
    override fun createLexer(project: Project?): Lexer = JavaScriptLexer()

    override fun createParser(project: Project?): PsiParser = JavaScriptParser()

    override fun getFileNodeType(): IFileElementType = JavaScriptTypes.FILE

    override fun getWhitespaceTokens(): TokenSet = JavaScriptTokenSets.WHITESPACE

    override fun getCommentTokens(): TokenSet = JavaScriptTokenSets.COMMENTS

    override fun getStringLiteralElements(): TokenSet = JavaScriptTokenSets.STRINGS

    override fun createElement(node: ASTNode): PsiElement = JavaScriptTypes.Factory.createElement(node)

    override fun createFile(viewProvider: FileViewProvider): PsiFile = JavaScriptFile(viewProvider)
}