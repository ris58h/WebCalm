package ris58h.webcalm.javascript.completion

import com.intellij.codeInsight.completion.CompletionParameters
import com.intellij.codeInsight.completion.CompletionProvider
import com.intellij.codeInsight.completion.CompletionResultSet
import com.intellij.codeInsight.lookup.LookupElementBuilder
import com.intellij.psi.util.PsiTreeUtil
import com.intellij.util.ProcessingContext
import ris58h.webcalm.javascript.psi.*

class JavaScriptStatementCompletionProvider : CompletionProvider<CompletionParameters>() {
    override fun addCompletions(
        parameters: CompletionParameters,
        context: ProcessingContext,
        result: CompletionResultSet
    ) {
        result.addElement(LookupElementBuilder.create("null").bold())
        result.addElement(LookupElementBuilder.create("true").bold())
        result.addElement(LookupElementBuilder.create("false").bold())
        result.addElement(LookupElementBuilder.create("do").bold())
        result.addElement(JavaScriptLookupElements.WHILE)
        result.addElement(JavaScriptLookupElements.FOR)
        result.addElement(JavaScriptLookupElements.IF)
        result.addElement(JavaScriptLookupElements.SWITCH)
        result.addElement(LookupElementBuilder.create("break").bold())
        result.addElement(LookupElementBuilder.create("continue").bold())
        result.addElement(LookupElementBuilder.create("return").bold())
        result.addElement(LookupElementBuilder.create("yield").bold())
        result.addElement(LookupElementBuilder.create("throw").bold())
        result.addElement(JavaScriptLookupElements.TRY)
        result.addElement(LookupElementBuilder.create("var").bold())
        result.addElement(LookupElementBuilder.create("let").bold())
        result.addElement(LookupElementBuilder.create("const").bold())
        result.addElement(LookupElementBuilder.create("function").bold())
        result.addElement(LookupElementBuilder.create("new").bold())
        result.addElement(LookupElementBuilder.create("class").bold())
        result.addElement(LookupElementBuilder.create("async").bold())
        result.addElement(LookupElementBuilder.create("await").bold())
        result.addElement(LookupElementBuilder.create("void").bold())
        result.addElement(LookupElementBuilder.create("debugger").bold())
        result.addElement(LookupElementBuilder.create("with").bold())

        val statement = PsiTreeUtil.getParentOfType(parameters.position, JavaScriptStatement::class.java)
        val prevStatement = PsiTreeUtil.getPrevSiblingOfType(statement, JavaScriptStatement::class.java)
        if (prevStatement is JavaScriptIfStatement) {
            result.addElement(LookupElementBuilder.create("else").bold())
        }
        if (prevStatement is JavaScriptTryStatement) {
            result.addElement(JavaScriptLookupElements.CATCH)
            result.addElement(JavaScriptLookupElements.FINALLY)
        }

        val statementsOwner = PsiTreeUtil.getParentOfType(statement, JavaScriptStatementsOwner::class.java)
        if (statementsOwner is JavaScriptFile) {
            result.addElement(LookupElementBuilder.create("import").bold())
            result.addElement(LookupElementBuilder.create("export").bold())
        }
    }
}