package ris58h.webcalm.javascript

import JavaScriptParser as JavaScriptANTLRParser
import com.intellij.lang.PsiBuilder
import com.intellij.psi.tree.IElementType
import com.intellij.psi.tree.IFileElementType
import org.antlr.intellij.adaptor.parser.ANTLRParseTreeToPSIConverter
import org.antlr.intellij.adaptor.parser.ANTLRParserAdaptor
import org.antlr.v4.runtime.Parser
import org.antlr.v4.runtime.tree.ParseTree
import ris58h.webcalm.antlr.CustomParseTreeToPsiConverter

class JavaScriptParser : ANTLRParserAdaptor(JavaScriptLanguage, JavaScriptANTLRParser(null)) {
    override fun parse(parser: Parser, root: IElementType): ParseTree {
        if (root is IFileElementType) {
            return (parser as JavaScriptANTLRParser).program()
        }
        throw UnsupportedOperationException("Unsupported root: ${root.javaClass.name}")
    }

    override fun createListener(parser: Parser, root: IElementType, builder: PsiBuilder): ANTLRParseTreeToPSIConverter {
        val rulesToDrop = setOf(
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
            JavaScriptANTLRParser.RULE_arrowFunctionParameters,
            JavaScriptANTLRParser.RULE_arrowFunctionBody,
            JavaScriptANTLRParser.RULE_assignmentOperator,
            JavaScriptANTLRParser.RULE_classTail,
            JavaScriptANTLRParser.RULE_getter,
            JavaScriptANTLRParser.RULE_setter,
            JavaScriptANTLRParser.RULE_caseClauses,
        )
        return CustomParseTreeToPsiConverter(language, parser, builder)
            .withRulesToDrop(rulesToDrop)
    }
}