package ris58h.webcalm.javascript

import com.intellij.codeInsight.daemon.RainbowVisitor
import com.intellij.openapi.editor.DefaultLanguageHighlighterColors
import com.intellij.psi.PsiElement
import com.intellij.psi.PsiFile
import ris58h.webcalm.javascript.psi.*

class JavaScriptRainbowVisitor : RainbowVisitor() {
    override fun suitableForFile(file: PsiFile) = file is JavaScriptFile

    override fun visit(element: PsiElement) {
        if (element !is JavaScriptIdentifier) return
        if (element.introducesName() || element.parent is JavaScriptIdentifierOwner
            || element.parent is JavaScriptBreakStatement || element.parent is JavaScriptContinueStatement
            || element.parent is JavaScriptIdentifierExpression) {
            addInfo(getInfo(element.containingFile, element, element.text, DefaultLanguageHighlighterColors.IDENTIFIER))
        }
    }

    override fun clone() = JavaScriptRainbowVisitor()
}