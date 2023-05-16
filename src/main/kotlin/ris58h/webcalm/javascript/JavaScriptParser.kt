package ris58h.webcalm.javascript

import JavaScriptParser as JavaScriptANTLRParser
import com.intellij.lang.PsiBuilder
import com.intellij.psi.tree.IElementType
import com.intellij.psi.tree.IFileElementType
import org.antlr.intellij.adaptor.parser.ANTLRParseTreeToPSIConverter
import org.antlr.intellij.adaptor.parser.ANTLRParserAdaptor
import org.antlr.v4.runtime.Parser
import org.antlr.v4.runtime.tree.ParseTree
import ris58h.webcalm.antlr.SkipRuleNodeParseTreeToPsiConverter

class JavaScriptParser : ANTLRParserAdaptor(JavaScriptLanguage, JavaScriptANTLRParser(null)) {
    override fun parse(parser: Parser, root: IElementType): ParseTree {
        if (root is IFileElementType) {
            return (parser as JavaScriptANTLRParser).program()
        }
        throw UnsupportedOperationException("Unsupported root: ${root.javaClass.name}")
    }

    override fun createListener(parser: Parser, root: IElementType, builder: PsiBuilder): ANTLRParseTreeToPSIConverter {
        val rulesToSkip = setOf(
            JavaScriptANTLRParser.RULE_program,
            JavaScriptANTLRParser.RULE_sourceElements,
            JavaScriptANTLRParser.RULE_sourceElement,
            JavaScriptANTLRParser.RULE_statementList,
            JavaScriptANTLRParser.RULE_statement,
            JavaScriptANTLRParser.RULE_eos,
            JavaScriptANTLRParser.RULE_declaration,
            JavaScriptANTLRParser.RULE_assignable,
            JavaScriptANTLRParser.RULE_elementList,
            JavaScriptANTLRParser.RULE_arrayElement,
            JavaScriptANTLRParser.RULE_templateStringAtom,
        )
        return SkipRuleNodeParseTreeToPsiConverter(rulesToSkip, language, parser, builder)
    }
}