package ris58h.webcalm.css.completion

import com.intellij.codeInsight.completion.CompletionContributor
import com.intellij.codeInsight.completion.CompletionType
import com.intellij.patterns.PlatformPatterns
import ris58h.webcalm.css.psi.CssIdentifier
import ris58h.webcalm.css.psi.CssTerm
import ris58h.webcalm.css.psi.CssTypes
import ris58h.webcalm.css.psi.CssValue

class CssCompletionContributor : CompletionContributor() {
    init {
        extend(CompletionType.BASIC, TERM_PATTERN, CssPropertyValueCompletionProvider())
        extend(CompletionType.BASIC, ANY_VALUE_IDENTIFIER_PATTERN, CssPropertyValueCompletionProvider())
    }
}

private val TERM_PATTERN = PlatformPatterns.psiElement(CssTypes.IDENTIFIER)
    .withParent(PlatformPatterns.psiElement(CssIdentifier::class.java)
        .withParent(PlatformPatterns.psiElement(CssTerm::class.java)))

private val ANY_VALUE_IDENTIFIER_PATTERN = PlatformPatterns.psiElement(CssTypes.IDENTIFIER)
    .withParent(PlatformPatterns.psiElement(CssIdentifier::class.java)
        .inside(PlatformPatterns.psiElement(CssValue::class.java)))