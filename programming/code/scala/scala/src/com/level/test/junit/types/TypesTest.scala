package com.level.test.junit.types

import org.junit.Test

/**
 * @author huangshi
 */

import org.junit.Assert._

class TypesTest {
  
  @Test def testIntegerLiterals() {
    
    // 十六进制表示法
    val hex = 0X00FF
    
    assertEquals(255, hex)
    
    // 表示八进制已经被废弃
    
  }
}