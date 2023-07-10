package ris58h.webcalm.javascript

import com.intellij.lang.annotation.AnnotationHolder
import com.intellij.lang.annotation.Annotator
import com.intellij.lang.annotation.HighlightSeverity
import com.intellij.openapi.editor.DefaultLanguageHighlighterColors
import com.intellij.openapi.editor.colors.TextAttributesKey
import com.intellij.openapi.project.DumbAware
import com.intellij.psi.PsiElement
import ris58h.webcalm.javascript.psi.*

class JavaScriptHighlightingAnnotator : Annotator, DumbAware {
    // TODO: A variable/parameter should be marked in all places where it's defined or used. The same for a reassigned variable/parameter.
    // TODO: REASSIGNED_PARAMETER
    override fun annotate(element: PsiElement, holder: AnnotationHolder) {
        if (element !is JavaScriptIdentifier) return

        val color = when (val parent = element.parent) {
            is JavaScriptVariableDeclaration -> DefaultLanguageHighlighterColors.LOCAL_VARIABLE
            is JavaScriptFormalParameter -> DefaultLanguageHighlighterColors.PARAMETER
            is JavaScriptIdentifierExpression -> colorWhenParentIsIdentifierExpression(parent, element)
            is JavaScriptFunctionDeclaration -> {
                if (parent.identifier === element) DefaultLanguageHighlighterColors.FUNCTION_DECLARATION
                else null
            }
            is JavaScriptClassElement -> {
                if (element.text == "static") DefaultLanguageHighlighterColors.KEYWORD
                else null
            }
            is JavaScriptMethodDefinition -> {
                val text = element.text
                if (text == "get" || text == "set") DefaultLanguageHighlighterColors.KEYWORD
                else null
            }
            else -> null
        }

        if (color != null) {
            holder.newSilentAnnotation(HighlightSeverity.INFORMATION)
                .textAttributes(color)
                .create()
        }
    }

    private fun colorWhenParentIsIdentifierExpression(parent: JavaScriptIdentifierExpression, element: JavaScriptIdentifier): TextAttributesKey? {
        return when (val parent2 = parent.parent) {
            is JavaScriptCallExpression -> DefaultLanguageHighlighterColors.FUNCTION_CALL
            is JavaScriptUpdateExpression -> DefaultLanguageHighlighterColors.REASSIGNED_LOCAL_VARIABLE
            is JavaScriptAssignmentExpression -> {
                if (parent2.firstChild === parent) DefaultLanguageHighlighterColors.REASSIGNED_LOCAL_VARIABLE
                else  null
            }
            is JavaScriptFormalRestParameter -> DefaultLanguageHighlighterColors.PARAMETER
            else -> {
                // TODO: check for a param with the same name that can override predefined globals
                val name = element.name
                when {
                    JavaScriptBuiltInObjects.isGlobalValue(name) -> DefaultLanguageHighlighterColors.KEYWORD
                    JavaScriptBuiltInObjects.isGlobalFunction(name) -> DefaultLanguageHighlighterColors.PREDEFINED_SYMBOL
                    JavaScriptBuiltInObjects.isGlobalObject(name) -> DefaultLanguageHighlighterColors.CLASS_REFERENCE
                    JavaScriptBuiltInObjects.isGlobalNamespace(name) -> DefaultLanguageHighlighterColors.PREDEFINED_SYMBOL
                    else -> null
                }
            }
        }
    }
}