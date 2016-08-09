package com.level.collections

import org.scalatest.Matchers
import org.scalatest.FlatSpec
import org.junit.Test

/**
 * @author huangshi
 */
class ArrayTest extends FlatSpec with Matchers {
  
  @Test def testArray() {
    
	  // 这里实际调用的是伴生对象的apply方法
    val arr = Array(1, 2, 3)
    arr should equal (Array.apply(1, 2, 3))
    
    // 数组的访问时通过()，而不是通过[],因为这里的()实际上又是调用的Array的apply方法
    arr(0) should be (1)
    
    // 两者是有区别的，一个是在Array上的，一个是在Array的伴生对象上的，想当如Java的静态方法和实例方法
    
    // Array的长度是不变的，但是每个元素的引用是可变的 
    // 这个格式，一个变量，带着括号和参数，再加上一个赋值操作，那么就会被转变为update方法
    arr(0) = 10
    arr(0) should be (10)
    
    // 等价于这种情况
    arr.update(0, 20)
    arr(0) should be (20)
    
    // 这个时候的参数是括号中的参数加上赋值操作后面的参数
    // a(1, 2) = 3的话，实际上的调用就是a.update(1, 2, 3)
    
  }
}