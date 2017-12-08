package at.fhj.swengb.apps.calculator

import java.util.NoSuchElementException

import scala.util.Try

/**
  * Companion object for our reverse polish notation calculator.
  */
object RpnCalculator {

  /**
    * Returns empty RpnCalculator if string is empty, otherwise pushes all operations
    * on the stack of the empty RpnCalculator.
    *
    * @param s a string representing a calculation, for example '1 2 +'
    * @return
    */
  def apply(s: String): Try[RpnCalculator] =
    if (!s.isEmpty) { // der Stack ist eine Liste aus Op
      val derStack: List[Op] = s.split(' ').map(elem => Op(elem)).toList
      /*Splitte den String an den Leerzeichen => List[String], mappe darauf eine Funktion, die schaut
       ob das element ein Op ist und anschließend in eine Liste verwandelt
      */
      var derCalculator: Try[RpnCalculator] = Try(RpnCalculator())
      derStack.foreach(element => derCalculator = derCalculator.get.push(element))
      derCalculator
    } else {
      Try(RpnCalculator())
    }
}
  /**
    * Reverse Polish Notation Calculator.
    *
    * @param stack a datastructure holding all operations
    */
  case class RpnCalculator(stack: List[Op] = Nil) {

    /**
      * By pushing Op on the stack, the Op is potentially executed. If it is a Val, it the op instance is just put on the
      * stack, if not then the stack is examined and the correct operation is performed.
      *
      * @param op
      * @return
      */

    def push(op: Op): Try[RpnCalculator] = op match {
      case x: Val => Try(RpnCalculator(stack :+ x))
      case y: BinOp => {
        def valNexter3000(rpn: RpnCalculator): Val = {
          val oG = rpn.peek()
          oG match {
            case x: Val => x
            case _ => throw new NoSuchElementException
          }
        }

        val fstElem = valNexter3000(this)
        var rmnElem = pop()._2

        val sndElem = valNexter3000(rmnElem)
        rmnElem = rmnElem.pop()._2

        val rslt = y.eval(fstElem, sndElem)
        rmnElem.push(rslt)
      }
    }

    /**
      * Pushes val's on the stack.
      *
      * If op is not a val, pop two numbers from the stack and apply the operation.
      *
      * @param op
      * @return
      */
    def push(op: Seq[Op]): Try[RpnCalculator] = op.foldLeft(Try(RpnCalculator()))((acc, elem) => acc.get.push(elem))


    /**
      * Returns an tuple of Op and a RevPolCal instance with the remainder of the stack.
      *
      * @return
      */
    def pop(): (Op, RpnCalculator) = (stack.head, RpnCalculator(stack.tail))

    /**
      * If stack is nonempty, returns the top of the stack. If it is empty, this function throws a NoSuchElementException.
      *
      * @return
      */
    def peek(): Op = if (!stack.isEmpty) stack.head else throw new NoSuchElementException

    /**
      * returns the size of the stack.
      *
      * @return
      */
    def size: Int = stack.length
  }
