package ris58h.webcalm.util

class Union2<F, S> private constructor(private val first: F?, private val second: S?) {
    fun <R> doWhen(firstCallback: (F) -> R, secondCallback: (S) -> R): R {
        return if (first != null) firstCallback(first)
        else if (second != null) secondCallback(second)
        else throw IllegalStateException()
    }

    companion object {
        fun <F, S> first(first: F): Union2<F, S> = Union2(first, null)

        fun <F, S> second(second: S): Union2<F, S> = Union2(null, second)
    }
}

class Union3<F, S, T> private constructor(private val first: F?, private val second: S?, private val third: T?) {
    fun <R> doWhen(firstCallback: (F) -> R, secondCallback: (S) -> R, thirdCallback: (T) -> R): R {
        return if (first != null) firstCallback(first)
        else if (second != null) secondCallback(second)
        else if (third != null) thirdCallback(third)
        else throw IllegalStateException()
    }

    companion object {
        fun <F, S, T> first(first: F): Union3<F, S, T> = Union3(first, null, null)

        fun <F, S, T> second(second: S): Union3<F, S, T> = Union3(null, second, null)

        fun <F, S, T> third(third: T): Union3<F, S, T> = Union3(null, null, third)
    }
}