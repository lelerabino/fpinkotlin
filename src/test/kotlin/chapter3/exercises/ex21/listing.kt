package chapter3.exercises.ex21

import chapter3.Cons
import chapter3.List
import chapter3.Nil
import chapter3.append
import chapter3.reverse
import io.kotlintest.shouldBe
import io.kotlintest.specs.WordSpec

// tag::init[]
fun addWrong(xa: List<Int>, xb: List<Int>): List<Int> {
    tailrec fun loop(acc: List<Int>, arest: List<Int>, brest: List<Int>): List<Int>{
       return when(arest){
           is Nil ->{
              append(acc, reverse(brest))
           }
           is Cons ->{
               when(brest){
                   is Nil ->{
                       append(acc, reverse(arest))
                   }
                   is Cons ->{
                       loop(Cons(arest.head + brest.head, acc), arest.tail, brest.tail)
                   }
               }
           }
       }
    }

    return reverse(loop(List.empty(), xa, xb))
}

fun add(xa: List<Int>, xb: List<Int>): List<Int> =
    when (xa) {
        is Nil -> Nil
        is Cons -> when (xb) {
            is Nil -> Nil
            is Cons ->
                Cons(xa.head + xb.head,
                    add(xa.tail, xb.tail)
                )
        }
    }

// end::init[]

//TODO: Enable tests by removing `!` prefix
class Exercise21 : WordSpec({
    "list add" should {
        "add elements of two corresponding lists" {
            add(List.of(1, 2, 3), List.of(4, 5, 6)) shouldBe
                List.of(5, 7, 9)
        }

        "add elements of two lists with different length" {
            add(List.of(1, 2, 3), List.of(4)) shouldBe
                List.of(5)
        }
    }
})
