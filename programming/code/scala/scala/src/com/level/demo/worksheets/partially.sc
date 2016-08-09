package com.level.demo.worksheets

object partially {

  def sum(a: Int, b: Int, c: Int) = a + b + c     //> sum: (a: Int, b: Int, c: Int)Int

  val sum2: (Int, Int, Int) => Int = _ + _ + _    //> sum2  : (Int, Int, Int) => Int = <function3>

  val sum3 = sum _                                //> sum3  : (Int, Int, Int) => Int = <function3>
  sum3(1, 2, 3)                                   //> res0: Int = 6
  sum3.apply(1, 2, 3)                             //> res1: Int = 6

  // 这样好像是不行的，貌似不能对Function的实例使用
  // val sum4 = sum2 _

  val sum5 = sum(1, _: Int, _: Int)               //> sum5  : (Int, Int) => Int = <function2>
  sum5(2, 3)                                      //> res2: Int = 6
  sum5.apply(2, 3)                                //> res3: Int = 6
  
  val sum6: (Int, Int, Int) => Int = sum          //> sum6  : (Int, Int, Int) => Int = <function3>
}