package ris58h.webcalm.javascript.psi

import com.intellij.extapi.psi.ASTWrapperPsiElement
import com.intellij.lang.ASTNode
import com.intellij.psi.util.PsiTreeUtil

class JavaScriptCaseBlock(node: ASTNode) : ASTWrapperPsiElement(node) {
    val cases: List<JavaScriptCaseClause>
        get() = PsiTreeUtil.getChildrenOfTypeAsList(this, JavaScriptCaseClause::class.java)

    val defaultCase: JavaScriptDefaultClause?
        get() = this.findChildByClass(JavaScriptDefaultClause::class.java)
}