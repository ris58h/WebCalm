package ris58h.webcalm.javascript.psi

import com.intellij.lang.ASTNode
import com.intellij.psi.util.PsiTreeUtil

class JavaScriptClassDeclaration(node: ASTNode) : JavaScriptIdentifierOwner(node), JavaScriptStatement, JavaScriptDeclaration {
    val classElements: List<JavaScriptClassElement>
        get() = PsiTreeUtil.getChildrenOfTypeAsList(this, JavaScriptClassElement::class.java)
}