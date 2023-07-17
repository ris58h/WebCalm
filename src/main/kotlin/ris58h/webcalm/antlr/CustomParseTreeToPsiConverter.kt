package ris58h.webcalm.antlr

import com.intellij.lang.Language
import com.intellij.lang.PsiBuilder
import com.intellij.openapi.progress.ProgressIndicatorProvider
import com.intellij.psi.tree.IElementType
import org.antlr.intellij.adaptor.parser.ANTLRParseTreeToPSIConverter
import org.antlr.v4.runtime.Parser
import org.antlr.v4.runtime.ParserRuleContext

class CustomParseTreeToPsiConverter(language: Language, parser: Parser, builder: PsiBuilder) : ANTLRParseTreeToPSIConverter(language, parser, builder) {
    private var rulesToDrop: Set<Int> = emptySet()
    private var rulesToCollapse: Map<Int, IElementType> = emptyMap()
    private var labeledRules: Map<String, IElementType> = emptyMap()

    override fun exitEveryRule(ctx: ParserRuleContext) {
        ProgressIndicatorProvider.checkCanceled()
        val ruleIndex = ctx.ruleIndex
        if (rulesToDrop.contains(ruleIndex)) {
            val marker = markers.pop()
            marker.drop()
        } else if (rulesToCollapse.containsKey(ruleIndex)) {
            val marker = markers.pop()
            marker.collapse(rulesToCollapse[ruleIndex]!!)
        } else {
            val marker = markers.pop()
            val label = label(ctx)
            val ruleIElementType = labeledRules[label] ?: getRuleElementTypes()[ctx.ruleIndex]
            marker.done(ruleIElementType)
        }
    }

    fun withRulesToDrop(rulesToDrop: Set<Int>): CustomParseTreeToPsiConverter {
        this.rulesToDrop = rulesToDrop
        return this
    }

    fun withRulesToCollapse(rulesToCollapse: Map<Int, IElementType>): CustomParseTreeToPsiConverter {
        this.rulesToCollapse = rulesToCollapse
        return this
    }

    fun withLabeledRules(labeledRuleElements: Map<String, IElementType>): CustomParseTreeToPsiConverter {
        this.labeledRules = labeledRuleElements
        return this
    }

    private fun label(ctx: ParserRuleContext): String? {
        val javaClass = ctx.javaClass
        val isLabeled = javaClass.superclass.canonicalName != "org.antlr.v4.runtime.ParserRuleContext"
        return if (isLabeled) {
            javaClass.simpleName.removeSuffix("Context")
        } else null
    }
}