package ris58h.webcalm.css.psi

import com.intellij.extapi.psi.ASTWrapperPsiElement
import com.intellij.lang.ASTNode
import com.intellij.psi.util.PsiTreeUtil

class CssFontFeatureValuesRule(node: ASTNode) : ASTWrapperPsiElement(node), CssNestedStatement {
    val featureValueBlocks: List<CssFeatureValueBlock>
        get() = PsiTreeUtil.getChildrenOfTypeAsList(this, CssFeatureValueBlock::class.java)
}