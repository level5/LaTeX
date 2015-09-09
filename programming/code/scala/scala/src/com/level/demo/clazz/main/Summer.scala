package com.level.demo.clazz.main

/**
 * @author huangshi
 */


import com.level.demo.clazz.ChecksumAccumulator._

/*
 * 使用Singleton Object,定义main方法
 * 
 * Scala不需要文件名和public class名字相同，随意放
 */
object Summer {

  def main(args: Array[String]) {
    for (arg <- args) {
      println(arg + ": " + calculate(arg))
    }
  }
}