package ris58h.webcalm.css

import com.intellij.lang.ASTFactory
import com.intellij.psi.impl.source.tree.LeafElement
import com.intellij.psi.tree.IElementType
import ris58h.webcalm.css.psi.CssTypes
import ris58h.webcalm.css.psi.CssVariable

class CssASTFactory : ASTFactory() {
    override fun createLeaf(type: IElementType, text: CharSequence): LeafElement? {
        if (type == CssTypes.VARIABLE) {
            return CssVariable(text)
        }
        return super.createLeaf(type, text)
    }
}