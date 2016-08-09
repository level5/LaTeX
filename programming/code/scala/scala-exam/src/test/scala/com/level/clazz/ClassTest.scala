package com.level.clazz

import org.scalatest.Matchers
import org.scalatest.FlatSpec
import org.junit.Test

import com.level.clazz.ImplictConvert._

/**
 * @author huangshi
 */
class ClassTest extends FlatSpec with Matchers  {
  
  @Test def testClass () {
    class Check {
      // 定义class的属性(instance variables)和方法
      
      // scala的默认访问级别是public
      var sum = 0
      
      def add(n: Int) {
        // n = 5 // 编译错误，因为n是val，方法的参数默认都是val
        sum += n
      }      
      // 方法只有一个参数的时候，大括号是可以省略的,这里显示指定返回类型为Unit。
      // 表明方法有副作用,类似于Java的void返回值
      def add2 (n: Int): Unit = sum += n
            
      // 私有
      private var sum2 = 0
      
      // 重载
      override def toString: String = "sum is: " + sum
    }
    
    // 伴生对象，和class有相同的名字，必须和对应的class定义在相同的源文件中
    
    /*
     *  伴生对象不能带参数，因为没有办法new一个半生对象。
     *  
     *  伴生对象可以extends class，或者mixin trait
     *  
     *  带main方法的singleton object可以作为应用的入口 
     */
    
    object Check {
      
    }
    val checker = new Check
    
    checker.sum should equal (0)
    
    checker.toString should equal ("sum is: 0")
  }
  
  @Test def testRational() {
    
    
    // toString
    val one = new Rational
    val half = new Rational(4, 8)
    (one toString) should equal ("1/1")
    (half toString) should equal ("1/2")

    // 运算符
    Rational(1) + Rational(1) should equal (Rational(2))
    Rational(1, 2) * Rational(1, 2) should equal (Rational(1, 4))
    
    // 通过 import com.level.clazz.ImplictConvert._来导入隐式转换
    // 或者通过with trait的方式导入进来，类似于Matchers中将所有对象隐式转换成可以调用should
    1 * Rational(1, 2) should equal (Rational(1, 2))
    Rational(1, 2) * 4 should equal (Rational(2))
    
    // 这里没有重载+方法也可以达到同样的效果    
    Rational(1, 2) + 1 should equal (Rational(3, 2))
  }
}