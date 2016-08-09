package com.level.patternmatching

import org.junit.Test
import org.scalatest.Matchers
import org.scalatest.FlatSpec

class CaseTest extends FlatSpec with Matchers {

  abstract class Expr

  case class Var(name: String) extends Expr
  case class Num(num: Double) extends Expr
  case class UnOp(operator: String, arg: Expr) extends Expr
  case class BinOp(operator: String, left: Expr, right: Expr) extends Expr

  @Test def testCase() {

    /*
     * case修饰的class，scala编译器会做一些特殊的处理：
     * 
     */

    // 1. 会自动添加工厂方法
    val v = Var("x")

    // 2. 参数列表中的参数自动添加了val前缀就好像是这样写的：class Var(val name: String)
    v.name should equal("x")

    // 3. 根据参数实现toString, hashcode, equals方法

    // 4. 生成copy方法，用来生成一个拷贝，可以修改其中对的部分属性
    val v2 = v.copy(name = "y")
    v2.name should equal("y")

  }

  @Test def testMatch() {
    def simplifyTop(expr: Expr): Expr = expr match {
      case UnOp("-", UnOp("-", e)) => e
      case BinOp("+", e, Num(0)) => e
      case BinOp("*", e, Num(1)) => e
      case _ => expr
    }
    
    simplifyTop(UnOp("-", UnOp("-", Var("x")))) should equal (Var("x"))
    
  }
  
  /**
   * 泛型运行时已经被檫除，没有办法在运行时知道Map中的类型
   */
  @Test def testIntIntMap() {
     
    def isIntIntMap(x: Any): Boolean = x match {
      case m: Map[Int, Int] => true
      case _ => false
    }
    
    /*
     * 类型都被檫除了，所以都返回true
     */
    isIntIntMap(Map(1->1)) should be (true)
    isIntIntMap(Map("abc" -> "abc")) should be (true)
    
    
  }
  
  /**
   * Array[] 是个例外
   */
  @Test def testIntArray() {
    def isIntArray(x:Any) = x match {
      case a : Array[Int] => true
      case _ => false
    }
    
    isIntArray(Array(1)) should be (true)
    isIntArray(Array("abc")) should be (false)
  }
  
  
  @Test def testOption() {
    // Map的get方法返回的Option
    val map = Map(1 -> "One", 2 -> "Two", 3 -> "Three")
  }
  
  /**
   * Pattern的各种使用方法:
   * 1. 变量中
   * 2. case class的construct
   * 3. for语句中
   */
  @Test def testPatterns() {
    
    // 变量定义
    val myTuple = (1, "one")
    val (number, string) = myTuple
    
    number should equal (1)
    string should equal ("one")
    
    // construct中和case class一起使用
    val BinOp(op, left, right) = BinOp("*", Num(5), Num(1))
    
    op should equal ("*")
    left should equal (Num(5))
    right should equal (Num(1))
    
    // for语句中
    val map = Map(1 -> "One", 2 -> "Two")
    val newMap = for ((num, str) <- map) yield (num * 2, str * 2)
    newMap should equal (Map(2 -> "OneOne", 4 -> "TwoTwo"))
    
    // 这里不匹配会被过滤掉
    val fruits = List(Some("Orange"), None, Some("Apple"))
    (for (Some(fruit) <- fruits) yield fruit) should equal (List("Orange", "Apple"))
  }
  

}