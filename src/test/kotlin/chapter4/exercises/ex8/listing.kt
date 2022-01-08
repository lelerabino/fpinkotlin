package chapter4.exercises.ex8

sealed class Partial<out E, out A>

data class Failures<out E>(val values: List<E>) : Partial<E, Nothing>()

data class Success<out A>(val value: A) : Partial<Nothing, A>()
