package ris58h.webcalm.javascript

import com.intellij.lang.cacheBuilder.WordsScanner
import com.intellij.lang.findUsages.EmptyFindUsagesProvider
import com.intellij.psi.PsiElement
import ris58h.webcalm.javascript.psi.JavaScriptIdentifier
import ris58h.webcalm.javascript.psi.JavaScriptIdentifierOwner

class JavaScriptFindUsagesProvider : EmptyFindUsagesProvider() {
    override fun getWordsScanner(): WordsScanner = JavaScriptWordsScanner()

    override fun canFindUsagesFor(element: PsiElement): Boolean {
        return element is JavaScriptIdentifier
                || element is JavaScriptIdentifierOwner
    }
}