package ris58h.webcalm.html

import com.intellij.lang.injection.MultiHostInjector
import com.intellij.lang.injection.MultiHostRegistrar
import com.intellij.psi.PsiElement
import com.intellij.psi.PsiLanguageInjectionHost
import com.intellij.psi.xml.XmlAttribute
import com.intellij.psi.xml.XmlAttributeValue
import com.intellij.psi.xml.XmlTokenType
import ris58h.webcalm.javascript.JavaScriptLanguage

class HtmlAttributeJavaScriptInjector : MultiHostInjector {
    override fun getLanguagesToInject(registrar: MultiHostRegistrar, context: PsiElement) {
        if (context !is XmlAttributeValue || !isEventHandlerAttribute(context.parent as? XmlAttribute?)) {
            return
        }
        val attributeValueToken = context.node.findChildByType(XmlTokenType.XML_ATTRIBUTE_VALUE_TOKEN) ?: return
        if (attributeValueToken.text.isNotBlank()) {
            registrar.startInjecting(JavaScriptLanguage)
            registrar.addPlace("(function () {", ";})", context as PsiLanguageInjectionHost, attributeValueToken.psi.textRangeInParent)
            registrar.doneInjecting()
        }
    }

    override fun elementsToInjectIn() = listOf(XmlAttributeValue::class.java)

    private fun isEventHandlerAttribute(attribute: XmlAttribute?): Boolean {
        return attribute != null && EVENT_HANDLER_ATTRIBUTE_NAMES.contains(attribute.localName.toLowerCase())
    }

    companion object {
        private val EVENT_HANDLER_ATTRIBUTE_NAMES = setOf(
            "onAbort",
            "onAnimationCancel",
            "onAnimationEnd",
            "onAnimationIteration",
            "onAnimationStart",
            "onAuxClick",
            "onBeforeInput",
            "onBeforeMatch",
            "onBeforeToggle",
            "onCancel",
            "onCanPlay",
            "onCanPlayThrough",
            "onChange",
            "onClick",
            "onClose",
            "onContextLost",
            "onContextMenu",
            "onContextRestored",
            "onCopy",
            "onCueChange",
            "onCut",
            "onDblClick",
            "onDrag",
            "onDragEnd",
            "onDragEnter",
            "onDragLeave",
            "onDragOver",
            "onDragStart",
            "onDrop",
            "onDurationChange",
            "onEmptied",
            "onEnded",
            "onFormData",
            "onInput",
            "onInvalid",
            "onKeyDown",
            "onKeyPress",
            "onKeyUp",
            "onLoadedData",
            "onLoadedMetaData",
            "onLoadStart",
            "onMouseDown",
            "onMouseEnter",
            "onMouseLeave",
            "onMouseMove",
            "onMouseOut",
            "onMouseOver",
            "onMouseUp",
            "onPaste",
            "onPause",
            "onPlay",
            "onPlaying",
            "onProgress",
            "onRateChange",
            "onReset",
            "onSecurityPolicyViolation",
            "onSeekEnd",
            "onSeeking",
            "onSelect",
            "onSlotChange",
            "onStalled",
            "onSubmit",
            "onSuspend",
            "onTimeUpdate",
            "onToggle",
            "onTransitionCancel",
            "onTransitionEnd",
            "onTransitionRun",
            "onTransitionStart",
            "onVolumeChange",
            "onWaiting",
            "onWheel",
        ).map { it.toLowerCase() }
    }
}