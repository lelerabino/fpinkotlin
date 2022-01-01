package chapter3.exercises.ex12

import chapter3.Cons
import chapter3.List
import chapter3.foldLeft
import chapter3.foldRight
import io.kotlintest.shouldBe
import io.kotlintest.specs.WordSpec

// tag::init[]
fun <A, B> foldLeftR(xs: List<A>, z: B, f: (B, A) -> B): B =
    foldRight(
        xs,
        { b: B -> b },
        { a, g ->
            { b ->
                g(f(b, a))
            }
        })(z)

fun <A, B> foldRightL(xs: List<A>, z: B, f: (A, B) -> B): B = foldLeft(xs,
    { b: B -> b },
    { g, a -> { b -> g(f(a, b)) } }
)(z)

// end::init[]

//TODO: Enable tests by removing `!` prefix
class Exercise12 : WordSpec({
    "list foldLeftR" should {
        "implement foldLeft functionality using foldRight" {
            foldLeftR(
                List.of(1, 2, 3, 4, 5),
                0,
                { x, y -> x + y }) shouldBe 15
        }

        "reverse list using foldLeftR" {
            foldLeftR<Int, List<Int>>(
                List.of(1, 2, 3, 4, 5),
                List.empty(),
                { x, y -> Cons(y, x) }) shouldBe List.of(5, 4, 3, 2, 1)
        }
    }


    "list foldRightL" should {
        "implement foldRight functionality using foldLeft" {
            foldRightL(
                List.of(1, 2, 3, 4, 5),
                0
            ) { x, y -> x + y } shouldBe 15
        }

        "copy list using foldRightL" {
            foldRightL<Int, List<Int>>(
                List.of(1, 2, 3, 4, 5),
                List.empty()
            ) { x, y -> Cons(x, y) } shouldBe List.of(1, 2, 3, 4, 5)
        }
    }
})
