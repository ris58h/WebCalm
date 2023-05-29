package ris58h.webcalm.javascript

import com.intellij.codeInsight.completion.CompletionParameters
import com.intellij.codeInsight.completion.CompletionProvider
import com.intellij.codeInsight.completion.CompletionResultSet
import com.intellij.codeInsight.lookup.LookupElementBuilder
import com.intellij.psi.util.PsiTreeUtil
import com.intellij.util.ProcessingContext
import ris58h.webcalm.javascript.psi.JavaScriptFile
import ris58h.webcalm.javascript.psi.JavaScriptIfStatement
import ris58h.webcalm.javascript.psi.JavaScriptStatement
import ris58h.webcalm.javascript.psi.JavaScriptStatementsOwner

class JavaScriptStatementCompletionProvider : CompletionProvider<CompletionParameters>() {
    override fun addCompletions(
        parameters: CompletionParameters,
        context: ProcessingContext,
        result: CompletionResultSet
    ) {
        result.addElement(LookupElementBuilder.create("true").bold())
        result.addElement(LookupElementBuilder.create("false").bold())
        result.addElement(LookupElementBuilder.create("do").bold())
        result.addElement(LookupElementBuilder.create("while").bold())
        result.addElement(LookupElementBuilder.create("for").bold())
        result.addElement(LookupElementBuilder.create("if").bold())
        result.addElement(LookupElementBuilder.create("switch").bold())
        result.addElement(LookupElementBuilder.create("break").bold())
        result.addElement(LookupElementBuilder.create("continue").bold())
        result.addElement(LookupElementBuilder.create("return").bold())
        result.addElement(LookupElementBuilder.create("yield").bold())
        result.addElement(LookupElementBuilder.create("throw").bold())
        result.addElement(LookupElementBuilder.create("try").bold())
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

        val statementsOwner = PsiTreeUtil.getParentOfType(statement, JavaScriptStatementsOwner::class.java)
        if (statementsOwner is JavaScriptFile) {
            result.addElement(LookupElementBuilder.create("import").bold())
            result.addElement(LookupElementBuilder.create("export").bold())
        }
    }
}