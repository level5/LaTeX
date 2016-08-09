package com.level.function

import org.scalatest.FlatSpec
import org.scalatest.Matchers
import org.junit.Test

/**
 *  所有的内建的控制都是表达式，也就是会返回值
 */
class BuiltInControlStructuresTest extends FlatSpec with Matchers {

  /**
   * if 就类似于java中的三元表达式， scala中没有三元表达式
   */
  @Test def testIf() {
    val x = 10
    val y = 20
    val max = if (x > y) x else y

    max should be(20)
  }

  /**
   * while 不是函数式的，因为他返回的是一个没有意义的值Unit
   */
  @Test def testWhile() {

    var a = 0;
    val b = while (a < 10) {
      a += 1
    }
    b.isInstanceOf[Unit] should be(true)

    val c = do {
      a -= 1
    } while (a > 0)
    c.isInstanceOf[Unit] should be(true)
  }

  @Test def testAssign() {
    val a = () // () 返回的就是Unit
    a.isInstanceOf[Unit] should be(true)

    // 关键在这里，赋值操作的返回值也是Unit
    var foo = ""
    val b = (foo = "next")
    b should be(())
  }

  @Test def tesFor() {

    // for 返回的类型由<-后的类型决定
    val result = for (i <- List(1, 2, 3, 4)) yield i
    result.isInstanceOf[List[Int]] should be(true)
    val result2 = for (i <- Array(1, 2, 3, 4)) yield i
    result2.isInstanceOf[Array[Int]] should be(true)

    // 1 to 4 是1，2，3，4
    val numbers = for (i <- 1 to 4) yield i
    numbers should equal(List(1, 2, 3, 4))

    // 1 until 4是 1，2，3. 不包括4 
    val numbers2 = for (i <- 1 until 4) yield i
    numbers2 should equal(List(1, 2, 3))

    // 过滤，一个if从句
    val numbers3 = for (i <- 1 to 4 if i % 2 == 0) yield i
    numbers3 should equal(List(2, 4))

    // 更多的if过滤从句
    val numbers4 = for (i <- 1 to 100 if i < 10; if i % 2 == 1) yield i
    //                                          ^ 多条if必须用分号隔开
    numbers4 should equal(List(1, 3, 5, 7, 9))

    // 嵌套迭代
    val tuples = for {
      i <- 1 to 4
      if i % 2 == 0
      j <- 1 to 4
      if j % 2 == 1
    } yield (i, j)

    tuples should equal(List((2, 1), (2, 3), (4, 1), (4, 3)))
  }

  /**
   * 自己傻叉了，没有把这里写清楚呀。
   */
  @Test def testFinally() {

    def f(): Int = try { return 1 } finally { return 2 }

    // 嘿嘿，这里会被finally的分支替换呀
    f should equal(2)

    // 这才是scala风格的吗？　finally 的值
    def g(): Int = try { 1 } finally { 2 }
    
    g should equal (1)
  }
  
  @Test def testMatch() {
    
    def rn(number: Any) = number match {
      case 1 => "I"
      case 2 => "II"
      case 3 => "III"
      case _ => "..."
    }
    
    rn(1) should equal ("I")
    rn(2) should equal ("II")
    
    rn("k") should equal ("...")
  }
  
  /**
   * 
   * java风格的查找第一个满足条件的：
   * int i = 0;
   * boolean found = false;
   * while (i < args.length) {
   *    if (args[i].startWith("-") {
   *      i++;
   *      continue;
   *    }
   *    if (args[i].endWith(".scala") {
   *      found = true;
   *      break;
   *    }
   *    i++;
   * }
   * 
   */
  @Test def testNoBreakAndContinue() {
    
    val args = List("-a.scala", "", "-aa", "aa", "aa.scala", "aa.scala", "c.scala", "bc")
    
    def find(args:List[String]): Int = args.indexWhere { x => x.endsWith(".scala") && !x.startsWith("-") }
    
    find(args) should be (4)
  }

}