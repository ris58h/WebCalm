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
    override fun getStructureViewBuilder(psiFile: PsiFile) =
        object : TreeBasedStructureViewBuilder() {
            override fun createStructureViewModel(editor: Editor?) = createModel(psiFile, editor)
        }
}

private fun createModel(psiFile: PsiFile, editor: Editor?): StructureViewModel {
    return object : StructureViewModelBase(psiFile, editor, JavaScriptStructureViewElement(psiFile)),
        StructureViewModel.ElementInfoProvider {
        override fun isAlwaysShowsPlus(element: StructureViewTreeElement?): Boolean = false
        override fun isAlwaysLeaf(element: StructureViewTreeElement): Boolean = false
    }.withSuitableClasses(
        JavaScriptFunctionDeclaration::class.java,
        JavaScriptClassDeclaration::class.java,
        JavaScriptClassElement::class.java,
    )
}

private class JavaScriptStructureViewElement(private val myElement: NavigatablePsiElement) : StructureViewTreeElement {
    override fun getValue() = myElement
    override fun navigate(requestFocus: Boolean) = myElement.navigate(requestFocus)
    override fun canNavigate() = myElement.canNavigate()
    override fun canNavigateToSource() = myElement.canNavigateToSource()

    override fun getPresentation(): ItemPresentation {
        return when (myElement) {
            is JavaScriptFunctionDeclaration -> PresentationData(myElement.name, null, AllIcons.Nodes.Function, null)
            is JavaScriptClassDeclaration -> PresentationData(myElement.name, null, AllIcons.Nodes.Class, null)
            is JavaScriptMethod -> {
                val text = when (myElement.type) {
                    JavaScriptMethod.Type.GETTER -> "get ${myElement.name}"
                    JavaScriptMethod.Type.SETTER -> "set ${myElement.name}"
                    JavaScriptMethod.Type.REGULAR -> myElement.name
                }
                PresentationData(text, null, AllIcons.Nodes.Method, null)
            }
            is JavaScriptField -> PresentationData(myElement.name, null, AllIcons.Nodes.Field, null)
            is JavaScriptClassStaticBlock -> PresentationData("static class initializer", null, AllIcons.Nodes.ClassInitializer, null)
            else -> myElement.presentation ?: PresentationData()
        }
    }

    override fun getChildren(): Array<TreeElement> {
        return when (myElement) {
            is JavaScriptFile -> statementsToTreeChildren(myElement.statements)
            is JavaScriptFunctionDeclaration -> statementsToTreeChildren(myElement.body?.statements.orEmpty())
            is JavaScriptClassDeclaration -> classElementsToTreeChildren(myElement.classElements)
            is JavaScriptMethod -> statementsToTreeChildren(myElement.body?.statements.orEmpty())
            is JavaScriptClassStaticBlock -> statementsToTreeChildren(myElement.block?.statements.orEmpty())
            else -> TreeElement.EMPTY_ARRAY
        }
    }

    private fun statementsToTreeChildren(statements: List<JavaScriptStatement>): Array<TreeElement> {
        return statements
            .map { if (it is JavaScriptExportStatement) it.declaration else it }
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
