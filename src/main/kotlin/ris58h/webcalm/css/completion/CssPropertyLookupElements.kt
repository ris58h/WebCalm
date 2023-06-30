package ris58h.webcalm.css.completion

import com.intellij.codeInsight.lookup.LookupElementBuilder

object CssPropertyLookupElements {
    private val COLORS = listOf(
        "black",
        "silver",
        "gray",
        "white",
        "maroon",
        "red",
        "purple",
        "fuchsia",
        "green",
        "lime",
        "olive",
        "yellow",
        "navy",
        "blue",
        "teal",
        "aqua",
        // TODO: add other named colors. See https://developer.mozilla.org/en-US/docs/Web/CSS/named-color
    )
    private val TRANSPARENT_COLORS = COLORS + "transparent"
    private val AUTO_COLORS = COLORS + "auto"

    private val BORDER_COLOR_VALUES = TRANSPARENT_COLORS
    private val BORDER_STYLE_VALUES = listOf("none", "hidden", "dotted", "dashed", "solid", "double", "groove", "ridge", "inset", "outset")
    private val BORDER_WIDTH_VALUES = listOf("medium", "thin", "thick")

    private val BREAK_VALUES = listOf("auto", "all", "always", "avoid", "avoid-column", "avoid-page", "avoid-region", "column", "left", "page", "recto", "region", "right", "verso")

