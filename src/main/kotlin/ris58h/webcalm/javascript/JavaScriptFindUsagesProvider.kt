package ris58h.webcalm.javascript

import com.intellij.lang.cacheBuilder.WordsScanner
import com.intellij.lang.findUsages.EmptyFindUsagesProvider
import com.intellij.psi.PsiElement
import ris58h.webcalm.javascript.psi.JavaScriptIdentifier

class JavaScriptFindUsagesProvider : EmptyFindUsagesProvider() {
    override fun getWordsScanner(): WordsScanner = JavaScriptWordsScanner()

    override fun canFindUsagesFor(psiElement: PsiElement): Boolean = psiElement is JavaScriptIdentifier
}