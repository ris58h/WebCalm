package ris58h.webcalm.antlr

import com.intellij.lang.Language
import com.intellij.lang.PsiBuilder
import org.antlr.intellij.adaptor.parser.ANTLRParseTreeToPSIConverter
import org.antlr.v4.runtime.Parser
import org.antlr.v4.runtime.ParserRuleContext

class SkipRuleNodeParseTreeToPsiConverter(private val rulesToSkip: Set<Int>, language: Language, parser: Parser, builder: PsiBuilder) : ANTLRParseTreeToPSIConverter(language, parser, builder) {
    override fun enterEveryRule(ctx: ParserRuleContext) {
        if (rulesToSkip.contains(ctx.ruleIndex)) {
            // Just skip this node.
        } else {
            super.enterEveryRule(ctx)
        }
    }

    override fun exitEveryRule(ctx: ParserRuleContext) {
        if (rulesToSkip.contains(ctx.ruleIndex)) {
            // Just skip this node.
        } else {
            super.exitEveryRule(ctx)
        }
    }
}