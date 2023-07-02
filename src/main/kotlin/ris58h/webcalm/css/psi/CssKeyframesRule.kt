package ris58h.webcalm.css.psi

import com.intellij.extapi.psi.ASTWrapperPsiElement
import com.intellij.lang.ASTNode
import com.intellij.psi.util.PsiTreeUtil

class CssKeyframesRule(node: ASTNode) : ASTWrapperPsiElement(node), CssNestedStatement {
    val keyframeBlocks: List<CssKeyframeBlock>
        get() = PsiTreeUtil.getChildrenOfTypeAsList(this, CssKeyframeBlock::class.java)
}