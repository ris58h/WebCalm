package ris58h.webcalm.javascript.psi

import com.intellij.extapi.psi.ASTWrapperPsiElement
import com.intellij.lang.ASTNode

class JavaScriptBlock(node: ASTNode) : ASTWrapperPsiElement(node), JavaScriptStatement, JavaScriptStatementList