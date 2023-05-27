package ris58h.webcalm.javascript.psi

import com.intellij.lang.ASTNode

class JavaScriptLabeledStatement(node: ASTNode) : JavaScriptIdentifierOwner(node), JavaScriptStatement {
    val block: JavaScriptBlock?
        get() = this.findChildByClass(JavaScriptBlock::class.java)
}