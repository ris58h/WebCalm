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
import ris58h.webcalm.javascript.psi.JavaScriptClassDeclaration
import ris58h.webcalm.javascript.psi.JavaScriptFile
import ris58h.webcalm.javascript.psi.JavaScriptFunctionDeclaration
import ris58h.webcalm.javascript.psi.JavaScriptStatement

class JavaScriptStructureViewFactory : PsiStructureViewFactory {
    override fun getStructureViewBuilder(psiFile: PsiFile): StructureViewBuilder {
        return object : TreeBasedStructureViewBuilder() {
            override fun createStructureViewModel(editor: Editor?): StructureViewModel {
                return JavaScriptStructureViewModel(editor, psiFile)
            }
        }
    }
}

class JavaScriptStructureViewModel(editor: Editor?, psiFile: PsiFile) :
    StructureViewModelBase(psiFile, editor, JavaScriptStructureViewElement(psiFile)),
    StructureViewModel.ElementInfoProvider {

    override fun isAlwaysShowsPlus(element: StructureViewTreeElement?): Boolean {
        return false
    }

    override fun isAlwaysLeaf(element: StructureViewTreeElement): Boolean {
        return false
    }

    override fun getSuitableClasses(): Array<Class<out Any>> {
        return arrayOf(
            JavaScriptFunctionDeclaration::class.java,
            JavaScriptClassDeclaration::class.java,
        )
    }
}

class JavaScriptStructureViewElement(private val myElement: NavigatablePsiElement) : StructureViewTreeElement {
    override fun getValue(): Any {
        return myElement
    }

    override fun navigate(requestFocus: Boolean) {
        myElement.navigate(requestFocus)
    }

    override fun canNavigate(): Boolean {
        return myElement.canNavigate()
    }

    override fun canNavigateToSource(): Boolean {
        return myElement.canNavigateToSource()
    }

    override fun getPresentation(): ItemPresentation {
        return when (myElement) {
            is JavaScriptFunctionDeclaration -> PresentationData(myElement.name, null, AllIcons.Nodes.Function, null)
            is JavaScriptClassDeclaration -> PresentationData(myElement.name, null, AllIcons.Nodes.Class, null)
            else -> myElement.presentation ?: PresentationData()
        }
    }

    override fun getChildren(): Array<TreeElement> {
        return when (myElement) {
            is JavaScriptFile -> toTreeChildren(myElement.statements)
            is JavaScriptFunctionDeclaration -> toTreeChildren(myElement.body?.statements.orEmpty())
            else -> TreeElement.EMPTY_ARRAY
        }
    }

    private fun toTreeChildren(statements: Array<out JavaScriptStatement>): Array<TreeElement> {
        return statements
            .filter { it is JavaScriptFunctionDeclaration || it is JavaScriptClassDeclaration}
            .map { JavaScriptStructureViewElement(it as NavigatablePsiElement) }
            .toTypedArray()
    }
}
