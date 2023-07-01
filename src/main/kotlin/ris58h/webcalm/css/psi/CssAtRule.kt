package ris58h.webcalm.css.psi

import com.intellij.extapi.psi.ASTWrapperPsiElement
import com.intellij.lang.ASTNode

// Other @ rules
class CssAtRule(node: ASTNode) : ASTWrapperPsiElement(node), CssNestedStatement