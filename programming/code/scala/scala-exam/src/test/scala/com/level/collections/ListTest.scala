package com.level.collections

import org.junit.Test
import org.scalatest.FlatSpec
import org.scalatest.Matchers
import scala.collection.mutable.ListBuffer


/**
 * @author huangshi
 */
class ListTest extends FlatSpec with Matchers {

  @Test def testList() {

    // 默认的List是不可变的,不能改变大小或者引用的值
    // 伴生对象上的工厂方法
    val oneTwoThree = List(1, 2, 3)

    val fourFiveSix = List(4, 5, 6)

    // 这个括号不是必须的，只是为了添加一点点可读性
    // TODO: 补充方法结合的优先级别

    /*
     *  这里导入的另外一个知识点，以：结束的操作符，实际上是调用的后面的这个对象的方法。
     *  
     *  ::: 的意思是将List的元素添加到List前面，所以是把oneTwoThree加到fourFiveSix前
     */

    (oneTwoThree ::: fourFiveSix) should equal(List(1, 2, 3, 4, 5, 6))

  }

  @Test def testGenList() {

    // Nil就是一个空的List
    (Nil.isInstanceOf[List[_]]) should be(true)
    Nil should equal(List())

    // 将元素添加到List前面。最后一个必须是Nil
    1 :: 2 :: 3 :: Nil should equal(List(1, 2, 3))
  }

  @Test def testOpsOnList() {

    var list: List[String] = Nil // 一个空的List，不可变对象
    list should equal(List())

    /*
     * :结束的操作符，实际调用的是右边的对象的方法
     * ^       ^
     */
    // ::: 将两个list拼接到一起
    list = List("tool", "rule") ::: list
    list should equal(List("tool", "rule"))

    // :: 将元素拼接到List的前面
    list = "cool" :: list
    list should equal(List("cool", "tool", "rule"))
    list should equal("cool" :: "tool" :: "rule" :: Nil)
    //                                                ^ 这里必须是Nil

    // 取得list的第二个元素，实际调用的是list.apply方法
    list(2) should equal("rule")
    list(2) should equal(list.apply(2))

    // 编译错误，因为是不可变的
    // list(2) = "cast"

    // 统计数量
    list.count { _.length == 4 } should be(3)
    list.count { _.endsWith("ool") } should be(2)

    // 返回删除前面n个元素的List
    list.drop(2) should equal (List("rule"))
    list.drop(list.size) should equal(Nil)

    // 和上面方法删除的方向相反
    list.dropRight(2) should equal(List("cool"))
    list.dropRight(list size) should equal(Nil)

    list.dropWhile { _.length == 4 } should equal(Nil)

    // 删除最前面连续满足条件的元素
    // 这里前两个都满足条件，所以前两个都删掉了。
    list.dropWhile { _.endsWith("ool") } should equal(List("rule"))
    // 因为第一个就不满足条件，所以第二个满足也没用，不会删除
    list.dropWhile { _.startsWith("t") } should equal(List("cool", "tool", "rule"))

    // 而想删除匹配的元素，不管在什么位置。使用filterNot
    list.filterNot { _.startsWith("t") } should equal(List("cool", "rule"))

    // 判断List中是否有满足条件的元素
    list.exists { _.startsWith("r") } should be(true)
    list.exists { _.startsWith("a") } should be(false)

    // 过滤出所有满足条件的元素
    list.filter { _.endsWith("ool") } should equal(List("cool", "tool"))

    // 所有元素满足条件时返回true
    list.forall { _.length == 4 } should be(true)
    list.forall { _ endsWith "ool" } should be(false)

    // 对每个元素执行一次条件
    list.foreach { _.length should equal(4) }

    
    
    // 第一个元素
    list.head should equal("cool")
    // 删除了第一个元素的List
    list.tail should equal(List("tool", "rule"))

    // 最后一个元素
    list.last should equal("rule")
    // 删除最后一个元素的List
    list.init should equal(List("cool", "tool"))

    (list.isEmpty) should be(false)

    list.length should be(3)

    list.map { _.toUpperCase() } should equal(List("COOL", "TOOL", "RULE"))

    // 拼接
    list.mkString(", ") should equal("cool, tool, rule")

    // 反转
    list.reverse should equal(List("rule", "tool", "cool"))

    // 排序
    list.sortWith { _ > _ } should equal(List("tool", "rule", "cool"))
  }

