package ris58h.webcalm.css.completion

import com.intellij.codeInsight.completion.InsertHandler
import com.intellij.codeInsight.lookup.LookupElement
import com.intellij.codeInsight.lookup.LookupElementBuilder
import com.intellij.util.ui.ColorIcon
import java.awt.Color

object CssPropertyLookupElements {
    private val COLORS_BY_NAME = mapOf(
        "aliceblue" to "#f0f8ff",
        "antiquewhite" to "#faebd7",
        "aqua" to "#00ffff",
        "aquamarine" to "#7fffd4",
        "azure" to "#f0ffff",
        "beige" to "#f5f5dc",
        "bisque" to "#ffe4c4",
        "black" to "#000000",
        "blanchedalmond" to "#ffebcd",
        "blue" to "#0000ff",
        "blueviolet" to "#8a2be2",
        "brown" to "#a52a2a",
        "burlywood" to "#deb887",
        "cadetblue" to "#5f9ea0",
        "chartreuse" to "#7fff00",
        "chocolate" to "#d2691e",
        "coral" to "#ff7f50",
        "cornflowerblue" to "#6495ed",
        "cornsilk" to "#fff8dc",
        "crimson" to "#dc143c",
        "cyan" to "#00ffff",
        "darkblue" to "#00008b",
        "darkcyan" to "#008b8b",
        "darkgoldenrod" to "#b8860b",
        "darkgray" to "#a9a9a9",
        "darkgreen" to "#006400",
        "darkgrey" to "#a9a9a9",
        "darkkhaki" to "#bdb76b",
        "darkmagenta" to "#8b008b",
        "darkolivegreen" to "#556b2f",
        "darkorange" to "#ff8c00",
        "darkorchid" to "#9932cc",
        "darkred" to "#8b0000",
        "darksalmon" to "#e9967a",
        "darkseagreen" to "#8fbc8f",
        "darkslateblue" to "#483d8b",
        "darkslategray" to "#2f4f4f",
        "darkslategrey" to "#2f4f4f",
        "darkturquoise" to "#00ced1",
        "darkviolet" to "#9400d3",
        "deeppink" to "#ff1493",
        "deepskyblue" to "#00bfff",
        "dimgray" to "#696969",
        "dimgrey" to "#696969",
        "dodgerblue" to "#1e90ff",
        "firebrick" to "#b22222",
        "floralwhite" to "#fffaf0",
        "forestgreen" to "#228b22",
        "fuchsia" to "#ff00ff",
        "gainsboro" to "#dcdcdc",
        "ghostwhite" to "#f8f8ff",
        "gold" to "#ffd700",
        "goldenrod" to "#daa520",
        "gray" to "#808080",
        "green" to "#008000",
        "greenyellow" to "#adff2f",
        "grey" to "#808080",
        "honeydew" to "#f0fff0",
        "hotpink" to "#ff69b4",
        "indianred" to "#cd5c5c",
        "indigo" to "#4b0082",
        "ivory" to "#fffff0",
        "khaki" to "#f0e68c",
        "lavender" to "#e6e6fa",
        "lavenderblush" to "#fff0f5",
        "lawngreen" to "#7cfc00",
        "lemonchiffon" to "#fffacd",
        "lightblue" to "#add8e6",
        "lightcoral" to "#f08080",
        "lightcyan" to "#e0ffff",
        "lightgoldenrodyellow" to "#fafad2",
        "lightgray" to "#d3d3d3",
        "lightgreen" to "#90ee90",
        "lightgrey" to "#d3d3d3",
        "lightpink" to "#ffb6c1",
        "lightsalmon" to "#ffa07a",
        "lightseagreen" to "#20b2aa",
        "lightskyblue" to "#87cefa",
        "lightslategray" to "#778899",
        "lightslategrey" to "#778899",
        "lightsteelblue" to "#b0c4de",
        "lightyellow" to "#ffffe0",
        "lime" to "#00ff00",
        "limegreen" to "#32cd32",
        "linen" to "#faf0e6",
        "magenta" to "#ff00ff",
        "maroon" to "#800000",
        "mediumaquamarine" to "#66cdaa",
        "mediumblue" to "#0000cd",
        "mediumorchid" to "#ba55d3",
        "mediumpurple" to "#9370db",
        "mediumseagreen" to "#3cb371",
        "mediumslateblue" to "#7b68ee",
        "mediumspringgreen" to "#00fa9a",
        "mediumturquoise" to "#48d1cc",
        "mediumvioletred" to "#c71585",
        "midnightblue" to "#191970",
        "mintcream" to "#f5fffa",
        "mistyrose" to "#ffe4e1",
        "moccasin" to "#ffe4b5",
        "navajowhite" to "#ffdead",
        "navy" to "#000080",
        "oldlace" to "#fdf5e6",
        "olive" to "#808000",
        "olivedrab" to "#6b8e23",
        "orange" to "#ffa500",
        "orangered" to "#ff4500",
        "orchid" to "#da70d6",
        "palegoldenrod" to "#eee8aa",
        "palegreen" to "#98fb98",
        "paleturquoise" to "#afeeee",
        "palevioletred" to "#db7093",
        "papayawhip" to "#ffefd5",
        "peachpuff" to "#ffdab9",
        "peru" to "#cd853f",
        "pink" to "#ffc0cb",
        "plum" to "#dda0dd",
        "powderblue" to "#b0e0e6",
        "purple" to "#800080",
        "rebeccapurple" to "#663399",
        "red" to "#ff0000",
        "rosybrown" to "#bc8f8f",
        "royalblue" to "#4169e1",
        "saddlebrown" to "#8b4513",
        "salmon" to "#fa8072",
        "sandybrown" to "#f4a460",
        "seagreen" to "#2e8b57",
        "seashell" to "#fff5ee",
        "sienna" to "#a0522d",
        "silver" to "#c0c0c0",
        "skyblue" to "#87ceeb",
        "slateblue" to "#6a5acd",
        "slategray" to "#708090",
        "slategrey" to "#708090",
        "snow" to "#fffafa",
        "springgreen" to "#00ff7f",
        "steelblue" to "#4682b4",
        "tan" to "#d2b48c",
        "teal" to "#008080",
        "thistle" to "#d8bfd8",
        "tomato" to "#ff6347",
        "turquoise" to "#40e0d0",
        "violet" to "#ee82ee",
        "wheat" to "#f5deb3",
        "white" to "#ffffff",
        "whitesmoke" to "#f5f5f5",
        "yellow" to "#ffff00",
        "yellowgreen" to "#9acd32"
    )
    private val SYSTEM_COLOR_VALUES = values(
        "AccentColor",
        "AccentColorText",
        "ActiveText",
        "ButtonBorder",
        "ButtonFace",
        "ButtonText",
        "Canvas",
        "CanvasText",
        "Field",
        "FieldText",
        "GrayText",
        "Highlight",
        "HighlightText",
        "LinkText",
        "Mark",
        "MarkText",
        "VisitedText",
    )
    private val NAMED_COLOR_VALUES = COLORS_BY_NAME.entries.map { e -> color(e.key, e.value) }
    private val COLOR_BASE_VALUES = NAMED_COLOR_VALUES + values("transparent")
    private val COLOR_VALUES = COLOR_BASE_VALUES + values("currentcolor") + SYSTEM_COLOR_VALUES

