package ris58h.webcalm.javascript

import com.intellij.lang.BracePair
import com.intellij.lang.PairedBraceMatcher
import com.intellij.psi.PsiFile
import com.intellij.psi.tree.IElementType
import ris58h.webcalm.javascript.psi.JavaScriptTypes

class JavaScriptPairedBraceMatcher : PairedBraceMatcher {
    override fun getPairs() = PAIRS
    override fun isPairedBracesAllowedBeforeType(lbraceType: IElementType, contextType: IElementType?) = true
    override fun getCodeConstructStart(file: PsiFile?, openingBraceOffset: Int) = openingBraceOffset
}

private val PAIRS = arrayOf(
    BracePair(JavaScriptTypes.OPEN_BRACE, JavaScriptTypes.CLOSE_BRACE, true),
    BracePair(JavaScriptTypes.OPEN_BRACKET, JavaScriptTypes.CLOSE_BRACKET, true),
    BracePair(JavaScriptTypes.OPEN_PAREN, JavaScriptTypes.CLOSE_PAREN, true),
)
