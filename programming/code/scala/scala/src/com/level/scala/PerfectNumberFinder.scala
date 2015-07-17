package com.level.scala

import scala.actors.Actor._

/**
 * @author huangshi
 */
object PerfectNumberFinder {

  def sumOfFactors(number: Int): Int = {
    (0 /: (1 to number)) { (sum, i) => if (number % i == 0) sum + i else sum }
  }

  def isPerfect(cadinate: Int) = 2 * cadinate == sumOfFactors(cadinate)

  def isPerfectC(cadinate: Int): Boolean = {
    val RANGE = 10000
    val numberOfPartitions = (cadinate / RANGE).ceil.toInt
    val caller = self

    for (i <- 0 until numberOfPartitions) {
      val lower = i * RANGE + 1
      val upper = cadinate min (i + 1) * RANGE
      actor {
        caller ! sumOfFactorsInRange(cadinate, lower, upper)
      }
    }
    val sum = (0 /: (0 until numberOfPartitions)) { (partialSum, _) =>
      receive {
        case sumOfRange: Int => partialSum + sumOfRange
      }
    }

    2 * cadinate == sum
  }

  def sumOfFactorsInRange(number: Int, lower: Int, upper: Int): Int = {
    (0 /: (lower to upper)) { (sum, i) => if (number % i == 0) sum + i else sum }
  }

}



