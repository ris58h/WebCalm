package ris58h.webcalm.css

import java.awt.Color

object CssColors {
    fun parseHexColor(hexColor: String): Color? {
        if (hexColor.isEmpty()) return null
        if (hexColor[0] != '#') return null

        val hex = hexColor.substring(1)
        return when (hex.length) {
            3 -> parse3(hex)
            4 -> parse4(hex)
            6 -> parse6(hex)
            8 -> parse8(hex)
            else -> null
        }
    }

    private fun parse3(hex: String): Color? {
        return parse6(duplicateEveryChar(hex))
    }

    private fun parse4(hex: String): Color? {
        return parse8(duplicateEveryChar(hex))
    }

    private fun duplicateEveryChar(string: String): String {
        return buildString(string.length * 2) { string.forEach { append(it); append(it) } }
    }

    private fun parse6(hex: String): Color? {
        val r = parseHex(hex.substring(0, 2)) ?: return null
        val g = parseHex(hex.substring(2, 4)) ?: return null
        val b = parseHex(hex.substring(4, 6)) ?: return null
        return Color(r, g, b)
    }

    private fun parse8(hex: String): Color? {
        val r = parseHex(hex.substring(0, 2)) ?: return null
        val g = parseHex(hex.substring(2, 4)) ?: return null
        val b = parseHex(hex.substring(4, 6)) ?: return null
        val a = parseHex(hex.substring(6, 8)) ?: return null
        return Color(r, g, b, a)
    }

    private fun parseHex(hex: String) = hex.toIntOrNull(16)

    fun toHexColor(color: Color): String {
        val rgb = color.rgb and 0x00ffffff
        val alpha = color.alpha
        return if (alpha == 255) "#%06x".format(rgb)
        else "#%06x%02x".format(rgb, alpha)
    }

    private val HEX_COLORS_BY_NAME = mapOf(
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
    val COLORS_BY_NAME = HEX_COLORS_BY_NAME.mapValues { e -> parseHexColor(e.value)!! }
    val SYSTEM_COLORS = listOf(
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
}