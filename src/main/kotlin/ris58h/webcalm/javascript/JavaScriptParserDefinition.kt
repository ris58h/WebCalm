package ris58h.webcalm.javascript

import JavaScriptLexer
import JavaScriptParser
import com.intellij.lang.ASTNode
import com.intellij.lang.ParserDefinition
import com.intellij.lang.PsiParser
import com.intellij.lexer.Lexer
import com.intellij.openapi.project.Project
import com.intellij.psi.FileViewProvider
import com.intellij.psi.PsiElement
import com.intellij.psi.PsiFile
import com.intellij.psi.tree.IElementType
import com.intellij.psi.tree.IFileElementType
import com.intellij.psi.tree.TokenSet
import org.antlr.intellij.adaptor.lexer.ANTLRLexerAdaptor
import org.antlr.intellij.adaptor.parser.ANTLRParserAdaptor
import org.antlr.v4.runtime.Parser
import org.antlr.v4.runtime.tree.ParseTree
import ris58h.webcalm.javascript.psi.*

class JavaScriptParserDefinition : ParserDefinition {
    override fun createLexer(project: Project?): Lexer {
        return ANTLRLexerAdaptor(JavaScriptLanguage, JavaScriptLexer(null))
    }

    override fun createParser(project: Project?): PsiParser {
        return object : ANTLRParserAdaptor(JavaScriptLanguage, JavaScriptParser(null)) {
            override fun parse(parser: Parser, root: IElementType): ParseTree {
                if (root is IFileElementType) {
                    return (parser as JavaScriptParser).program()
                }
                throw UnsupportedOperationException("Unsupported root: ${root.javaClass.name}")
            }
        }
    }

    override fun getFileNodeType(): IFileElementType = JavaScriptTypes.FILE

    override fun getWhitespaceTokens(): TokenSet = JavaScriptTokenSets.WHITESPACE

    override fun getCommentTokens(): TokenSet = JavaScriptTokenSets.COMMENTS

    override fun getStringLiteralElements(): TokenSet = JavaScriptTokenSets.STRINGS

    override fun createElement(node: ASTNode): PsiElement = JavaScriptTypes.Factory.createElement(node)

    override fun createFile(viewProvider: FileViewProvider): PsiFile = JavaScriptFile(viewProvider)
}