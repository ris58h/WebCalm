package ris58h.webcalm.css

import com.intellij.lang.cacheBuilder.DefaultWordsScanner
import com.intellij.psi.tree.TokenSet
import ris58h.webcalm.css.psi.CssTokenSets
import ris58h.webcalm.css.psi.CssTypes

class CssWordsScanner : DefaultWordsScanner(
    CssLexer(),
    TokenSet.create(CssTypes.VARIABLE),
    CssTokenSets.COMMENTS,
    TokenSet.orSet(
        CssTokenSets.STRINGS,
        TokenSet.create(CssTypes.NUMBER, CssTypes.DIMENSION, CssTypes.PERCENTAGE)
    )
)