package com.level.syntax

import org.scalatest.Matchers
import org.scalatest.FlatSpec
import org.junit.Test

class VariableTest   extends FlatSpec with Matchers {
  
  /**
   *  pattern match 用在变量赋值
   */
  @Test def testAssign() {
    
    // case class的构造函数匹配
    case class Num(n:Int)
    val Num(a) = new Num(10)
    a should be (10)
    
    // Tuple 
    val (c, d) = ("String", 20)
    c should equal ("String")
    d should equal (20)
    
    // 如果忘记写括号了，结果是不一样的。
    val e, f = ("String", 20)
    e should equal ("String" -> 20)
    f should equal ("String" -> 20)
    
    // TODO: 为什么e和f不是一个对象呢？
    (e eq f) should be (false)
    // 按照解释的意思是，每个变量都执行了一次右边表达式式。所以e和f是两个不同的实例
    
    
  }
}