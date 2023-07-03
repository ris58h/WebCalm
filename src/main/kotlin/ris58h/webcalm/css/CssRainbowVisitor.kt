package ris58h.webcalm.css

import com.intellij.codeInsight.daemon.RainbowVisitor
import com.intellij.openapi.editor.DefaultLanguageHighlighterColors
import com.intellij.psi.PsiElement
import com.intellij.psi.PsiFile
import ris58h.webcalm.css.psi.CssFile
import ris58h.webcalm.css.psi.CssVariable

class CssRainbowVisitor : RainbowVisitor() {
    override fun suitableForFile(file: PsiFile) = file is CssFile

    override fun visit(element: PsiElement) {
        if (element is CssVariable) {
            addInfo(getInfo(element.containingFile, element, element.text, DefaultLanguageHighlighterColors.IDENTIFIER))
        }
    }

    override fun clone() = CssRainbowVisitor()
}