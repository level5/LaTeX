package com.level.clazz

/**
 * @author huangshi
 */
object ImplictConvert {

  implicit def intToRational(x: Int) = Rational(x)
}