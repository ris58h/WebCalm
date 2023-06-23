package ris58h.webcalm.javascript.completion

import com.intellij.codeInsight.completion.InsertHandler
import com.intellij.codeInsight.lookup.LookupElement
import com.intellij.codeInsight.lookup.LookupElementBuilder

object JavaScriptLookupElements {
    val NULL = keyword("null")
    val TRUE = keyword("true")
    val FALSE = keyword("false")
    val DO = keyword("do")
    val IF = keywordWithParentheses("if")
    val ELSE = keyword("else")
    val WHILE = keywordWithParentheses("while")
    val FOR = keywordWithParentheses("for")
    val SWITCH = keywordWithParentheses("switch")
    val BREAK = keyword("break")
    val CONTINUE = keyword("continue")
    val RETURN = keyword("return")
    val YIELD = keyword("yield")
    val THROW = keyword("throw")
    val TRY = keywordWithBlock("try")
    val CATCH = keywordWithParentheses("catch")
    val FINALLY = keywordWithBlock("finally")
    val VAR = keyword("var")
    val LET = keyword("let")
    val CONST = keyword("const")
    val FUNCTION = keyword("function")
    val NEW = keyword("new")
    val CLASS = keyword("class")
    val ASYNC = keyword("async")
    val AWAIT = keyword("await")
    val VOID = keyword("void")
    val DEBUGGER = keyword("debugger")
    val WITH = keyword("with")
    val IMPORT = keyword("import")
    val EXPORT = keyword("export")

    private fun keyword(keyword: String): LookupElement = LookupElementBuilder.create(keyword).bold()

    private fun keywordWithParentheses(keyword: String): LookupElement {
        return LookupElementBuilder.create("$keyword ()")
            .withInsertHandler(moveCaretRelatively(-1, 0))
            .withPresentableText(keyword).bold()
            .withTailText(" (...)")
    }

    private fun keywordWithBlock(keyword: String): LookupElement {
        return LookupElementBuilder.create("$keyword {\n\n}")
            .withInsertHandler(moveCaretRelatively(-1, -1))
            .withPresentableText(keyword).bold()
            .withTailText(" {...}")
    }

    private fun moveCaretRelatively(columnShift: Int, lineShift: Int): InsertHandler<LookupElement> {
        return InsertHandler { context, _ -> context.editor.caretModel.moveCaretRelatively(columnShift, lineShift, false, false, true) }
    }
}