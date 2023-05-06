package ris58h.webcalm.javascript

import com.intellij.lang.cacheBuilder.DefaultWordsScanner
import com.intellij.psi.tree.TokenSet
import ris58h.webcalm.javascript.psi.JavaScriptTokenSets
import ris58h.webcalm.javascript.psi.JavaScriptTypes

class JavaScriptWordsScanner : DefaultWordsScanner(
    JavaScriptLexer(),
    TokenSet.create(JavaScriptTypes.IDENTIFIER),
    JavaScriptTokenSets.COMMENTS,
    TokenSet.andSet(
        JavaScriptTokenSets.NUMBERS,
        JavaScriptTokenSets.STRINGS
    )
)