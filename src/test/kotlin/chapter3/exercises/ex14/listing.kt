package chapter3.exercises.ex14

import chapter3.Cons
import chapter3.List
import chapter3.Nil
import chapter3.append
import chapter3.foldRight
import chapter3.reverse
import io.kotlintest.shouldBe
import io.kotlintest.specs.WordSpec

// tag::init[]
fun <A> concat(lla: List<List<A>>): List<A> =
    foldRight(lla, List.empty()) { l1, l2 -> append(l1, l2) }

fun <A> concat2(lla: List<List<A>>): List<A> {
    tailrec fun loop(acc: List<A>, ll: List<List<A>>): List<A> {
        return when (ll) {
            is Nil -> acc
            is Cons -> {
                when (ll.head) {
                    is Nil -> loop(acc, ll.tail)
                    is Cons -> loop(
                        Cons((ll.head as Cons).head, acc),
                        Cons((ll.head as Cons).tail, ll.tail)
                    )
                }
            }
        }
    }

    return reverse(loop(List.empty(), lla))
}

// end::init[]

//TODO: Enable tests by removing `!` prefix
class Exercise14 : WordSpec({
    "list concat" should {
        "concatenate a list of lists into a single list" {
            concat(
                List.of(
                    List.of(1, 2, 3),
                    List.of(4, 5, 6)
                )
            ) shouldBe List.of(1, 2, 3, 4, 5, 6)

            concat2(
                List.of(
                    List.of(1, 2, 3), List.of(4, 5, 6)
                )
            ) shouldBe List.of(1, 2, 3, 4, 5, 6)
        }
    }
})
