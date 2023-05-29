package ris58h.webcalm.javascript

import com.intellij.codeInsight.completion.CompletionContributor
import com.intellij.codeInsight.completion.CompletionType
import com.intellij.patterns.PlatformPatterns
import ris58h.webcalm.javascript.psi.*

class JavaScriptCompletionContributor: CompletionContributor() {
    init {
        val statementIdentifierPattern = PlatformPatterns.psiElement(JavaScriptTypes.IDENTIFIER).withParent(
            PlatformPatterns.psiElement(JavaScriptIdentifier::class.java).withParent(
                PlatformPatterns.psiElement(JavaScriptIdentifierExpression::class.java).withParent(
                    PlatformPatterns.psiElement(JavaScriptExpressionSequence::class.java).withParent(
                        PlatformPatterns.psiElement(JavaScriptExpressionStatement::class.java)
                    )
                )
            )
        )
        extend(CompletionType.BASIC, statementIdentifierPattern, JavaScriptStatementCompletionProvider())
    }
}