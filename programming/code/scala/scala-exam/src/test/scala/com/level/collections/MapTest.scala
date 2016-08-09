package com.level.collections

import org.scalatest.Matchers
import org.scalatest.FlatSpec
import org.junit.Test

/**
 * @author huangshi
 */
class MapTest extends FlatSpec with Matchers {

  
  @Test def testMap () {
    
    val map = Map(1 -> "I", 2 -> "II", 3 -> "III")
    
    map(1) should equal ("I")
    
    val newMap = map + (4 -> "IV")
    newMap(4) should equal ("IV")
    
  }
}