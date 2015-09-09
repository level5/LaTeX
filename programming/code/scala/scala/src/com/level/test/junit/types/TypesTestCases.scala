package com.level.test.junit.types

import org.junit.Test
import org.junit.Assert._
/**
 * @author user
 */
class TypesTestCases {
  
  
  @Test def testStringLiterals() {
    
    // """ 忽略escape character
    assertEquals("\\\"\\n\\r", """\"\n\r""")
  }
}