  @Test def testFold() {

    val numbers: List[Int] = List(1, 2, 3, 4, 5, 6, 7, 8, 9, 10)

    // 左折叠
    (0 /: numbers)(_ + _) should be(55)

    // 右折叠
    (numbers :\ 0)(_ + _) should be(55)

  }
  
  @Test def testNil() {
    (Nil == List()) should be (true)
    (Nil eq List()) should be (true)
  }

  /**
   *  插入排序
   */
  @Test def testSortWithPatternmatching() {

    def insertSort(input: List[Int]): List[Int] = input match {
      case Nil          => Nil
      case head :: tail => insert(head, insertSort(tail))
    }

    def insert(value: Int, input: List[Int]): List[Int] = input match {
      case Nil          => value :: Nil
      case head :: tail => if (value <= head) value :: input else head :: insert(value, tail)
    }
    
    // test
  }
  
  /**
   * 变量的Pattern Match
   */
  @Test def testNils() {
    
    val fruits = List("Orange", "Apple", "Banana")
    val List(a, b, c) = fruits
    
    a should equal ("Orange")
    b should equal ("Apple")
    c should equal ("Banana")
    
    
    val a1 :: b1 :: c1 :: Nil = fruits
    a1 should equal ("Orange")
    b1 should equal ("Apple")
    c1 should equal ("Banana")
    
    val a2 :: b2 :: c2  = fruits
    a2 should equal ("Orange")
    b2 should equal ("Apple")
    c2 should equal (List("Banana"))
    
  }
  
  
  @Test def testConcatenateLists() {
    
    List(1, 2) ::: List(3, 4, 5) should equal (List(1, 2, 3, 4, 5))
  }
  
  
  /**
   * 手动实现  ::: 方法
   */
  @Test def testAppend() {
    
    // 因为是从后往前并且到ys上，所以匹配xs是更好的选择
    def append[T](xs:List[T], ys: List[T]): List[T] = xs match {
      case Nil => ys
      case x :: xs1 => x :: append(xs1, ys)
    }
    
    append(List(1, 2, 3), List(4, 5)) should equal (List(1, 2, 3, 4, 5))
    
  }

  /**
   * 手动实现 length方法
   */
  @Test def testLength() {
    
    def length[T](xs: List[T]): Int = xs match {
      case Nil => 0
      case x :: xs1 => 1 + length(xs1)
    }
  }
  
  
  @Test def testReserve() {
    
    def reserve[T](list: List[T]) :List[T] = list match {
      case Nil => list
      case x::xs => reserve(xs) ::: List(x)
    }
    
  }
  
  @Test def testApply() {
    
    "abcde"(2) should equal ('c')
    
    val fruits = List("Orange", "Apple", "Banana")
    fruits(2) should equal (fruits.drop(2).head)
    
  }
  
  @Test def testFlatten() {
    
    val l = List(List(1, 2), List(3, 4, 5) , List(), List(6)).flatten
    l should equal (List(1, 2, 3, 4, 5, 6))
  }
  
  @Test def testZip() {
    
    val fruits = List("Orange", "Apple", "Banana")
    val zipped = fruits.indices zip fruits 
    val unzipped = zipped.unzip
    
    zipped should equal (List((0, "Orange"), (1, "Apple"), (2, "Banana")))
    unzipped should equal ((List(0, 1, 2), List("Orange", "Apple", "Banana")))
    
  }
  
  @Test def testToString() {
    
    val fruits = List("Orange", "Apple", "Banana")
    fruits.toString should equal ("List(Orange, Apple, Banana)")
    
    fruits.mkString(", ") should equal ("Orange, Apple, Banana")
    fruits.mkString("[", ", ", "]") should equal ("[Orange, Apple, Banana]")
    
    fruits.mkString should equal (fruits.mkString(""))
    
  }
  
  /**
   * 对list的第一个元素进行操作的速度会比较快。比如取得第一个元素
   * 
   * 添加元素到头部.
   * 
   * 如果想往尾部添加，可以使用 ListBuffer
   * 
   */
  @Test def testBuffer() {
    val buff = new ListBuffer[Int]
    buff += 1
    buff += 2
    buff should equal (List(1, 2))
  }

}