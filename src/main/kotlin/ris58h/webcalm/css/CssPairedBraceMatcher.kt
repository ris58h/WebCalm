package ris58h.webcalm.css

import com.intellij.lang.BracePair
import com.intellij.lang.PairedBraceMatcher
import com.intellij.psi.PsiFile
import com.intellij.psi.tree.IElementType
import ris58h.webcalm.css.psi.CssTypes

class CssPairedBraceMatcher : PairedBraceMatcher {
    override fun getPairs(): Array<BracePair> = PAIRS

    override fun isPairedBracesAllowedBeforeType(lbraceType: IElementType, contextType: IElementType?): Boolean = true

    override fun getCodeConstructStart(file: PsiFile?, openingBraceOffset: Int): Int = openingBraceOffset

    companion object {
        private val PAIRS = arrayOf(
            BracePair(CssTypes.OPEN_BRACE, CssTypes.CLOSE_BRACE, true),
            BracePair(CssTypes.OPEN_BRACKET, CssTypes.CLOSE_BRACKET, true),
            BracePair(CssTypes.OPEN_PAREN, CssTypes.CLOSE_PAREN, true),
        )
    }
}