package ris58h.webcalm.javascript

import com.intellij.codeInsight.completion.CompletionContributor
import com.intellij.codeInsight.completion.CompletionType
import com.intellij.patterns.PlatformPatterns
import com.intellij.patterns.PsiElementPattern
import com.intellij.psi.PsiElement
import ris58h.webcalm.javascript.psi.*

class JavaScriptCompletionContributor: CompletionContributor() {
    init {
        extend(CompletionType.BASIC, STATEMENT_IDENTIFIER_PATTERN, JavaScriptStatementCompletionProvider())
        extend(CompletionType.BASIC, RETURN_IDENTIFIER_PATTERN, JavaScriptReturnCompletionProvider())
        extend(CompletionType.BASIC, RIGHT_HAND_IDENTIFIER_PATTERN, JavaScriptRightHandCompletionProvider())
    }
}

private val STATEMENT_IDENTIFIER_PATTERN = identifierExpressionWithParent(
    PlatformPatterns.psiElement(JavaScriptExpressionSequence::class.java)
        .withParent(PlatformPatterns.psiElement(JavaScriptExpressionStatement::class.java))
)
private val RETURN_IDENTIFIER_PATTERN = identifierExpressionWithParent(
    PlatformPatterns.psiElement(JavaScriptExpressionSequence::class.java)
        .withParent(PlatformPatterns.psiElement(JavaScriptReturnStatement::class.java))
)
private val RIGHT_HAND_IDENTIFIER_PATTERN = identifierExpressionWithParent(
    PlatformPatterns.psiElement(JavaScriptVariableDeclaration::class.java)
)

private fun identifierExpressionWithParent(parent: PsiElementPattern.Capture<out PsiElement>): PsiElementPattern.Capture<PsiElement> {
    return PlatformPatterns.psiElement(JavaScriptTypes.IDENTIFIER)
        .withParent(PlatformPatterns.psiElement(JavaScriptIdentifier::class.java)
            .withParent(PlatformPatterns.psiElement(JavaScriptIdentifierExpression::class.java)
                .withParent(parent)))
}
