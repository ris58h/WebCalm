package ris58h.webcalm.css

import css3Lexer
import org.antlr.intellij.adaptor.lexer.ANTLRLexerAdaptor
import org.antlr.intellij.adaptor.lexer.ANTLRLexerState

class CssLexer : ANTLRLexerAdaptor(CssLanguage, css3Lexer(null)) {
    /**
     * TODO: it's a hack to prevent the exception below for injected CSS code when startOffset != 0 :
     * java.lang.IndexOutOfBoundsException: Index 0 out of bounds for length 0
     * 	at java.base/jdk.internal.util.Preconditions.outOfBounds(Preconditions.java:64)
     * 	at java.base/jdk.internal.util.Preconditions.outOfBoundsCheckIndex(Preconditions.java:70)
     * 	at java.base/jdk.internal.util.Preconditions.checkIndex(Preconditions.java:248)
     * 	at java.base/java.util.Objects.checkIndex(Objects.java:372)
     * 	at java.base/java.util.ArrayList.get(ArrayList.java:458)
     * 	at org.antlr.intellij.adaptor.lexer.ANTLRLexerAdaptor.toLexerState(ANTLRLexerAdaptor.java:280)
     * 	at org.antlr.intellij.adaptor.lexer.ANTLRLexerAdaptor.start(ANTLRLexerAdaptor.java:168)
     * 	at com.intellij.lexer.HtmlHighlightingLexer$XmlEmbeddmentHandler.handleElement(HtmlHighlightingLexer.java:269)
     * 	at com.intellij.lexer.BaseHtmlLexer.advance(BaseHtmlLexer.java:415)
     * 	at com.intellij.lexer.HtmlHighlightingLexer.advance(HtmlHighlightingLexer.java:173)
     * 	at com.intellij.openapi.editor.ex.util.LexerEditorHighlighter.doSetText(LexerEditorHighlighter.java:459)
     * 	at com.intellij.openapi.editor.ex.util.LexerEditorHighlighter.setText(LexerEditorHighlighter.java:428)
     * 	at com.intellij.openapi.fileEditor.impl.text.TextEditorImpl.loadEditorInBackground(TextEditorImpl.java:71)
     * 	at com.intellij.openapi.fileEditor.impl.text.PsiAwareTextEditorImpl.loadEditorInBackground(PsiAwareTextEditorImpl.java:48)
     * 	at com.intellij.openapi.fileEditor.impl.text.AsyncEditorLoader.lambda$null$0(AsyncEditorLoader.java:93)
     * 	at com.intellij.openapi.progress.impl.CoreProgressManager.computePrioritized(CoreProgressManager.java:739)
     * 	at com.intellij.openapi.fileEditor.impl.text.AsyncEditorLoader.lambda$scheduleLoading$1(AsyncEditorLoader.java:91)
     * 	at com.intellij.openapi.application.impl.NonBlockingReadActionImpl$Submission.insideReadAction(NonBlockingReadActionImpl.java:485)
     * 	at com.intellij.openapi.application.impl.NonBlockingReadActionImpl$Submission.lambda$attemptComputation$2(NonBlockingReadActionImpl.java:442)
     * 	at com.intellij.openapi.application.impl.ApplicationImpl.tryRunReadAction(ApplicationImpl.java:1106)
     * 	at com.intellij.openapi.progress.util.ProgressIndicatorUtils.lambda$runInReadActionWithWriteActionPriority$0(ProgressIndicatorUtils.java:75)
     * 	at com.intellij.openapi.progress.util.ProgressIndicatorUtils.lambda$runWithWriteActionPriority$1(ProgressIndicatorUtils.java:129)
     * 	at com.intellij.openapi.progress.impl.CoreProgressManager.lambda$runProcess$2(CoreProgressManager.java:164)
     * 	at com.intellij.openapi.progress.impl.CoreProgressManager.registerIndicatorAndRun(CoreProgressManager.java:625)
     * 	at com.intellij.openapi.progress.impl.CoreProgressManager.executeProcessUnderProgress(CoreProgressManager.java:570)
     * 	at com.intellij.openapi.progress.impl.ProgressManagerImpl.executeProcessUnderProgress(ProgressManagerImpl.java:61)
     * 	at com.intellij.openapi.progress.impl.CoreProgressManager.runProcess(CoreProgressManager.java:151)
     * 	at com.intellij.openapi.progress.util.ProgressIndicatorUtils.runWithWriteActionPriority(ProgressIndicatorUtils.java:118)
     * 	at com.intellij.openapi.progress.util.ProgressIndicatorUtils.runInReadActionWithWriteActionPriority(ProgressIndicatorUtils.java:75)
     * 	at com.intellij.openapi.application.impl.NonBlockingReadActionImpl$Submission.attemptComputation(NonBlockingReadActionImpl.java:450)
     * 	at com.intellij.openapi.application.impl.NonBlockingReadActionImpl$Submission.lambda$transferToBgThread$0(NonBlockingReadActionImpl.java:383)
     * 	at com.intellij.util.concurrency.BoundedTaskExecutor.doRun(BoundedTaskExecutor.java:222)
     * 	at com.intellij.util.concurrency.BoundedTaskExecutor.access$200(BoundedTaskExecutor.java:29)
     * 	at com.intellij.util.concurrency.BoundedTaskExecutor$1.execute(BoundedTaskExecutor.java:201)
     * 	at com.intellij.util.ConcurrencyUtil.runUnderThreadName(ConcurrencyUtil.java:210)
     * 	at com.intellij.util.concurrency.BoundedTaskExecutor$1.run(BoundedTaskExecutor.java:190)
     * 	at java.base/java.util.concurrent.ThreadPoolExecutor.runWorker(ThreadPoolExecutor.java:1128)
     * 	at java.base/java.util.concurrent.ThreadPoolExecutor$Worker.run(ThreadPoolExecutor.java:628)
     * 	at java.base/java.lang.Thread.run(Thread.java:834)
     */
    override fun toLexerState(state: Int): ANTLRLexerState {
        if (state == 0) {
            return initialState
        }
        return super.toLexerState(state)
    }
}