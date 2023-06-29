package ris58h.webcalm.antlr

import com.intellij.lang.Language
import com.intellij.lang.PsiBuilder
import org.antlr.intellij.adaptor.parser.ANTLRParseTreeToPSIConverter
import org.antlr.v4.runtime.Parser
import org.antlr.v4.runtime.ParserRuleContext

class DropRuleNodeParseTreeToPsiConverter(private val rulesToDrop: Set<Int>, language: Language, parser: Parser, builder: PsiBuilder) : ANTLRParseTreeToPSIConverter(language, parser, builder) {
    override fun exitEveryRule(ctx: ParserRuleContext) {
        if (rulesToDrop.contains(ctx.ruleIndex)) {
            val marker = markers.pop()
            marker.drop()
        } else {
            super.exitEveryRule(ctx)
        }
    }
}