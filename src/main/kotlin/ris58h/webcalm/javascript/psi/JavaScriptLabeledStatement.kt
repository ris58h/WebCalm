package ris58h.webcalm.javascript.psi

import com.intellij.lang.ASTNode
import com.intellij.psi.search.LocalSearchScope
import com.intellij.psi.search.SearchScope

class JavaScriptLabeledStatement(node: ASTNode) : JavaScriptIdentifierOwner(node), JavaScriptStatement {
    val statement: JavaScriptStatement?
        get() = this.findChildByClass(JavaScriptStatement::class.java)

    override fun getUseScope(): SearchScope = LocalSearchScope(this)
}