package ris58h.webcalm.css

import com.intellij.lang.PsiBuilder
import com.intellij.psi.tree.IElementType
import com.intellij.psi.tree.IFileElementType
import css3Parser
import org.antlr.intellij.adaptor.parser.ANTLRParseTreeToPSIConverter
import org.antlr.intellij.adaptor.parser.ANTLRParserAdaptor
import org.antlr.v4.runtime.Parser
import org.antlr.v4.runtime.tree.ParseTree
import ris58h.webcalm.antlr.CustomParseTreeToPsiConverter

class CssParser : ANTLRParserAdaptor(CssLanguage, css3Parser(null)) {
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
        return CustomParseTreeToPsiConverter(language, parser, builder)
            .withRulesToDrop(rulesToDrop)
    }
}