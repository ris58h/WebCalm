package ris58h.webcalm.javascript

import com.intellij.lang.cacheBuilder.DefaultWordsScanner
import com.intellij.psi.tree.TokenSet
import ris58h.webcalm.javascript.psi.JavaScriptTokenSets
import ris58h.webcalm.javascript.psi.JavaScriptTypes

class JavaScriptWordsScanner : DefaultWordsScanner(
    JavaScriptLexer(),
    TokenSet.create(JavaScriptTypes.IDENTIFIER),
    JavaScriptTokenSets.COMMENTS,
    TokenSet.orSet(
        JavaScriptTokenSets.NUMBERS,
        JavaScriptTokenSets.STRINGS,
        TokenSet.create(JavaScriptTypes.NULL_LITERAL, JavaScriptTypes.BOOLEAN_LITERAL)
    )
)