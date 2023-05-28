package ris58h.webcalm.javascript.psi

import com.intellij.extapi.psi.ASTWrapperPsiElement
import com.intellij.lang.ASTNode

class JavaScriptCaseClause(node: ASTNode) : ASTWrapperPsiElement(node), JavaScriptStatementsOwner