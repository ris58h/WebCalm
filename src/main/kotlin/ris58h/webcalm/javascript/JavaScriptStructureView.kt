package ris58h.webcalm.javascript

import com.intellij.icons.AllIcons
import com.intellij.ide.projectView.PresentationData
import com.intellij.ide.structureView.*
import com.intellij.ide.util.treeView.smartTree.TreeElement
import com.intellij.lang.PsiStructureViewFactory
import com.intellij.navigation.ItemPresentation
import com.intellij.openapi.editor.Editor
import com.intellij.psi.NavigatablePsiElement
import com.intellij.psi.PsiFile
import ris58h.webcalm.javascript.psi.*

class JavaScriptStructureViewFactory : PsiStructureViewFactory {
    override fun getStructureViewBuilder(psiFile: PsiFile): StructureViewBuilder {
        return object : TreeBasedStructureViewBuilder() {
            override fun createStructureViewModel(editor: Editor?): StructureViewModel {
                return JavaScriptStructureViewModel(editor, psiFile)
            }
        }
    }
}

private val SUITABLE_CLASSES: Array<Class<out Any>> = arrayOf(
    JavaScriptFunctionDeclaration::class.java,
    JavaScriptClassDeclaration::class.java,
    JavaScriptMethod::class.java,
)

class JavaScriptStructureViewModel(editor: Editor?, psiFile: PsiFile) :
    StructureViewModelBase(psiFile, editor, JavaScriptStructureViewElement(psiFile)),
    StructureViewModel.ElementInfoProvider {
    override fun isAlwaysShowsPlus(element: StructureViewTreeElement?): Boolean = false
    override fun isAlwaysLeaf(element: StructureViewTreeElement): Boolean = false
    override fun getSuitableClasses(): Array<Class<out Any>> = SUITABLE_CLASSES
}

class JavaScriptStructureViewElement(private val myElement: NavigatablePsiElement) : StructureViewTreeElement {
    override fun getValue(): Any = myElement
    override fun navigate(requestFocus: Boolean) = myElement.navigate(requestFocus)
    override fun canNavigate(): Boolean = myElement.canNavigate()
    override fun canNavigateToSource(): Boolean = myElement.canNavigateToSource()

    override fun getPresentation(): ItemPresentation {
        return when (myElement) {
            is JavaScriptFunctionDeclaration -> PresentationData(myElement.name, null, AllIcons.Nodes.Function, null)
            is JavaScriptClassDeclaration -> PresentationData(myElement.name, null, AllIcons.Nodes.Class, null)
            is JavaScriptMethod -> PresentationData(myElement.name, null, AllIcons.Nodes.Method, null)
            else -> myElement.presentation ?: PresentationData()
        }
    }

    override fun getChildren(): Array<TreeElement> {
        return when (myElement) {
            is JavaScriptFile -> statementsToTreeChildren(myElement.statements)
            is JavaScriptFunctionDeclaration -> statementsToTreeChildren(myElement.body?.statements.orEmpty())
            is JavaScriptClassDeclaration -> classElementsToTreeChildren(myElement.classElements)
            is JavaScriptMethod -> statementsToTreeChildren(myElement.body?.statements.orEmpty())
            else -> TreeElement.EMPTY_ARRAY
        }
    }

    private fun statementsToTreeChildren(statements: List<JavaScriptStatement>): Array<TreeElement> {
        return statements
            .filter { it is JavaScriptFunctionDeclaration || it is JavaScriptClassDeclaration }
            .map { JavaScriptStructureViewElement(it as NavigatablePsiElement) }
            .toTypedArray()
    }

    private fun classElementsToTreeChildren(elements: List<JavaScriptClassElement>): Array<TreeElement> {
        return elements
            .map { JavaScriptStructureViewElement(it as NavigatablePsiElement) }
            .toTypedArray()
    }
}
