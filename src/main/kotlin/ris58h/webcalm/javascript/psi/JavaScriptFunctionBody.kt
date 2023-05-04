package ris58h.webcalm.javascript.psi

import com.intellij.extapi.psi.ASTWrapperPsiElement
import com.intellij.lang.ASTNode

class JavaScriptFunctionBody(node: ASTNode) : ASTWrapperPsiElement(node), JavaScriptStatementsOwner