    val PROPERTY_VALUES_LOOKUP_ELEMENTS = mapOf(
        "accent-color" to AUTO_COLORS,
        "align-content" to listOf("stretch", "center", "flex-start", "flex-end", "space-between", "space-around", "space-evenly"),
        "align-items" to listOf("normal", "stretch", "center", "flex-start", "flex-end", "baseline"),
        "align-self" to listOf("auto", "stretch", "center", "flex-start", "flex-end", "baseline"),
        "all" to listOf("unset"),
        "animation" to listOf(),
        "animation-delay" to listOf(),
        "animation-direction" to listOf(),
        "animation-duration" to listOf(),
        "animation-fill-mode" to listOf("none", "forwards", "backwards", "both"),
        "animation-iteration-count" to listOf("infinite"),
        "animation-name" to listOf("none"),
        "animation-play-state" to listOf("paused", "running"),
        "animation-timing-function" to listOf("linear", "ease", "ease-in", "ease-out", "ease-in-out", "step-start", "step-end"),
        "aspect-ratio" to listOf(),
        "backdrop-filter" to listOf("none"),
        "backface-visibility" to listOf("visible", "hidden"),
        "background" to listOf(),
        "background-attachment" to listOf("scroll", "fixed", "local"),
        "background-blend-mode" to listOf("normal", "multiply", "screen", "overlay", "darken", "lighten", "color-dodge", "saturation", "color", "luminosity"),
        "background-clip" to listOf("border-box", "padding-box", "content-box"),
        "background-color" to TRANSPARENT_COLORS,
        "background-image" to listOf("none"),
        "background-origin" to listOf("padding-box", "border-box", "content-box"),
        "background-position" to listOf("left", "right", "center"),
        "background-position-x" to listOf("left", "right", "center"),
        "background-position-y" to listOf("left", "right", "center"),
        "background-repeat" to listOf("repeat", "repeat-x", "repeat-y", "no-repeat"),
        "background-size" to listOf("auto", "cover", "contain"),
        "block-size" to listOf("auto"),
        "border" to listOf(),
        "border-block" to listOf(),
        "border-block-color" to BORDER_COLOR_VALUES,
        "border-block-end-color" to BORDER_COLOR_VALUES,
        "border-block-end-style" to BORDER_STYLE_VALUES,
        "border-block-end-width" to BORDER_WIDTH_VALUES,
        "border-block-start-color" to BORDER_COLOR_VALUES,
        "border-block-start-style" to BORDER_STYLE_VALUES,
        "border-block-start-width" to BORDER_WIDTH_VALUES,
        "border-block-style" to BORDER_STYLE_VALUES,
        "border-block-width" to BORDER_WIDTH_VALUES,
        "border-bottom" to listOf(),
        "border-bottom-color" to BORDER_COLOR_VALUES,
        "border-bottom-left-radius" to listOf(),
        "border-bottom-right-radius" to listOf(),
        "border-bottom-style" to BORDER_STYLE_VALUES,
        "border-bottom-width" to BORDER_WIDTH_VALUES,
        "border-collapse" to listOf("separate", "collapse"),
        "border-color" to BORDER_COLOR_VALUES,
        "border-end-end-radius" to listOf(),
        "border-end-start-radius" to listOf(),
        "border-image" to listOf(),
        "border-image-outset" to listOf(),
        "border-image-repeat" to listOf(),
        "border-image-slice" to listOf(),
        "border-image-source" to listOf(),
        "border-image-width" to listOf("auto"),
        "border-inline" to listOf(),
        "border-inline-color" to BORDER_COLOR_VALUES,
        "border-inline-end-color" to BORDER_COLOR_VALUES,
        "border-inline-end-style" to BORDER_STYLE_VALUES,
        "border-inline-end-width" to BORDER_WIDTH_VALUES,
        "border-inline-start-color" to BORDER_COLOR_VALUES,
        "border-inline-start-style" to BORDER_STYLE_VALUES,
        "border-inline-start-width" to BORDER_WIDTH_VALUES,
        "border-inline-style" to BORDER_STYLE_VALUES,
        "border-inline-width" to BORDER_WIDTH_VALUES,
        "border-left" to listOf(),
        "border-left-color" to BORDER_COLOR_VALUES,
        "border-left-style" to BORDER_STYLE_VALUES,
        "border-left-width" to BORDER_WIDTH_VALUES,
        "border-radius" to listOf(),
        "border-right" to listOf(),
        "border-right-color" to BORDER_COLOR_VALUES,
        "border-right-style" to BORDER_STYLE_VALUES,
        "border-right-width" to BORDER_WIDTH_VALUES,
        "border-spacing" to listOf(),
        "border-start-end-radius" to listOf(),
        "border-start-start-radius" to listOf(),
        "border-style" to BORDER_STYLE_VALUES,
        "border-top" to listOf(),
        "border-top-color" to BORDER_COLOR_VALUES,
        "border-top-left-radius" to listOf(),
        "border-top-right-radius" to listOf(),
        "border-top-style" to BORDER_STYLE_VALUES,
        "border-top-width" to BORDER_WIDTH_VALUES,
        "border-width" to BORDER_WIDTH_VALUES,
        "bottom" to listOf("auto"),
        "box-decoration-break" to listOf("slice", "clone", "unset"),
        "box-reflect" to listOf("none", "below", "above", "left", "right"),
        "box-shadow" to listOf("none", "inset"),
        "box-sizing" to listOf("content-box", "border-box"),
        "break-after" to BREAK_VALUES,
        "break-before" to BREAK_VALUES,
        "break-inside" to BREAK_VALUES,
        "caption-side" to listOf("top", "bottom"),
        "caret-color" to AUTO_COLORS,
        "clear" to listOf("none", "left", "right", "both"),
        "clip" to listOf("auto"),
        "clip-path" to listOf("margin-box", "border-box", "padding-box", "content-box", "fill-box", "stroke-box", "view-box", "none"),
        "color" to COLORS,
        "column-count" to listOf("auto"),
        "column-fill" to listOf("balance", "auto"),
        "column-gap" to listOf("normal"),
        "column-rule" to listOf(),
        "column-rule-color" to COLORS,
        "column-rule-style" to BORDER_STYLE_VALUES,
        "column-rule-width" to BORDER_WIDTH_VALUES,
        "column-span" to listOf("none", "all"),
        "column-width" to listOf("auto"),
        "columns" to listOf(),
        "content" to listOf("normal", "none", "counter", "attr", "open-quote", "close-quote", "no-open-quote", "no-close-quote"),
        "counter-increment" to listOf("none"),
        "counter-reset" to listOf("none"),
        "cursor" to listOf(
            "alias",
            "all-scroll",
            "auto",
            "cell",
            "context-menu",
            "col-resize",
            "copy",
            "crosshair",
            "default",
            "e-resize",
            "ew-resize",
            "help",
            "move",
            "n-resize",
            "ne-resize",
            "nesw-resize",
            "ns-resize",
            "nw-resize",
            "nwse-resize",
            "no-drop",
            "none",
            "not-allowed",
            "pointer",
            "progress",
            "row-resize",
            "s-resize",
            "se-resize",
            "sw-resize",
            "text",
            "vertical-text",
            "w-resize",
            "wait",
            "zoom-in",
            "zoom-out",
        ),
        "direction" to listOf(),
        "display" to listOf(
            "inline",
            "block",
            "contents",
            "flex",
            "grid",
            "inline-block",
            "inline-flex",
            "inline-grid",
            "inline-table",
            "list-item",
            "run-in",
            "table",
            "table-caption",
            "table-column-group",
            "table-header-group",
            "table-footer-group",
            "table-row-group",
            "table-cell",
            "table-column",
            "table-row",
            "none",
        ),
        "empty-cells" to listOf(),
        "filter" to listOf(),
        "flex" to listOf(),
        "flex-basis" to listOf(),
        "flex-direction" to listOf(),
        "flex-flow" to listOf(),
        "flex-grow" to listOf(),
        "flex-shrink" to listOf(),
        "flex-wrap" to listOf(),
        "float" to listOf(),
        "font" to listOf(),
        "font-family" to listOf(),
        "font-feature-settings" to listOf(),
        "font-kerning" to listOf(),
        "font-size" to listOf(),
        "font-size-adjust" to listOf(),
        "font-stretch" to listOf(),
        "font-style" to listOf(),
        "font-variant" to listOf(),
        "font-variant-caps" to listOf(),
        "font-weight" to listOf(),
        "gap" to listOf(),
        "grid" to listOf(),
        "grid-area" to listOf(),
        "grid-auto-columns" to listOf(),
        "grid-auto-flow" to listOf(),
        "grid-auto-rows" to listOf(),
        "grid-column" to listOf(),
        "grid-column-end" to listOf(),
        "grid-column-gap" to listOf(),
        "grid-column-start" to listOf(),
        "grid-gap" to listOf(),
        "grid-row" to listOf(),
        "grid-row-end" to listOf(),
        "grid-row-gap" to listOf(),
        "grid-row-start" to listOf(),
        "grid-template" to listOf(),
        "grid-template-areas" to listOf(),
        "grid-template-columns" to listOf(),
        "grid-template-rows" to listOf(),
        "hanging-punctuation" to listOf(),
        "height" to listOf(),
        "hyphens" to listOf(),
        "image-rendering" to listOf(),
        "inline-size" to listOf(),
        "inset" to listOf(),
        "inset-block" to listOf(),
        "inset-block-end" to listOf(),
        "inset-block-start" to listOf(),
        "inset-inline" to listOf(),
        "inset-inline-end" to listOf(),
        "inset-inline-start" to listOf(),
        "isolation" to listOf(),
        "justify-content" to listOf(),
        "justify-items" to listOf(),
        "justify-self" to listOf(),
        "left" to listOf(),
        "letter-spacing" to listOf(),
        "line-height" to listOf(),
        "list-style" to listOf(),
        "list-style-image" to listOf(),
        "list-style-position" to listOf(),
        "list-style-type" to listOf(),
        "margin" to listOf(),
        "margin-block" to listOf(),
        "margin-block-end" to listOf(),
        "margin-block-start" to listOf(),
        "margin-bottom" to listOf(),
        "margin-inline" to listOf(),
        "margin-inline-end" to listOf(),
        "margin-inline-start" to listOf(),
        "margin-left" to listOf(),
        "margin-right" to listOf(),
        "margin-top" to listOf(),
        "mask-image" to listOf(),
        "mask-mode" to listOf(),
        "mask-origin" to listOf(),
        "mask-position" to listOf(),
        "mask-repeat" to listOf(),
        "mask-size" to listOf(),
        "max-block-size" to listOf(),
        "max-height" to listOf(),
        "max-inline-size" to listOf(),
        "max-width" to listOf(),
        "min-block-size" to listOf(),
        "min-inline-size" to listOf(),
        "min-height" to listOf(),
        "min-width" to listOf(),
        "mix-blend-mode" to listOf(),
        "object-fit" to listOf(),
        "object-position" to listOf(),
        "offset" to listOf(),
        "offset-anchor" to listOf(),
        "offset-distance" to listOf(),
        "offset-path" to listOf(),
        "offset-rotate" to listOf(),
        "opacity" to listOf(),
        "order" to listOf(),
        "orphans" to listOf(),
        "outline" to listOf(),
        "outline-color" to listOf(),
        "outline-offset" to listOf(),
        "outline-style" to listOf(),
        "outline-width" to listOf(),
        "overflow" to listOf(),
        "overflow-anchor" to listOf(),
        "overflow-wrap" to listOf(),
        "overflow-x" to listOf(),
        "overflow-y" to listOf(),
        "overscroll-behavior" to listOf(),
        "overscroll-behavior-block" to listOf(),
        "overscroll-behavior-inline" to listOf(),
        "overscroll-behavior-x" to listOf(),
        "overscroll-behavior-y" to listOf(),
        "padding" to listOf(),
        "padding-block" to listOf(),
        "padding-block-end" to listOf(),
        "padding-block-start" to listOf(),
        "padding-bottom" to listOf(),
        "padding-inline" to listOf(),
        "padding-inline-end" to listOf(),
        "padding-inline-start" to listOf(),
        "padding-left" to listOf(),
        "padding-right" to listOf(),
        "padding-top" to listOf(),
        "page-break-after" to listOf(),
        "page-break-before" to listOf(),
        "page-break-inside" to listOf(),
        "paint-order" to listOf(),
        "perspective" to listOf(),
        "perspective-origin" to listOf(),
        "place-content" to listOf(),
        "place-items" to listOf(),
        "place-self" to listOf(),
        "pointer-events" to listOf(),
        "position" to listOf("static", "absolute", "fixed", "relative", "sticky"),
        "quotes" to listOf(),
        "resize" to listOf(),
        "right" to listOf(),
        "rotate" to listOf(),
        "row-gap" to listOf(),
        "scale" to listOf(),
        "scroll-behavior" to listOf(),
        "scroll-margin" to listOf(),
        "scroll-margin-block" to listOf(),
        "scroll-margin-block-end" to listOf(),
        "scroll-margin-block-start" to listOf(),
        "scroll-margin-bottom" to listOf(),
        "scroll-margin-inline" to listOf(),
        "scroll-margin-inline-end" to listOf(),
        "scroll-margin-inline-start" to listOf(),
        "scroll-margin-left" to listOf(),
        "scroll-margin-right" to listOf(),
        "scroll-margin-top" to listOf(),
        "scroll-padding" to listOf(),
        "scroll-padding-block" to listOf(),
        "scroll-padding-block-end" to listOf(),
        "scroll-padding-block-start" to listOf(),
        "scroll-padding-bottom" to listOf(),
        "scroll-padding-inline" to listOf(),
        "scroll-padding-inline-end" to listOf(),
        "scroll-padding-inline-start" to listOf(),
        "scroll-padding-left" to listOf(),
        "scroll-padding-right" to listOf(),
        "scroll-padding-top" to listOf(),
        "scroll-snap-align" to listOf(),
        "scroll-snap-stop" to listOf(),
        "scroll-snap-type" to listOf(),
        "scrollbar-color" to listOf(),
        "tab-size" to listOf(),
        "table-layout" to listOf(),
        "text-align" to listOf(),
        "text-align-last" to listOf(),
        "text-decoration" to listOf(),
        "text-decoration-color" to listOf(),
        "text-decoration-line" to listOf(),
        "text-decoration-style" to listOf(),
        "text-decoration-thickness" to listOf(),
        "text-indent" to listOf(),
        "text-justify" to listOf(),
        "text-orientation" to listOf(),
        "text-overflow" to listOf(),
        "text-shadow" to listOf(),
        "text-transform" to listOf(),
        "top" to listOf(),
        "transform" to listOf(),
        "transform-origin" to listOf(),
        "transform-style" to listOf(),
        "transition" to listOf(),
        "transition-delay" to listOf(),
        "transition-duration" to listOf(),
        "transition-property" to listOf(),
        "transition-timing-function" to listOf(),
        "translate" to listOf(),
        "unicode-bidi" to listOf(),
        "user-select" to listOf(),
        "vertical-align" to listOf(),
        "visibility" to listOf(),
        "white-space" to listOf(),
        "widows" to listOf(),
        "width" to listOf(),
        "word-break" to listOf(),
        "word-spacing" to listOf(),
        "word-wrap" to listOf(),
        "writing-mode" to listOf(),
        "z-index" to listOf(),
    ).mapValues { it.value.map { LookupElementBuilder.create(it) } }

    val PROPERTY_NAME_LOOKUP_ELEMENTS = PROPERTY_VALUES_LOOKUP_ELEMENTS.keys.map { LookupElementBuilder.create(it) }
}