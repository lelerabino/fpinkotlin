package chapter2.exercises.ex1

import io.kotlintest.shouldBe
import io.kotlintest.specs.WordSpec
import kotlinx.collections.immutable.persistentMapOf

//TODO: Enable tests by removing `!` prefix
class Exercise1 : WordSpec({
    //tag::init[]
    fun fib(i: Int): Int {
        tailrec fun loop(index: Int, prev1: Int, prev: Int): Int =
            if (index == i) (prev1 + prev) else loop(
                index = index + 1,
                prev1 = prev,
                prev = prev1 + prev
            )

        return if (i <= 1) i else loop(index = 2, prev1 = 0, prev = 1)
    }

    //end::init[]

    "fib" should {
        "return the nth fibonacci number" {
            persistentMapOf(
                0 to 0,
                1 to 1,
                2 to 1,
                3 to 2,
                4 to 3,
                5 to 5,
                6 to 8,
                7 to 13,
                8 to 21,
                9 to 34
            ).forEach { (n, num) ->
                fib(n) shouldBe num
            }
        }
    }
})
