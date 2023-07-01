package ris58h.webcalm.css

import com.intellij.ide.projectView.PresentationData
import com.intellij.ide.structureView.*
import com.intellij.ide.util.treeView.smartTree.TreeElement
import com.intellij.lang.PsiStructureViewFactory
import com.intellij.navigation.ItemPresentation
import com.intellij.openapi.editor.Editor
import com.intellij.psi.NavigatablePsiElement
import com.intellij.psi.PsiElement
import com.intellij.psi.PsiFile
import ris58h.webcalm.css.psi.*

class CssStructureViewFactory : PsiStructureViewFactory {
    override fun getStructureViewBuilder(psiFile: PsiFile) =
        object : TreeBasedStructureViewBuilder() {
            override fun createStructureViewModel(editor: Editor?) = createModel(psiFile, editor)
        }
}

private fun createModel(psiFile: PsiFile, editor: Editor?): StructureViewModel {
    return object : StructureViewModelBase(psiFile, editor, CssStructureViewElement(psiFile)),
        StructureViewModel.ElementInfoProvider {
        override fun isAlwaysShowsPlus(element: StructureViewTreeElement?): Boolean = false
        override fun isAlwaysLeaf(element: StructureViewTreeElement): Boolean = false
    }.withSuitableClasses(
        CssNestedStatement::class.java,
        CssFeatureValueBlock::class.java,
    )
}

private class CssStructureViewElement(private val myElement: NavigatablePsiElement) : StructureViewTreeElement {
    override fun getValue() = myElement
    override fun navigate(requestFocus: Boolean) = myElement.navigate(requestFocus)
    override fun canNavigate(): Boolean = myElement.canNavigate()
    override fun canNavigateToSource(): Boolean = myElement.canNavigateToSource()

    override fun getPresentation(): ItemPresentation {
        return when (myElement) {
            is CssNestedStatement -> PresentationData(presentableText(myElement), null, null, null)
            is CssFeatureValueBlock -> PresentationData(presentableText(myElement), null, null, null)
            else -> myElement.presentation ?: PresentationData()
        }
    }

    private fun presentableText(element: CssNestedStatement): String = substringBeforeOpenBrace(element)

    private fun presentableText(element: CssFeatureValueBlock): String = substringBeforeOpenBrace(element)

    private fun substringBeforeOpenBrace(element: PsiElement): String {
        // TODO a hack to get statement's description
        return element.text.substringBefore('{').trim()
    }

    override fun getChildren(): Array<TreeElement> {
        return when (myElement) {
            is CssFile -> nestedStatementsToTreeChildren(myElement.statements)
            // TODO: other statements that can have nested statements (@keyframes, etc.)
            // CssNestedStatement cases
            is CssMediaRule -> nestedStatementsToTreeChildren(myElement.statements)
            is CssKeyframesRule -> TreeElement.EMPTY_ARRAY
            is CssSupportsRule -> nestedStatementsToTreeChildren(myElement.statements)
            is CssFontFeatureValuesRule -> myElement.featureValueBlocks.map(::CssStructureViewElement).toTypedArray()
            is CssAtRule -> TreeElement.EMPTY_ARRAY

            else -> TreeElement.EMPTY_ARRAY
        }
    }

    private fun nestedStatementsToTreeChildren(elements: List<CssNestedStatement>): Array<TreeElement> {
        return elements
            .map { CssStructureViewElement(it as NavigatablePsiElement) }
            .toTypedArray()
    }
}