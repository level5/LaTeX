package com.level.demo.worksheets

object def_val {

  def defEven: Int => Boolean = _ % 2 == 0        //> defEven: => Int => Boolean

  defEven eq defEven                              //> res0: Boolean = false

  val valEven: Int => Boolean = _ % 2 == 0        //> valEven  : Int => Boolean = <function1>

  valEven eq valEven                              //> res1: Boolean = true
	
	
	
	/*
	
		def 和 val的区别
	
	*/
	
	
	// 这样子比较清晰，这个时候已经打印了test. test. test
  val test = {
    val r = util.Random.nextInt
    println("test. test. test")
    () => r
  }                                               //> test. test. test
                                                  //| test  : () => Int = <function0>
                                                  
  
  // 这里还没有打印
  def test3 = {
    val r = util.Random.nextInt
    println("test. test. test")
    () => r
  }                                               //> test3: => () => Int
  
  // 这个时候打印了两次
  test3 eq test3                                  //> test. test. test
                                                  //| test. test. test
                                                  //| res2: Boolean = false
}