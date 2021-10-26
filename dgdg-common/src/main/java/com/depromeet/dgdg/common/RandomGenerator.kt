package com.depromeet.dgdg.common

object RandomGenerator {
    private val charPool : List<Char> = ('a'..'z') + ('A'..'Z') + ('0'..'9')
    private const val NAME_LENGTH = 6

    @JvmStatic
    fun getDefaultName() : String {
        return (1..NAME_LENGTH)
            .map { kotlin.random.Random.nextInt(0, charPool.size) }
            .map(charPool::get)
            .joinToString("")
    }
}
