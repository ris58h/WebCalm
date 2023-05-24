package ris58h.webcalm.psi

import com.intellij.psi.PsiElement
import ris58h.webcalm.util.Union2

fun <F, S> PsiElement.findChildUnionByClass(firstClass: Class<F>, secondClass: Class<S>): Union2<F, S>? {
    var child: PsiElement? = firstChild
    while (child != null) {
        val childClass = child::class.java
        if (firstClass.isAssignableFrom(childClass)) return Union2.first(child as F)
        if (secondClass.isAssignableFrom(childClass)) return Union2.second(child as S)
        child = child.nextSibling
    }
    return null
}