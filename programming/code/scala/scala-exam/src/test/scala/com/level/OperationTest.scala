package com.level

import org.scalatest.Matchers
import org.scalatest.FlatSpec
import org.junit.Test

/**
 * @author huangshi
 */
class OperationTest extends FlatSpec with Matchers {

  @Test def testOperation() {

    // 操作符就是方法
    // TODO: 确认一下下面关于格式的说法是否正确
    // 1 + 2 就是 (1).+(2), 貌似只有在方法的具体调用者已经指明的情况下才能这么写，
    // 参数的括号要省略的话好像必须是只有一个参数或者0个参数吧.
    (1 + 2) should equal((1).+(2))

    // 按操作符风格使用方法，所有方法都可以这么使用
    // 比如 String.index
    ("cool" indexOf "c") should be(0)
    // 但是如果带多个参数的话，后面的括号是不能省略的
    ("cool" indexOf ("o", 2)) should be(2)

    // 

    /*
     * prefix infix, postfix
     */

    /*
     * 
     */

    /*
     * 那些方法属于操作符：
     */

  }

  /**
   *  只有+, -, ~, !三个可以当做前置操作符
   *
   *  对前置操作符的调用，会自动转换成对unary_<symbol>的调用
   */
  @Test def testPrefixOperations() {

    -2 should equal(2.unary_-)
    +2 should equal(2.unary_+)
    ~2 should equal(2.unary_~)

    !true should equal(true.unary_!)

    // 其他的以unary开头的方法是不能当做前置操作符调用的

    class Sample {
      def unary_*(): Sample = new Sample
    }

    // *(new Sample) // 编译错误，因为不支持*当做前缀操作符 
  }

  /**
   * 后置操作符就是没有参数的方法
   */
  @Test def testPostfixOperation() {

  }

  @Test def testEqualOperation() {

    // 这个定义是错误的，仅仅为了演示
    class Sample {
      override def equals(obj: Any): Boolean = true
    }

    // null == null 一直返回true
    // null == Any 或者 Any == null 一直返回false (是这样吗？)
    (null == null) should be(true)
    (null == new Sample) should be(false)
    (new Sample == null) should be (false)
    
    
    // 当 == 左边不为空的时候，会调用左边参数的equals方法. 
    // 所以下面返回的结果是不一样的。
    (new Sample == new Object) should be (true)
    (new Object == new Sample) should be (false)
    
    // eq, ne 对应的是引用比较
    val sample1 = new Sample
    val sample2 = new Sample
    
    (sample1 eq sample1) should be (true)
    (sample1 eq sample2) should be (false)
    
    (sample1 ne sample2) should be (true)
    
    

  }

  /**
   *  方法的第一字母来决定
   *  
   *  * / %
   *  + -
   *  :
   *  = ! 
   *  <>
   *  &
   *  ^
   *  | 
   *  all letters
   *  all assignment operators (以=号结束的，但不属于 >= <= == =的，都和 =属于相同的优先级)
   */
  @Test def testPrecedence() {
    
    
    
  }
  
}

class AllOpts(num: Int) {

}

object AllOpts {

  def apply(num: Int) = new AllOpts(num)
}