    private val EMPTY_VALUES = values()
    private val AUTO_VALUE = values("auto")
    private val NONE_VALUE = values("none")
    private val NORMAL_VALUE = values("normal")

    private val STYLE_VALUES = values("none", "hidden", "dotted", "dashed", "solid", "double", "groove", "ridge", "inset", "outset")
    private val WIDTH_VALUES = values("medium", "thin", "thick")

    private val ANIMATION_DELAY_VALUES = EMPTY_VALUES
    private val ANIMATION_DIRECTION_VALUES = EMPTY_VALUES
    private val ANIMATION_DURATION_VALUES = EMPTY_VALUES
    private val ANIMATION_FILL_MODE_VALUES = values("none", "forwards", "backwards", "both")
    private val ANIMATION_ITERATION_COUNT_VALUES = values("infinite")
    private val ANIMATION_NAME_VALUES = NONE_VALUE
    private val ANIMATION_PLAY_STATE_VALUES = values("paused", "running")
    private val ANIMATION_TIMING_FUNCTION_VALUES = values("linear", "ease", "ease-in", "ease-out", "ease-in-out", "step-start", "step-end")

    private val BACKGROUND_ATTACHMENT_VALUES = values("scroll", "fixed", "local")
    private val BACKGROUND_CLIP_VALUES = values("border-box", "padding-box", "content-box")
    private val BACKGROUND_COLOR_VALUES = COLOR_VALUES
    private val BACKGROUND_IMAGE_VALUES = NONE_VALUE
    private val BACKGROUND_ORIGIN_VALUES = values("padding-box", "border-box", "content-box")
    private val BACKGROUND_POSITION_VALUES = values("left", "right", "center")
    private val BACKGROUND_REPEAT_VALUES = values("repeat", "repeat-x", "repeat-y", "no-repeat")
    private val BACKGROUND_SIZE_VALUES = values("auto", "cover", "contain")

