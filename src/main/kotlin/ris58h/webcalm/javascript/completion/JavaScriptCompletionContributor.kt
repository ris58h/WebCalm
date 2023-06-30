package ris58h.webcalm.javascript.completion

import com.intellij.codeInsight.completion.CompletionContributor
import com.intellij.codeInsight.completion.CompletionType
import com.intellij.patterns.PlatformPatterns.*
import com.intellij.patterns.PsiElementPattern
import com.intellij.psi.PsiElement
import ris58h.webcalm.javascript.psi.*

class JavaScriptCompletionContributor : CompletionContributor() {
    init {
        extend(CompletionType.BASIC, STATEMENT_IDENTIFIER_PATTERN, JavaScriptStatementCompletionProvider())
        extend(CompletionType.BASIC, RETURN_IDENTIFIER_PATTERN, JavaScriptReturnCompletionProvider())
        extend(CompletionType.BASIC, RIGHT_HAND_IDENTIFIER_PATTERN, JavaScriptRightHandCompletionProvider())
    }
}

private val STATEMENT_IDENTIFIER_PATTERN = identifierExpressionWithParent(
    psiElement(JavaScriptExpressionSequence::class.java)
        .withParent(psiElement(JavaScriptExpressionStatement::class.java))
)
private val RETURN_IDENTIFIER_PATTERN = identifierExpressionWithParent(
    psiElement(JavaScriptExpressionSequence::class.java)
        .withParent(psiElement(JavaScriptReturnStatement::class.java))
)
private val RIGHT_HAND_IDENTIFIER_PATTERN = identifierExpressionWithParent(
    psiElement(JavaScriptVariableDeclaration::class.java)
)

private fun identifierExpressionWithParent(parent: PsiElementPattern.Capture<out PsiElement>): PsiElementPattern.Capture<PsiElement> {
    return psiElement(JavaScriptTypes.IDENTIFIER)
        .withParent(
            psiElement(JavaScriptIdentifier::class.java)
                .withParent(
                    psiElement(JavaScriptIdentifierExpression::class.java)
                        .withParent(parent)
                )
        )
}
