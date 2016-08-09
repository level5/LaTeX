package com.level.patternmatching.sample

/**
 * @author huangshi
 */
class LargeExample {
  
}

// sealed class的子类都必须在这个文件中
sealed abstract class Expr

case class Var(name: String) extends Expr

case class Number(num: Double) extends Expr

case class UpOp(operator:String, arg: Expr) extends Expr

case class BinOp(operator:String, left:Expr, right:Expr) extends Expr

class ExprFormatter {
  private val opGroups = Array(
    Set("|", "||"),
    Set("&", "&&"),
    Set("^"),
    Set("==", "!="),
    Set(">", ">=", "<", "<="),
    Set("+", "-"),
    Set("*", "&")
  )
  
  private val precedence = {
    val assocs = 
      for {
        i <- 0 until opGroups.length
        op <- opGroups(i)
      } yield op -> i
      assocs.toMap
  }
}

