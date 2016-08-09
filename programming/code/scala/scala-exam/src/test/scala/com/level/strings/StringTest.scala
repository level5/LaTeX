package com.level.strings

import org.junit.Test
import org.scalatest.FlatSpec
import org.scalatest.Matchers
import scala.collection.immutable.StringLike

/**
 * @author huangshi
 */
class StringTest extends FlatSpec with Matchers{
  
  
  @Test def testString() {
    
    val str: String = "hello"

    // """包含的字符串不转义
    val str2 = """a
b"""
    str2 should equal ("a\nb")

    // 没有顶格的话，前面的空格也会包含在字符串里面
    val str3 = """a
      b"""
    str3 should not equal ("a\nb")
    
    
    // 如果想保持空格的话,但不想放入字符串中
    """|a
       |b
       |c""".stripMargin should equal ("a\nb\nc")
    
  }
  
  @Test def testStringOps() {
    
        
    //  TODO: 妈蛋，下次自己注释写详细点吧，啥事StringLike啊！！！ 
    // 目前我还不知道StringLike和String怎么混合到一起的
    "hello" * 2 should equal ("hellohello")

  }
  
}