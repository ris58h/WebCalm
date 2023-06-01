package ris58h.webcalm.javascript.psi

import com.intellij.psi.PsiElement
import ris58h.webcalm.psi.findChildUnionByClass
import ris58h.webcalm.util.Union3

typealias JavaScriptAssignable = Union3<JavaScriptIdentifier, JavaScriptArray, JavaScriptObject>

fun findAssignableChild(element: PsiElement): JavaScriptAssignable? {
    return element.findChildUnionByClass(
        JavaScriptIdentifier::class.java,
        JavaScriptArray::class.java,
        JavaScriptObject::class.java
    )
}