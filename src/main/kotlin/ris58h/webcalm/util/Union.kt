package ris58h.webcalm.util

class Union2<F, S> private constructor(private val first: F?, private val second: S?) {
    fun <R> doWhen(firstCallback: (F) -> R, secondCallback: (S) -> R): R {
        return if (first != null) firstCallback(first)
        else if (second != null) secondCallback(second)
        else throw IllegalStateException()
    }

    companion object {
        fun <F, S> first(first: F): Union2<F, S> {
            return Union2(first, null)
        }

        fun <F, S> second(second: S): Union2<F, S> {
            return Union2(null, second)
        }
    }
}