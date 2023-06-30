package ris58h.webcalm.css.completion

import com.intellij.codeInsight.completion.CompletionContributor
import com.intellij.codeInsight.completion.CompletionType
import com.intellij.patterns.PlatformPatterns
import com.intellij.psi.PsiErrorElement
import ris58h.webcalm.css.psi.*

class CssCompletionContributor : CompletionContributor() {
    init {
        extend(
            CompletionType.BASIC,
            PlatformPatterns.or(PROPERTY_NAME_PATTERN, INCOMPLETE_PROPERTY_NAME_PATTERN),
            CssPropertyNameCompletionProvider()
        )
        extend(
            CompletionType.BASIC,
            PlatformPatterns.or(TERM_PATTERN, ANY_VALUE_IDENTIFIER_PATTERN),
            CssPropertyValueCompletionProvider()
        )
    }
}

private val PROPERTY_NAME_PATTERN = PlatformPatterns.psiElement(CssTypes.IDENTIFIER)
    .withParent(PlatformPatterns.psiElement(CssIdentifier::class.java)
        .withParent(PlatformPatterns.psiElement(CssProperty::class.java)))
private val INCOMPLETE_PROPERTY_NAME_PATTERN = PlatformPatterns.psiElement(CssTypes.IDENTIFIER)
    .withParent(PlatformPatterns.psiElement(PsiErrorElement::class.java)
        .withParent(PlatformPatterns.psiElement(CssRuleSet::class.java)
            .withParent(PlatformPatterns.psiElement(CssNestedStatement::class.java))))

private val TERM_PATTERN = PlatformPatterns.psiElement(CssTypes.IDENTIFIER)
    .withParent(PlatformPatterns.psiElement(CssIdentifier::class.java)
        .withParent(PlatformPatterns.psiElement(CssTerm::class.java)))
private val ANY_VALUE_IDENTIFIER_PATTERN = PlatformPatterns.psiElement(CssTypes.IDENTIFIER)
    .withParent(PlatformPatterns.psiElement(CssIdentifier::class.java)
        .inside(PlatformPatterns.psiElement(CssValue::class.java)))