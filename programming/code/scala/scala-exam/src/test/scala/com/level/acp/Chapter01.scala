package com.level.acp

import org.scalatest.Matchers
import org.scalatest.FlatSpec
import org.junit.Test

/**
 * @author huangshi
 */
class Chapter01 extends FlatSpec with Matchers {

  @Test def testGcd1() {

    def gcd(m: Int, n: Int): Int = {
      val r = m % n
      if (r == 0)
        n
      else
        gcd(n, r)
    }

    gcd(119, 544) should be (17)
  }

  @Test def testGcd2() {

    def gcd(m: Int, n: Int): Int =
      if (n == 0)
        m
      else gcd(n, m % n)

    gcd(119, 544) should be (17)
  }

}