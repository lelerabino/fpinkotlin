package chapter3.exercises.ex3

import chapter3.Cons
import chapter3.List
import chapter3.Nil
import io.kotlintest.shouldBe
import io.kotlintest.shouldThrow
import io.kotlintest.specs.WordSpec

// tag::init[]
fun <A> drop(l: List<A>, n: Int): List<A> {
    return if (n == 0) l else when (l) {
        is Cons -> drop(l.tail, n - 1)
        is Nil -> throw IllegalStateException("Cannot drop more elements in the list!")
    }
}

// end::init[]

//TODO: Enable tests by removing `!` prefix
class Exercise3 : WordSpec({
    "list drop" should {
        "!drop a given number of elements within capacity" {
            drop(List.of(1, 2, 3, 4, 5), 3) shouldBe
                List.of(4, 5)
        }

        "drop a given number of elements up to capacity" {
            drop(List.of(1, 2, 3, 4, 5), 5) shouldBe Nil
        }

        """throw an illegal state exception when dropped elements
            exceed capacity""" {
            shouldThrow<IllegalStateException> {
                drop(List.of(1, 2, 3, 4, 5), 6)
            }
        }
    }
})
