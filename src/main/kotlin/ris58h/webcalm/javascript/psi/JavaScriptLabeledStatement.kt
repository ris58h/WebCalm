package ris58h.webcalm.javascript.psi

import com.intellij.lang.ASTNode
import com.intellij.psi.search.LocalSearchScope
import com.intellij.psi.search.SearchScope

class JavaScriptLabeledStatement(node: ASTNode) : JavaScriptNamedIdentifierOwner(node), JavaScriptStatement {
    val block: JavaScriptBlock?
        get() = this.findChildByClass(JavaScriptBlock::class.java)

    override fun getUseScope(): SearchScope {
        val block = block
        return if (block != null) LocalSearchScope(block) else super.getUseScope()
    }
}