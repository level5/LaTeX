package com.level.demo.regexp

/**
 * @author huangshi
 */
object Test {
  println("test")

  println("more content...")

  /*
   * 如果使用scala提供的regex对象的话，flags必须嵌套在pattern string中，
   * 因为不提供option来提供flags
   * 
   * 支持的mode
   */

  // i 忽略大小写
  val caseIgnore = """(?i)hello""".r
  caseIgnore findAllIn "Hello" toList

  def run() {

  }
}