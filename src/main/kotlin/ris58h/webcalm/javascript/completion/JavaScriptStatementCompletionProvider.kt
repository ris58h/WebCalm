package ris58h.webcalm.javascript.completion

import com.intellij.codeInsight.completion.CompletionParameters
import com.intellij.codeInsight.completion.CompletionProvider
import com.intellij.codeInsight.completion.CompletionResultSet
import com.intellij.psi.util.PsiTreeUtil
import com.intellij.util.ProcessingContext
import ris58h.webcalm.javascript.psi.*

class JavaScriptStatementCompletionProvider : CompletionProvider<CompletionParameters>() {
    override fun addCompletions(
        parameters: CompletionParameters,
        context: ProcessingContext,
        result: CompletionResultSet
    ) {
        result.addElement(JavaScriptLookupElements.NULL)
        result.addElement(JavaScriptLookupElements.TRUE)
        result.addElement(JavaScriptLookupElements.FALSE)
        result.addElement(JavaScriptLookupElements.DO)
        result.addElement(JavaScriptLookupElements.WHILE)
        result.addElement(JavaScriptLookupElements.FOR)
        result.addElement(JavaScriptLookupElements.IF)
        result.addElement(JavaScriptLookupElements.SWITCH)
        result.addElement(JavaScriptLookupElements.BREAK)
        result.addElement(JavaScriptLookupElements.CONTINUE)
        result.addElement(JavaScriptLookupElements.RETURN)
        result.addElement(JavaScriptLookupElements.YIELD)
        result.addElement(JavaScriptLookupElements.THROW)
        result.addElement(JavaScriptLookupElements.TRY)
        result.addElement(JavaScriptLookupElements.VAR)
        result.addElement(JavaScriptLookupElements.LET)
        result.addElement(JavaScriptLookupElements.CONST)
        result.addElement(JavaScriptLookupElements.FUNCTION)
        result.addElement(JavaScriptLookupElements.NEW)
        result.addElement(JavaScriptLookupElements.CLASS)
        result.addElement(JavaScriptLookupElements.ASYNC)
        result.addElement(JavaScriptLookupElements.AWAIT)
        result.addElement(JavaScriptLookupElements.VOID)
        result.addElement(JavaScriptLookupElements.DEBUGGER)
        result.addElement(JavaScriptLookupElements.WITH)

        val statement = PsiTreeUtil.getParentOfType(parameters.position, JavaScriptStatement::class.java)
        val prevStatement = PsiTreeUtil.getPrevSiblingOfType(statement, JavaScriptStatement::class.java)
        if (prevStatement is JavaScriptIfStatement) {
            result.addElement(JavaScriptLookupElements.ELSE)
        }
        if (prevStatement is JavaScriptTryStatement) {
            result.addElement(JavaScriptLookupElements.CATCH)
            result.addElement(JavaScriptLookupElements.FINALLY)
        }

        val statementsOwner = PsiTreeUtil.getParentOfType(statement, JavaScriptStatementsOwner::class.java)
        if (statementsOwner is JavaScriptFile) {
            result.addElement(JavaScriptLookupElements.IMPORT)
            result.addElement(JavaScriptLookupElements.EXPORT)
        }
    }
}