    private val BORDER_COLOR_VALUES = WIDTH_VALUES
    private val BORDER_STYLE_VALUES = STYLE_VALUES
    private val BORDER_WIDTH_VALUES = COLOR_VALUES

    private val BREAK_VALUES = values("auto", "all", "always", "avoid", "avoid-column", "avoid-page", "avoid-region", "column", "left", "page", "recto", "region", "right", "verso")

    private val COLUMN_GAP_VALUES = NORMAL_VALUE

    private val FLEX_BASIS_VALUES = AUTO_VALUE
    private val FLEX_GROW_VALUES = EMPTY_VALUES
    private val FLEX_SHRINK_VALUES = EMPTY_VALUES

    private val FONT_FAMILY_VALUES = EMPTY_VALUES
    private val FONT_SIZE_VALUES = values("medium", "xx-small", "x-small", "small", "large", "x-large", "xx-large", "smaller", "larger")
    private val FONT_STYLE_VALUES = values("normal", "italic", "oblique")
    private val FONT_VARIANT_VALUES = values("normal", "small-caps")
    private val FONT_WEIGHT_VALUES = values("normal", "bold", "bolder", "lighter")

    private val LINE_HEIGHT_VALUES = NORMAL_VALUE

    private val LIST_STYLE_IMAGE_VALUES = NONE_VALUE
    private val LIST_STYLE_POSITION_VALUES = values("inside", "outside")
    private val LIST_STYLE_TYPE_VALUES = values("disc", "armenian", "circle", "cjk-ideographic", "decimal", "decimal-leading-zero", "georgian", "hebrew", "hiragana", "hiragana-iroha", "katakana", "katakana-iroha", "lower-alpha", "lower-greek", "lower-latin", "lower-roman", "none", "square", "upper-alpha", "upper-greek", "upper-latin", "upper-roman")

    private val OVERFLOW_VALUES = values("visible", "hidden", "scroll", "auto")
    private val OVERSCROLL_BEHAVIOR_VALUES = values("auto", "contain", "none")

    private val ROW_GAP_VALUES = NORMAL_VALUE

    private val TIMING_FUNCTION_VALUES = values("linear", "ease", "ease-in", "ease-out", "ease-in-out", "step-start", "step-end") + function("steps") + function("cubic-bezier")

    private val TRANSFORM_FUNCTION_NAMES = listOf(
        "matrix",
        "matrix3d",
        "translate",
        "translate3d",
        "translateX",
        "translateY",
        "translateZ",
        "scale",
        "scale3d",
        "scaleX",
        "scaleY",
        "scaleZ",
        "rotate",
        "rotate3d",
        "rotateX",
        "rotateY",
        "rotateZ",
        "skew",
        "skewX",
        "skewY",
        "perspective",
    )
    private val TRANSFORM_VALUES = NONE_VALUE + TRANSFORM_FUNCTION_NAMES.map { function(it) }

