package at.fhj.swengb.assignments.functional

/**
  * In this assignment you have the chance to demonstrate basic understanding of
  * functions like map/filter/foldleft a.s.o.
  **/
object FunctionalAssignment {

  /**
    * A function which returns its parameters in a changed order. Look at the type signature.
    */
  def flip[A, B](t: (A, B)): (B, A) = (t._2,t._1)

  /**
    * given a Seq[A] and a function f : A => B, return a Seq[B]
    */
  def unknown[A, B](as: Seq[A], fn: A => B): Seq[B] = as.map(fn)

  /**
    * Returns the absolute value of the parameter i.
    *
    * @param i a value, either with a positive or a negative sign.
    * @return
    */
  def abs(i: Int): Int = if (i < 0) -1*i else i


  // Describe with your own words what this function does.
  // in the comment below, add a description what this function does - in your own words - and give
  // the parameters more descriptive names.
  // Here a foldleft operation is used as a method on the parameter "as" (Sequence of type A) and something of type B is returned
  // this fold left take parameter b (something of type B) as start value and applies the function fn
  //
  // Afterwards, compare your new naming scheme with the original one.
  // What did you gain with your new names? What did you loose?
  //
  /**
    *
    * @param as: this is a Sequence of type A
    * @param b: this is something of type B
    * @param fn: This is a function that take a tuple of (type B, type A) and returns a value of type B
    * @tparam A: a value of different data type than B
    * @tparam B: a value of different data type than A
    * @return: the return type is of type B
    */
  def op[A, B](as: Seq[A], b: B)(fn: (B, A) => B): B = as.foldLeft(b)(fn)

  /**
    * implement the summation of the given numbers parameter.
    * Use the function 'op' defined above.
    *
    * @param numbers
    * @return
    */
  def sum(numbers: Seq[Int]): Int = op(numbers, 0)(_+_)


  /**
    * calculate the factorial number of the given parameter.
    *
    * for example, if we pass '5' as parameter, the function should do the following:
    *
    * 5! = 5 * 4 * 3 * 2 * 1
    *
    * @param i parameter for which the factorial must be calculated
    * @return i!
    */
  def fact(i: Int): Int = if (i == 0) 1 else i * fact(i-1)

  /**
    * compute the n'th fibonacci number
    *
    * hint: use a internal loop function which should be written in a way that it is tail
    * recursive.
    *
    * https://en.wikipedia.org/wiki/Fibonacci_number
    */
  def fib(n: Int): Int = n match {
    case 0 => 0
    case 1 => 1
    case _ => fib(n-1)+fib(n-2)
  }

  /**
    * Implement a isSorted which checks whether an Array[A] is sorted according to a
    * given comparison function.
    *
    * Implementation hint: you always have to compare two consecutive elements of the array.
    * Elements which are equal are considered to be ordered.
    */
  def isSorted[A](as: Array[A], gt: (A, A) => Boolean): Boolean = AND (for ( x <- as.grouped(2)) yield x.apply(gt))
    //(as.grouped(2)).map(_.gt)


  /**
    * Takes both lists and combines them, element per element.
    *
    * If one sequence is shorter than the other one, the function stops at the last element
    * of the shorter sequence.
    */
  def genPairs[A, B](as: Seq[A], bs: Seq[B]): Seq[(A, B)] = as zip bs

  // a simple definition of a linked list, we define our own list data structure
  sealed trait MyList[+A]

  case object MyNil extends MyList[Nothing]

  case class Cons[+A](head: A, tail: MyList[A]) extends MyList[A]

  // the companion object contains handy methods for our data structure.
  // it also provides a convenience constructor in order to instantiate a MyList without hassle
  object MyList {

    def sum[Int](list: MyList[Int]): Int = list.foldLeft(0)(acc i => acc + i)

    def product[Int](list: MyList[Int]): Int = list.fold(0)(accu im => accu*im)

    def apply[A](as: A*): MyList[A] = {
      as match {
        case Nil => MyNil
        case h :: tl => Cons(h, apply(tl: _*))
      }
    }

  }

}

