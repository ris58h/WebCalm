package ris58h.webcalm.css.completion

import com.intellij.codeInsight.completion.CompletionContributor
import com.intellij.codeInsight.completion.CompletionType
import com.intellij.patterns.PlatformPatterns.*
import com.intellij.psi.PsiErrorElement
import ris58h.webcalm.css.psi.*

class CssCompletionContributor : CompletionContributor() {
    init {
        extend(
            CompletionType.BASIC,
            or(PROPERTY_NAME_PATTERN, INCOMPLETE_PROPERTY_NAME_PATTERN, INCOMPLETE_PROPERTY_NAME_PATTERN2),
            CssPropertyNameCompletionProvider()
        )
        extend(
            CompletionType.BASIC,
            or(IDENTIFIER_TERM_PATTERN, ANY_VALUE_IDENTIFIER_PATTERN),
            CssPropertyValueCompletionProvider()
        )
        extend(
            CompletionType.BASIC,
            or(AT_KEYWORD_PATTERN),
            CssAtKeywordCompletionProvider()
        )
    }

    companion object {
        private val PROPERTY_NAME_PATTERN = psiElement(CssTypes.IDENTIFIER)
            .withParent(
                psiElement(CssIdentifier::class.java)
                    .withParent(psiElement(CssProperty::class.java))
            )
        private val INCOMPLETE_PROPERTY_NAME_PATTERN = psiElement(CssTypes.IDENTIFIER)
            .withParent(
                psiElement(PsiErrorElement::class.java)
                    .withParent(
                        psiElement(CssRuleSet::class.java)
                    )
            )
        private val INCOMPLETE_PROPERTY_NAME_PATTERN2 = psiElement(CssTypes.IDENTIFIER)
            .withParent(
                psiElement(CssIdentifier::class.java)
                    .withParent(
                        psiElement()
                            .withParent(
                                psiElement(CssRuleSet::class.java)
                            )
                    )
            )

        val IDENTIFIER_TERM_PATTERN = psiElement(CssTypes.IDENTIFIER)
            .withParent(
                psiElement(CssIdentifier::class.java)
                    .withParent(psiElement(CssTerm::class.java))
            )
        private val ANY_VALUE_IDENTIFIER_PATTERN = psiElement(CssTypes.IDENTIFIER)
            .withParent(
                psiElement(CssIdentifier::class.java)
                    .inside(psiElement(CssValue::class.java))
            )

        private val AT_KEYWORD_PATTERN = psiElement(CssTypes.AT_KEYWORD)
    }
}
