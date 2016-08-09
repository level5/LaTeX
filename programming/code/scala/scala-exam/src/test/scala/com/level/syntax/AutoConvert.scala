package com.level.syntax

import org.junit.Test
import org.scalatest.Matchers
import org.scalatest.FlatSpec

class AutoConvert  extends FlatSpec with Matchers {
  
  /**
   * 对class加上()会自动调用伴生对象的apply方法
   * 对object加上()会自动调用object.apply方法
   */
  @Test def testApply() {
    
    List(1, 2) should equal (List.apply(1, 2))
    
    val arr = Array(1, 2)
    arr(1) should equal (arr.apply(1))
  }
  
  /**
   *  object(a) = b 会自动调用 object.update(a, b)
   */
  @Test def testUpdate() {
    
    val arr = Array(1, 2, 3)
    
    arr(2)  = 20
    arr(2) should equal (20)
    
    arr.update(2, 40)
    arr(2) should equal (40)
  }
  
  /**
   * 如果a不支持+=方法
   * a += b 会自动转换为 a = a + b
   */
  @Test def test_+=() {
    class Num(val n: Int) {
      def +(x: Int) = new Num(n + x)
    }
    
    //这里需要定义成var，因为val没办法重新赋值
    val num = new Num(1)
    var copy = num
    
    (copy eq num) should be (true)
    copy += 5
    
     // 这个时候copy已经被替换成另外一个对象了
    (copy eq num) should be (false)
    copy.n should be (6)
  }
}