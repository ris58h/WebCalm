package ris58h.webcalm.javascript

// https://developer.mozilla.org/en-US/docs/Web/JavaScript/Reference/Global_Objects
object JavaScriptBuiltInObjects {
    fun isGlobalValue(name: String) = GLOBAL_VALUES.contains(name)
    fun isGlobalFunction(name: String) = GLOBAL_FUNCTIONS.contains(name)
    fun isGlobalObject(name: String) = GLOBAL_OBJECTS.contains(name)
    fun isGlobalNamespace(name: String) = GLOBAL_NAMESPACES.contains(name)

    private val GLOBAL_VALUES = setOf(
        "globalThis",
        "Infinity",
        "NaN",
        "undefined",
    )
    private val GLOBAL_FUNCTIONS = setOf(
        "eval",
        "isFinite",
        "isNaN",
        "parseFloat",
        "parseInt",
        "decodeURI",
        "decodeURIComponent",
        "encodeURI",
        "encodeURIComponent",
        "escape",
        "unescape",
    )
    private val GLOBAL_OBJECTS = setOf(
        // Fundamental objects
        "Object",
        "Function",
        "Boolean",
        "Symbol",
        // Error objects
        "Error",
        "AggregateError",
        "EvalError",
        "RangeError",
        "ReferenceError",
        "SyntaxError",
        "TypeError",
        "URIError",
        "InternalError",
        // Numbers and dates
        "Number",
        "BigInt",
        "Date",
        // Text processing
        "String",
        "Date",
        // Indexed collections
        "Array",
        "Int8Array",
        "Uint8Array",
        "Uint8ClampedArray",
        "Int16Array",
        "UintArray",
        "Int32Array",
        "Uint32Array",
        "BigInt64Array",
        "BigUint64Array",
        "Float32Array",
        "Float64Array",
        // Keyed collections
        "Map",
        "Set",
        "WeakMap",
        "WeakSet",
        // Structured data
        "ArrayBuffer",
        "SharedArrayBuffer",
        "DataView",
        // Managing memory
        "WeakRef",
        "FinalizationRegistry",
        // Control abstraction objects
        "Iterator",
        "AsyncIterator",
        "Promise",
        "GeneratorFunction",
        "AsyncGeneratorFunction",
        "Generator",
        "AsyncGenerator",
        "AsyncFunction",
        // Reflection
        "Proxy",
    )
    private val GLOBAL_NAMESPACES = setOf(
        "Math",
        "Atomics",
        "JSON",
        "Reflect",
        "Intl",
    )
}