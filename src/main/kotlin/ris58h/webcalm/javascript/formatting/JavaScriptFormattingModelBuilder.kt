package ris58h.webcalm.javascript.formatting

import com.intellij.formatting.*

class JavaScriptFormattingModelBuilder : FormattingModelBuilder {
    override fun createModel(formattingContext: FormattingContext): FormattingModel {
        return FormattingModelProvider.createFormattingModelForPsiFile(
            formattingContext.containingFile,
            JavaScriptBlock(formattingContext.node, null, null),
            formattingContext.codeStyleSettings
        )
    }
}