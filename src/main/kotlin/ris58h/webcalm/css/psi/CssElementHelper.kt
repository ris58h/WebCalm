package ris58h.webcalm.css.psi

object CssElementHelper {
    fun updateTermContent(term: CssTerm, newTermText: String) {
        val newTerm = CssElementFactory.createTermFromText(term.project, newTermText)
        // TODO: don't include Space token in term rule.
        // We have to replace the first child instead of the term because the term may contain Space leaf as the second child.
        term.firstChild.replace(newTerm.firstChild)
    }
}