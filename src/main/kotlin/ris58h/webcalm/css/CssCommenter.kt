package ris58h.webcalm.css

import com.intellij.lang.Commenter

class CssCommenter : Commenter {
    override fun getLineCommentPrefix() = null

    override fun getBlockCommentPrefix() = "/*"

    override fun getBlockCommentSuffix() = "*/"

    override fun getCommentedBlockCommentPrefix() = null

    override fun getCommentedBlockCommentSuffix() = null
}