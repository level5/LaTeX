package com.level.test.junit.clazz

import org.junit.Test
import org.junit.Assert._
import com.level.demo.clazz.ChecksumAccumulator
import org.junit.internal.runners.statements.ExpectException

/**
 * @author huangshi
 */
class ChecksumAccumulatorTest {
  
  /*
   * 
   * val和var的区别
   */
  @Test def testval() {
    val acc = new ChecksumAccumulator
    
    // 这样会有编译错误, 因为val类似于java中的final
    // acc = new ChecksumAccumulator
    assertEquals(0, acc.sum0)
    acc.sum0 = 100
    assertEquals(100, acc.sum0)
  }
  
  @Test def testvar() {
    var acc = new ChecksumAccumulator
    // var 就类似于java中的变量，函数式风格尽量使用val
    acc = new ChecksumAccumulator
  }
  
  // 结果转换为Unit的后，值会丢失
  @Test def testConvertReturnValueToUnit() {
  }
  
  @Test def testCompanionObject() {
    //调用方式类似于Java中调用static方法
    ChecksumAccumulator.calculate("Every value is an object.")
  }
  
}