    val PROPERTY_VALUES_LOOKUP_ELEMENTS = mapOf(
        "accent-color" to COLOR_VALUES + AUTO_VALUE,
        "align-content" to values("stretch", "center", "flex-start", "flex-end", "space-between", "space-around", "space-evenly"),
        "align-items" to values("normal", "stretch", "center", "flex-start", "flex-end", "baseline"),
        "align-self" to values("auto", "stretch", "center", "flex-start", "flex-end", "baseline"),
        "all" to values("unset"),
        "animation" to ANIMATION_DELAY_VALUES +
                ANIMATION_DIRECTION_VALUES +
                ANIMATION_DURATION_VALUES +
                ANIMATION_FILL_MODE_VALUES +
                ANIMATION_ITERATION_COUNT_VALUES +
                ANIMATION_NAME_VALUES +
                ANIMATION_PLAY_STATE_VALUES +
                ANIMATION_TIMING_FUNCTION_VALUES,
        "animation-delay" to ANIMATION_DELAY_VALUES,
        "animation-direction" to ANIMATION_DIRECTION_VALUES,
        "animation-duration" to ANIMATION_DURATION_VALUES,
        "animation-fill-mode" to ANIMATION_FILL_MODE_VALUES,
        "animation-iteration-count" to ANIMATION_ITERATION_COUNT_VALUES,
        "animation-name" to ANIMATION_NAME_VALUES,
        "animation-play-state" to ANIMATION_PLAY_STATE_VALUES,
        "animation-timing-function" to TIMING_FUNCTION_VALUES,
        "aspect-ratio" to EMPTY_VALUES,
        "backdrop-filter" to NONE_VALUE,
        "backface-visibility" to values("visible", "hidden"),
        "background" to BACKGROUND_IMAGE_VALUES +
                BACKGROUND_POSITION_VALUES +
                BACKGROUND_SIZE_VALUES +
                BACKGROUND_REPEAT_VALUES +
                BACKGROUND_ORIGIN_VALUES +
                BACKGROUND_CLIP_VALUES +
                BACKGROUND_ATTACHMENT_VALUES +
                BACKGROUND_COLOR_VALUES,
        "background-attachment" to BACKGROUND_ATTACHMENT_VALUES,
        "background-blend-mode" to values("normal", "multiply", "screen", "overlay", "darken", "lighten", "color-dodge", "saturation", "color", "luminosity"),
        "background-clip" to BACKGROUND_CLIP_VALUES,
        "background-color" to BACKGROUND_COLOR_VALUES,
        "background-image" to BACKGROUND_IMAGE_VALUES,
        "background-origin" to BACKGROUND_ORIGIN_VALUES,
        "background-position" to BACKGROUND_POSITION_VALUES,
        "background-position-x" to BACKGROUND_POSITION_VALUES,
        "background-position-y" to BACKGROUND_POSITION_VALUES,
        "background-repeat" to BACKGROUND_REPEAT_VALUES,
        "background-size" to BACKGROUND_SIZE_VALUES,
        "block-size" to AUTO_VALUE,
        "border" to BORDER_COLOR_VALUES + BORDER_STYLE_VALUES + BORDER_WIDTH_VALUES,
        "border-block" to EMPTY_VALUES,
        "border-block-color" to BORDER_COLOR_VALUES,
        "border-block-end-color" to BORDER_COLOR_VALUES,
        "border-block-end-style" to BORDER_STYLE_VALUES,
        "border-block-end-width" to BORDER_WIDTH_VALUES,
        "border-block-start-color" to BORDER_COLOR_VALUES,
        "border-block-start-style" to BORDER_STYLE_VALUES,
        "border-block-start-width" to BORDER_WIDTH_VALUES,
        "border-block-style" to BORDER_STYLE_VALUES,
        "border-block-width" to BORDER_WIDTH_VALUES,
        "border-bottom" to EMPTY_VALUES,
        "border-bottom-color" to BORDER_COLOR_VALUES,
        "border-bottom-left-radius" to EMPTY_VALUES,
        "border-bottom-right-radius" to EMPTY_VALUES,
        "border-bottom-style" to BORDER_STYLE_VALUES,
        "border-bottom-width" to BORDER_WIDTH_VALUES,
        "border-collapse" to values("separate", "collapse"),
        "border-color" to BORDER_COLOR_VALUES,
        "border-end-end-radius" to EMPTY_VALUES,
        "border-end-start-radius" to EMPTY_VALUES,
        "border-image" to EMPTY_VALUES,
        "border-image-outset" to EMPTY_VALUES,
        "border-image-repeat" to EMPTY_VALUES,
        "border-image-slice" to EMPTY_VALUES,
        "border-image-source" to EMPTY_VALUES,
        "border-image-width" to AUTO_VALUE,
        "border-inline" to EMPTY_VALUES,
        "border-inline-color" to COLOR_VALUES,
        "border-inline-end-color" to COLOR_VALUES,
        "border-inline-end-style" to STYLE_VALUES,
        "border-inline-end-width" to WIDTH_VALUES,
        "border-inline-start-color" to COLOR_VALUES,
        "border-inline-start-style" to STYLE_VALUES,
        "border-inline-start-width" to WIDTH_VALUES,
        "border-inline-style" to STYLE_VALUES,
        "border-inline-width" to WIDTH_VALUES,
        "border-left" to EMPTY_VALUES,
        "border-left-color" to COLOR_VALUES,
        "border-left-style" to STYLE_VALUES,
        "border-left-width" to WIDTH_VALUES,
        "border-radius" to EMPTY_VALUES,
        "border-right" to EMPTY_VALUES,
        "border-right-color" to COLOR_VALUES,
        "border-right-style" to STYLE_VALUES,
        "border-right-width" to WIDTH_VALUES,
        "border-spacing" to EMPTY_VALUES,
        "border-start-end-radius" to EMPTY_VALUES,
        "border-start-start-radius" to EMPTY_VALUES,
        "border-style" to STYLE_VALUES,
        "border-top" to EMPTY_VALUES,
        "border-top-color" to COLOR_VALUES,
        "border-top-left-radius" to EMPTY_VALUES,
        "border-top-right-radius" to EMPTY_VALUES,
        "border-top-style" to STYLE_VALUES,
        "border-top-width" to WIDTH_VALUES,
        "border-width" to WIDTH_VALUES,
        "bottom" to AUTO_VALUE,
        "box-decoration-break" to values("slice", "clone", "unset"),
        "box-reflect" to values("none", "below", "above", "left", "right"),
        "box-shadow" to values("none", "inset"),
        "box-sizing" to values("content-box", "border-box"),
        "break-after" to BREAK_VALUES,
        "break-before" to BREAK_VALUES,
        "break-inside" to BREAK_VALUES,
        "caption-side" to values("top", "bottom"),
        "caret-color" to COLOR_VALUES + AUTO_VALUE,
        "clear" to values("none", "left", "right", "both"),
        "clip" to AUTO_VALUE,
        "clip-path" to values("margin-box", "border-box", "padding-box", "content-box", "fill-box", "stroke-box", "view-box", "none"),
        "color" to COLOR_VALUES,
        "column-count" to AUTO_VALUE,
        "column-fill" to values("balance", "auto"),
        "column-gap" to COLUMN_GAP_VALUES,
        "column-rule" to EMPTY_VALUES,
        "column-rule-color" to COLOR_VALUES,
        "column-rule-style" to STYLE_VALUES,
        "column-rule-width" to WIDTH_VALUES,
        "column-span" to values("none", "all"),
        "column-width" to AUTO_VALUE,
        "columns" to EMPTY_VALUES,
        "content" to values("normal", "none", "counter", "attr", "open-quote", "close-quote", "no-open-quote", "no-close-quote"),
        "counter-increment" to NONE_VALUE,
        "counter-reset" to NONE_VALUE,
        "cursor" to values("alias", "all-scroll", "auto", "cell", "context-menu", "col-resize", "copy", "crosshair", "default", "e-resize", "ew-resize", "help", "move", "n-resize", "ne-resize", "nesw-resize", "ns-resize", "nw-resize", "nwse-resize", "no-drop", "none", "not-allowed", "pointer", "progress", "row-resize", "s-resize", "se-resize", "sw-resize", "text", "vertical-text", "w-resize", "wait", "zoom-in", "zoom-out"),
        "direction" to values("ltr", "rtl"),
        "display" to values("inline", "block", "contents", "flex", "grid", "inline-block", "inline-flex", "inline-grid", "inline-table", "list-item", "run-in", "table", "table-caption", "table-column-group", "table-header-group", "table-footer-group", "table-row-group", "table-cell", "table-column", "table-row", "none"),
        "empty-cells" to values("show", "hide"),
        "filter" to NONE_VALUE +
                function("blur") +
                function("brightness") +
                function("contrast") +
                function("drop-shadow") +
                function("grayscale") +
                function("hue-rotate") +
                function("invert") +
                function("opacity") +
                function("saturate") +
                function("sepia") +
                function("url"),
        "flex" to FLEX_BASIS_VALUES + FLEX_GROW_VALUES + FLEX_SHRINK_VALUES,
        "flex-basis" to FLEX_BASIS_VALUES,
        "flex-direction" to values("row", "row-reverse", "column", "column-reverse"),
        "flex-flow" to EMPTY_VALUES,
        "flex-grow" to FLEX_GROW_VALUES,
        "flex-shrink" to FLEX_SHRINK_VALUES,
        "flex-wrap" to values("nowrap", "wrap", "wrap-reverse"),
        "float" to values("none", "left", "right"),
        "font" to values("caption", "icon", "menu", "message-box", "small-caption", "status-bar") +
                FONT_STYLE_VALUES +
                FONT_VARIANT_VALUES +
                FONT_WEIGHT_VALUES +
                FONT_SIZE_VALUES + LINE_HEIGHT_VALUES +
                FONT_FAMILY_VALUES,
        "font-family" to FONT_FAMILY_VALUES,
        "font-feature-settings" to NORMAL_VALUE,
        "font-kerning" to values("auto", "normal", "none"),
        "font-size" to FONT_SIZE_VALUES,
        "font-size-adjust" to NONE_VALUE,
        "font-stretch" to values("ultra-condensed", "extra-condensed", "condensed", "semi-condensed", "normal", "semi-expanded", "expanded", "extra-expanded", "ultra-expanded"),
        "font-style" to FONT_STYLE_VALUES,
        "font-variant" to FONT_VARIANT_VALUES,
        "font-variant-caps" to values("normal", "small-caps", "all-small-caps", "petite-caps", "all-petite-caps", "unicase", "titling-caps", "unset"),
        "font-weight" to FONT_WEIGHT_VALUES,
        "gap" to COLUMN_GAP_VALUES + ROW_GAP_VALUES,
        "grid" to NONE_VALUE,
        "grid-area" to EMPTY_VALUES,
        "grid-auto-columns" to values("auto", "max-content", "min-content"),
        "grid-auto-flow" to values("row", "column", "dense", "row dense", "column dense"),
        "grid-auto-rows" to values("auto", "max-content", "min-content"),
        "grid-column" to AUTO_VALUE,
        "grid-column-end" to EMPTY_VALUES,
        "grid-column-gap" to EMPTY_VALUES,
        "grid-column-start" to AUTO_VALUE,
        "grid-gap" to EMPTY_VALUES,
        "grid-row" to EMPTY_VALUES,
        "grid-row-end" to AUTO_VALUE,
        "grid-row-gap" to EMPTY_VALUES,
        "grid-row-start" to AUTO_VALUE,
        "grid-template" to NONE_VALUE,
        "grid-template-areas" to NONE_VALUE,
        "grid-template-columns" to values("none", "auto", "max-content", "min-content"),
        "grid-template-rows" to values("none", "auto", "max-content", "min-content"),
        "hanging-punctuation" to values("none", "first", "last", "allow-end", "force-end"),
        "height" to AUTO_VALUE,
        "hyphens" to values("none", "manual", "auto"),
        "image-rendering" to values("auto", "smooth", "high-quality", "crisp-edges", "pixelated"),
        "inline-size" to AUTO_VALUE,
        "inset" to AUTO_VALUE,
        "inset-block" to AUTO_VALUE,
        "inset-block-end" to AUTO_VALUE,
        "inset-block-start" to AUTO_VALUE,
        "inset-inline" to AUTO_VALUE,
        "inset-inline-end" to AUTO_VALUE,
        "inset-inline-start" to AUTO_VALUE,
        "isolation" to values("auto", "isolate"),
        "justify-content" to values("flex-start", "flex-end", "center", "space-between", "space-around", "space-evenly"),
        "justify-items" to values("legacy", "normal", "stretch"),
        "justify-self" to values("auto", "normal", "stretch"),
        "left" to AUTO_VALUE,
        "letter-spacing" to NORMAL_VALUE,
        "line-height" to LINE_HEIGHT_VALUES,
        "list-style" to LIST_STYLE_IMAGE_VALUES + LIST_STYLE_POSITION_VALUES + LIST_STYLE_TYPE_VALUES,
        "list-style-image" to LIST_STYLE_IMAGE_VALUES,
        "list-style-position" to LIST_STYLE_POSITION_VALUES,
        "list-style-type" to LIST_STYLE_TYPE_VALUES,
        "margin" to AUTO_VALUE,
        "margin-block" to AUTO_VALUE,
        "margin-block-end" to AUTO_VALUE,
        "margin-block-start" to AUTO_VALUE,
        "margin-bottom" to AUTO_VALUE,
        "margin-inline" to AUTO_VALUE,
        "margin-inline-end" to AUTO_VALUE,
        "margin-inline-start" to AUTO_VALUE,
        "margin-left" to AUTO_VALUE,
        "margin-right" to AUTO_VALUE,
        "margin-top" to AUTO_VALUE,
        "mask-image" to NONE_VALUE,
        "mask-mode" to values("match-source", "luminance", "alpha"),
        "mask-origin" to values("border-box", "content-box", "padding-box", "margin-box", "fill-box", "stroke-box", "view-box"),
        "mask-position" to values("left", "right", "center", "top", "bottom"),
        "mask-repeat" to values("repeat", "repeat-x", "repeat-y", "space", "round", "no-repeat"),
        "mask-size" to values("auto", "size", "contain", "cover"),
        "max-block-size" to AUTO_VALUE,
        "max-height" to NONE_VALUE,
        "max-inline-size" to AUTO_VALUE,
        "max-width" to NONE_VALUE,
        "min-block-size" to AUTO_VALUE,
        "min-inline-size" to AUTO_VALUE,
        "min-height" to EMPTY_VALUES,
        "min-width" to EMPTY_VALUES,
        "mix-blend-mode" to values("normal", "multiply", "screen", "overlay", "darken", "lighten", "color-dodge", "color-burn", "difference", "exclusion", "hue", "saturation", "color", "luminosity"),
        "object-fit" to values("fill", "contain", "cover", "scale-down", "none"),
        "object-position" to EMPTY_VALUES,
        "offset" to AUTO_VALUE,
        "offset-anchor" to AUTO_VALUE,
        "offset-distance" to AUTO_VALUE,
        "offset-path" to NONE_VALUE,
        "offset-rotate" to AUTO_VALUE,
        "opacity" to EMPTY_VALUES,
        "order" to EMPTY_VALUES,
        "orphans" to EMPTY_VALUES,
        "outline" to EMPTY_VALUES,
        "outline-color" to COLOR_VALUES,
        "outline-offset" to EMPTY_VALUES,
        "outline-style" to STYLE_VALUES,
        "outline-width" to WIDTH_VALUES,
        "overflow" to values("visible", "hidden", "clip", "scroll", "auto"),
        "overflow-anchor" to values("auto", "none"),
        "overflow-wrap" to values("normal", "anywhere", "break-word"),
        "overflow-x" to OVERFLOW_VALUES,
        "overflow-y" to OVERFLOW_VALUES,
        "overscroll-behavior" to OVERSCROLL_BEHAVIOR_VALUES,
        "overscroll-behavior-block" to OVERSCROLL_BEHAVIOR_VALUES,
        "overscroll-behavior-inline" to OVERSCROLL_BEHAVIOR_VALUES,
        "overscroll-behavior-x" to OVERSCROLL_BEHAVIOR_VALUES,
        "overscroll-behavior-y" to OVERSCROLL_BEHAVIOR_VALUES,
        "padding" to EMPTY_VALUES,
        "padding-block" to AUTO_VALUE,
        "padding-block-end" to AUTO_VALUE,
        "padding-block-start" to AUTO_VALUE,
        "padding-bottom" to EMPTY_VALUES,
        "padding-inline" to AUTO_VALUE,
        "padding-inline-end" to AUTO_VALUE,
        "padding-inline-start" to AUTO_VALUE,
        "padding-left" to EMPTY_VALUES,
        "padding-right" to EMPTY_VALUES,
        "padding-top" to EMPTY_VALUES,
        "page-break-after" to values("auto", "always", "avoid", "left", "right"),
        "page-break-before" to values("auto", "always", "avoid", "left", "right"),
        "page-break-inside" to values("auto", "avoid"),
        "paint-order" to NORMAL_VALUE,
        "perspective" to NONE_VALUE,
        "perspective-origin" to EMPTY_VALUES,
        "place-content" to values("normal", "stretch", "start", "end", "center", "space-between", "space-around", "space-evenly"),
        "place-items" to values("normal legacy", "baseline", "center", "end", "start", "stretch"),
        "place-self" to values("auto", "normal", "stretch", "start", "end", "right"),
        "pointer-events" to values("auto", "none"),
        "position" to values("static", "absolute", "fixed", "relative", "sticky"),
        "quotes" to NONE_VALUE,
        "resize" to values("none", "both", "horizontal", "vertical"),
        "right" to AUTO_VALUE,
        "rotate" to EMPTY_VALUES,
        "row-gap" to ROW_GAP_VALUES,
        "scale" to EMPTY_VALUES,
        "scroll-behavior" to values("auto", "smooth"),
        "scroll-margin" to EMPTY_VALUES,
        "scroll-margin-block" to EMPTY_VALUES,
        "scroll-margin-block-end" to EMPTY_VALUES,
        "scroll-margin-block-start" to EMPTY_VALUES,
        "scroll-margin-bottom" to EMPTY_VALUES,
        "scroll-margin-inline" to EMPTY_VALUES,
        "scroll-margin-inline-end" to EMPTY_VALUES,
        "scroll-margin-inline-start" to EMPTY_VALUES,
        "scroll-margin-left" to EMPTY_VALUES,
        "scroll-margin-right" to EMPTY_VALUES,
        "scroll-margin-top" to EMPTY_VALUES,
        "scroll-padding" to AUTO_VALUE,
        "scroll-padding-block" to AUTO_VALUE,
        "scroll-padding-block-end" to AUTO_VALUE,
        "scroll-padding-block-start" to AUTO_VALUE,
        "scroll-padding-bottom" to AUTO_VALUE,
        "scroll-padding-inline" to AUTO_VALUE,
        "scroll-padding-inline-end" to AUTO_VALUE,
        "scroll-padding-inline-start" to AUTO_VALUE,
        "scroll-padding-left" to AUTO_VALUE,
        "scroll-padding-right" to AUTO_VALUE,
        "scroll-padding-top" to AUTO_VALUE,
        "scroll-snap-align" to values("none", "start", "end", "center"),
        "scroll-snap-stop" to values("normal", "always"),
        "scroll-snap-type" to values("none", "x", "y", "block", "inline", "both", "mandatory", "proximity"),
        "scrollbar-color" to COLOR_VALUES + AUTO_VALUE,
        "tab-size" to EMPTY_VALUES,
        "table-layout" to values("auto", "fixed"),
        "text-align" to values("left", "right", "center", "justify"),
        "text-align-last" to values("auto", "left", "right", "center", "justify", "start", "end"),
        "text-decoration" to EMPTY_VALUES,
        "text-decoration-color" to COLOR_VALUES,
        "text-decoration-line" to values("none", "underline", "overline", "line-through"),
        "text-decoration-style" to values("solid", "double", "dotted", "dashed", "wavy"),
        "text-decoration-thickness" to values("auto", "from-font"),
        "text-indent" to EMPTY_VALUES,
        "text-justify" to values("auto", "inter-word", "inter-character", "none"),
        "text-orientation" to values("mixed", "upright", "sideways", "sideways-right", "use-glyph-orientation"),
        "text-overflow" to values("clip", "ellipsis"),
        "text-shadow" to NONE_VALUE,
        "text-transform" to values("none", "capitalize", "uppercase", "lowercase"),
        "top" to AUTO_VALUE,
        "transform" to TRANSFORM_VALUES,
        "transform-origin" to EMPTY_VALUES,
        "transform-style" to values("flat", "preserve-3d"),
        "transition" to EMPTY_VALUES,
        "transition-delay" to EMPTY_VALUES,
        "transition-duration" to EMPTY_VALUES,
        "transition-property" to values("none", "all"),
        "transition-timing-function" to TIMING_FUNCTION_VALUES,
        "translate" to EMPTY_VALUES,
        "unicode-bidi" to values("normal", "embed", "bidi-override"),
        "user-select" to values("auto", "none", "text", "all"),
        "vertical-align" to values("baseline", "sub", "super", "top", "text-top", "middle", "bottom", "text-bottom"),
        "visibility" to values("visible", "hidden", "collapse"),
        "white-space" to values("normal", "nowrap", "pre", "pre-line", "pre-wrap"),
        "widows" to EMPTY_VALUES,
        "width" to AUTO_VALUE,
        "word-break" to values("normal", "break-all", "keep-all", "break-word"),
        "word-spacing" to NORMAL_VALUE,
        "word-wrap" to values("normal", "break-word"),
        "writing-mode" to values("horizontal-tb", "vertical-rl", "vertical-lr"),
        "z-index" to AUTO_VALUE,
    )

    val PROPERTY_NAME_LOOKUP_ELEMENTS = PROPERTY_VALUES_LOOKUP_ELEMENTS.keys.map { LookupElementBuilder.create(it) }

    private fun values(vararg names: String): List<LookupElement> {
        return names.map { LookupElementBuilder.create(it) }
    }

    private fun color(name: String, hexString: String): LookupElement {
        return LookupElementBuilder.create(name)
            .withIcon(ColorIcon(8, Color.decode(hexString)))
    }

    fun function(name: String): LookupElement {
        return LookupElementBuilder.create("$name()")
            .withInsertHandler(moveCaretRelatively(-1, 0))
            .withPresentableText(name).bold()
            .withTailText("(...)")
    }
}

private fun moveCaretRelatively(columnShift: Int, lineShift: Int): InsertHandler<LookupElement> {
    return InsertHandler { context, _ -> context.editor.caretModel.moveCaretRelatively(columnShift, lineShift, false, false, true) }
}