package com.level.demo.clazz

/**
 * @author huangshi
 */
class ChecksumAccumulator {

  /*
   * 直接在花括号中定义的变量或者方法是class的成员
   * 
   * 默认的访问级别是public的
   * 
   * 可以使用private修饰变量，来让成员变成私有
   */
  var sum0 = 0 // 这个变量将不会使用，仅仅为了当做例子使用
  
  private var sum = 0 
  
  def add(b: Byte):Unit = {
    // 不能编译，因为b是val
    // b = 1
    sum += b
  }
  
  
  def checkSum(): Int = {
    // 默认会把最后一条语句的值当做返回值，不推荐使用return这种写法，虽然也是对的
    // return ~(sum + 0XFF) + 1
    ~(sum + 0XFF)  + 1
  } 
  
  // 只有一条语句，花括号可以省略
  def checkSum0(): Int = ~(sum + 0XFF) + 1
  def add0(b: Byte): Unit = sum += b
  
  // 返回结果Unit表示只关注方法的副作用。
  // 可以采用下面这种格式：移除返回类型和等号，使用花括号将方法体括起来
  def add1(b: Byte) {
    sum += b
  }
  
}

/*
 * Singleton Object， 使用关键字object替换掉class 
 * 
 * 如果singleton object和class同名的话，这个singleton
 * 就是class的伴生对象。他们必须定义在一个原文件中
 * 
 * 伴生对象和伴生类能够相互访问对方的private成员
 * 
 */
import scala.collection.mutable.Map

object ChecksumAccumulator {
  private val cache = Map[String, Int]()
  
  def calculate(s: String): Int = {
    if (cache.contains(s))
      cache(s)
    else {
      val acc = new ChecksumAccumulator
      for (c <- s) 
        acc.add(c.toByte)
      val cs = acc.checkSum()
      cache += (s -> cs)
      cs
    }
      
  }
}
