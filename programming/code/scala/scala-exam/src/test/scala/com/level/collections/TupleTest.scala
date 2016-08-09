package com.level.collections

import org.scalatest.Matchers
import org.scalatest.FlatSpec
import org.junit.Test

/**
 * @author huangshi
 */
class TupleTest extends FlatSpec with Matchers {

  @Test def testTuple() {
    
    // 定义Tuple的语法，Tuple不需要元素类型相同
    val pair = (1, "One")
    
    // 不能使用apply访问是因为元素的类型不一定一样
    pair._1 should equal (1)
    pair._2 should equal ("One")
    
    // pair的类型就是Tuple2[Int, String]
    val copy: Tuple2[Int, String] = pair
    
    
  }
}