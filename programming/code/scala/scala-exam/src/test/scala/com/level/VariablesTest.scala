package com.level

import org.junit.Assert._
import org.junit.Test
import org.scalatest.FlatSpec
import org.scalatest.Matchers

/**
 * @author huangshi
 */
class VariablesTest extends FlatSpec with Matchers {
  
  /**
   *  关于变量
   */
  @Test def testVariable() {
    
    // type inference 类型推导，自动推导类型为String
    val hello = "Hello world!"                     
    // 显示指定也是可以的,上面的和这个效果是一样的
    val hello2 : String = "Hello world!"            
    
    // var val, var是可变的，val是不变的，类似于java的final
    var hello3 = "hello"                         
    hello3 = "new String"
    hello3 should equal ("new String") 
  }
  
  
  @Test def testLiteral() {
    
    // 0x或者0X开头的16进制
    val hex = 0xf 
    hex should equal (15)
    val hex2 = 0Xe
    hex2 should equal (14)
    
    // TODO: 八进制的字面表示
    
  }
  
  @Test def testSymbol() {
    'symbol.name should equal ("symbol")
    
    // TODO: == 和Java中不是一样，不是比较的引用，但是eq和ne只适应从java直接对应过来的对象。
    // 找找该怎么比较
    ('symbol == 'symbol) should be (true)
    ('symbol eq 'symbol) should be (true)
  }
  
}