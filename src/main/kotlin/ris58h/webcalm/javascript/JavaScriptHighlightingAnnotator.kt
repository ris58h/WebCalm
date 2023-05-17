package ris58h.webcalm.javascript

import com.intellij.lang.annotation.AnnotationHolder
import com.intellij.lang.annotation.Annotator
import com.intellij.lang.annotation.HighlightSeverity
import com.intellij.openapi.editor.DefaultLanguageHighlighterColors
import com.intellij.psi.PsiElement
import com.intellij.psi.util.parentOfType
import ris58h.webcalm.javascript.psi.*

class JavaScriptHighlightingAnnotator : Annotator {
    override fun annotate(element: PsiElement, holder: AnnotationHolder) {
        if (element !is JavaScriptIdentifier) return

        val color = when (val parent = element.parent) {
            is JavaScriptAssignable -> {
                when (val parent2 = parent.parent) {
                    is JavaScriptParameter -> DefaultLanguageHighlighterColors.PARAMETER
                    is JavaScriptVariableDeclaration -> {
                        val statement = parent2.parentOfType<JavaScriptVariableStatement>()
                        // TODO: only 'var' creates a global variable
                        val isGlobal = statement?.parent is JavaScriptFile
                        if (isGlobal) DefaultLanguageHighlighterColors.GLOBAL_VARIABLE
                        else DefaultLanguageHighlighterColors.LOCAL_VARIABLE
                    }

                    else -> null
                }
            }

            is JavaScriptFunctionDeclaration -> {
                if (parent.identifier === element) DefaultLanguageHighlighterColors.FUNCTION_DECLARATION
                else null
            }

            is JavaScriptIdentifierExpression -> {
                // TODO: check for a param with the same name that can override predefined globals
                val name = element.name
                when {
                    GLOBAL_VALUES.contains(name) -> DefaultLanguageHighlighterColors.KEYWORD
                    GLOBAL_FUNCTIONS.contains(name) -> DefaultLanguageHighlighterColors.PREDEFINED_SYMBOL
                    GLOBAL_OBJECTS.contains(name) -> DefaultLanguageHighlighterColors.CLASS_REFERENCE
                    GLOBAL_NAMESPACES.contains(name) -> DefaultLanguageHighlighterColors.PREDEFINED_SYMBOL
                    else -> null
                }
            }

            else -> null
        }

        if (color != null) {
            holder.newSilentAnnotation(HighlightSeverity.INFORMATION)
                .textAttributes(color)
                .create()
        }
    }

    companion object {
        private val GLOBAL_VALUES = setOf(
            "globalThis",
            "Infinity",
            "NaN",
            "undefined",
        )
        private val GLOBAL_FUNCTIONS = setOf(
            "eval",
            "isFinite",
            "isNaN",
            "parseFloat",
            "parseInt",
            "decodeURI",
            "decodeURIComponent",
            "encodeURI",
            "encodeURIComponent",
            "escape",
            "unescape",
        )
        private val GLOBAL_OBJECTS = setOf(
            // Fundamental objects
            "Object",
            "Function",
            "Boolean",
            "Symbol",
            // Error objects
            "Error",
            "AggregateError",
            "EvalError",
            "RangeError",
            "ReferenceError",
            "SyntaxError",
            "TypeError",
            "URIError",
            "InternalError",
            // Numbers and dates
            "Number",
            "BigInt",
            "Date",
            // Text processing
            "String",
            "Date",
            // Indexed collections
            "Array",
            "Int8Array",
            "Uint8Array",
            "Uint8ClampedArray",
            "Int16Array",
            "UintArray",
            "Int32Array",
            "Uint32Array",
            "BigInt64Array",
            "BigUint64Array",
            "Float32Array",
            "Float64Array",
            // Keyed collections
            "Map",
            "Set",
            "WeakMap",
            "WeakSet",
            // Structured data
            "ArrayBuffer",
            "SharedArrayBuffer",
            "DataView",
            // Managing memory
            "WeakRef",
            "FinalizationRegistry",
            // Control abstraction objects
            "Iterator",
            "AsyncIterator",
            "Promise",
            "GeneratorFunction",
            "AsyncGeneratorFunction",
            "Generator",
            "AsyncGenerator",
            "AsyncFunction",
            // Reflection
            "Proxy",
        )
        private val GLOBAL_NAMESPACES = setOf(
            "Math",
            "Atomics",
            "JSON",
            "Reflect",
            "Intl",
        )
    }
}