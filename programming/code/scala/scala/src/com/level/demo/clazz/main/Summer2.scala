package com.level.demo.clazz.main

import com.level.demo.clazz.ChecksumAccumulator._

/**
 * @author huangshi
 */


/*
 * 使用Trait，
 * 1. 缺点是不能传入参数
 * 2. 多线程程序，需要显示定义main method(这个没看懂)
 * 3. 一些JVM在这种情况下不会优化代码
 */
object Summer2 extends App {
  
  for(name <- List("HuangShifeng", "HuangShinuo", "FengXiaohui"))
    println(name +": " + calculate(name))
  
}