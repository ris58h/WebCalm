package ris58h.webcalm.css.colorProvider

import com.intellij.openapi.editor.ElementColorProvider
import com.intellij.psi.PsiElement
import com.intellij.psi.impl.source.tree.TreeUtil
import ris58h.webcalm.css.psi.*
import java.awt.Color

class RgbCssColorProvider : ElementColorProvider {
    override fun getColorFrom(element: PsiElement): Color? {
        if (element.node.elementType == CssTypes.FUNCTION_ &&
            (element.text == "rgb(" || element.text == "rgba(")
        ) {
            val parent = element.parent
            if (parent is CssFunction) {
                return parseColor(parent.arguments)
            }
        }
        return null
    }

    override fun setColorTo(element: PsiElement, color: Color) {
        val parent = element.parent as CssFunction
        val arguments = parent.arguments
        if (color == parseColor(arguments)) return
        replaceChannel(arguments[0], color.red)
        replaceChannel(arguments[1], color.green)
        replaceChannel(arguments[2], color.blue)
        val alpha = color.alpha
        if (arguments.size == 4) {
            replaceAlpha(arguments[3], alpha)
        } else if (alpha != 255) {
            addAlpha(parent, alpha)
        }
    }

    private fun parseColor(arguments: List<CssTerm>): Color? {
        return when (arguments.size) {
            3 -> {
                val r = parseChannel(arguments[0]) ?: return null
                val g = parseChannel(arguments[1]) ?: return null
                val b = parseChannel(arguments[2]) ?: return null
                Color(r, g, b)
            }

            4 -> {
                val r = parseChannel(arguments[0]) ?: return null
                val g = parseChannel(arguments[1]) ?: return null
                val b = parseChannel(arguments[2]) ?: return null
                val a = parseAlpha(arguments[3]) ?: return null
                Color(r, g, b, a)
            }

            else -> null
        }
    }

    private fun parseChannel(argument: CssTerm): Int? {
        val value = argument.value
        return if (value.endsWith('%')) {
            parsePercent(value)
        } else {
            parseChannel(value)
        }
    }

    private fun parseAlpha(argument: CssTerm): Int? {
        val value = argument.value
        return if (value.endsWith('%')) {
            parsePercent(value)
        } else {
            parseAlpha(value)
        }
    }

    private fun parseChannel(value: String): Int? {
        val floatValue = value.toFloatOrNull() ?: return null
        return from0to255(floatValue.toInt())
    }

    private fun parseAlpha(value: String): Int? {
        val floatValue = value.toFloatOrNull() ?: return null
        return from0to255((255 * floatValue).toInt())
    }

    private fun parsePercent(value: String): Int? {
        val floatValue = value.substring(0, value.lastIndex).toFloatOrNull() ?: return null
        return from0to255((255F / 100F * floatValue).toInt())
    }

    private fun from0to255(intValue: Int): Int {
        return if (intValue < 0) 0
        else if (intValue > 255) 255
        else intValue
    }

    private fun replaceChannel(argument: CssTerm, value: Int) {
        if (value == parseChannel(argument)) return
        CssElementHelper.updateTermContent(argument, value.toString())
    }

    private fun replaceAlpha(argument: CssTerm, value: Int) {
        if (value == parseAlpha(argument)) return
        val isPercent = argument.value.endsWith('%')
        val stringValue = if (isPercent) alphaToPercentString(value) else alphaToNumberString(value)
        CssElementHelper.updateTermContent(argument, stringValue)
    }

    private fun addAlpha(function: CssFunction, value: Int) {
        val project = function.project
        val alphaArg = CssElementFactory.createTermFromText(project, alphaToPercentString(value))
        val closingParen = TreeUtil.findChildBackward(function.node, CssTypes.CLOSE_PAREN)?.psi ?: return
        if (closingParen.prevSibling is CssTerm) {
            val operator = CssElementFactory.createOperatorFromText(project, "/")
            function.addBefore(operator, closingParen)
        }
        function.addBefore(alphaArg, closingParen)
    }

    private fun alphaToNumberString(value: Int): String {
        val floatValue = value.toFloat() / 255F
        return if (floatValue <= 0F) "0"
        else if (floatValue >= 1F) "1"
        else "%.2f".format(floatValue)
    }

    private fun alphaToPercentString(value: Int): String {
        return (value.toFloat() / 255F * 100F).toInt().toString() + '%'
    }
}