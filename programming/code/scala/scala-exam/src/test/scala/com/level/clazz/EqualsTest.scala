package com.level.clazz

import org.scalatest.FlatSpec
import org.scalatest.Matchers
import org.junit.Test

/**
 * @author huangshi
 */
class EqualsTest extends FlatSpec with Matchers{
  
  /**
   * scala的 == 的定义如下，可以通过改写equals来重写 == 的结果
   * 
   * final def ==（that: Any) = 
   *                if (null eq this) {null eq that} else {this equals that}
   * 
   * 
   * 这里的eq就是相当于java的==
   */
  @Test def test_==() {

    // 左边是null的情况，判断右边是不是null
    (null == null) should be (true)
    (null == new Object) should be (false)
    
    // 左边不为null的情况，直接调用左边的equals方法
    
  }
  
  
}