package com.level.collections

import org.scalatest.Matchers
import org.scalatest.FlatSpec
import org.junit.Test

/**
 * @author huangshi
 */
class SetTest  extends FlatSpec with Matchers {
  
  
  @Test def testSet() {
    
    val set = Set("tool", "cool")
    
    set contains "tool" should be (true)
    set contains "g" should be (false)
    
    // + 操作符生成一个新的set
    (set + "g") contains "g" should be (true)
    
    
    
    
  }
}