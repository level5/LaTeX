package com.level.demo.worksheets

object hello {
  
  // type inference 类型推导，自动推导类型为String
  val hello = "Hello world!"                      //> hello  : String = Hello world!
  // 显示指定也是可以的
  val hello2 : String = "Hello world!"            //> hello2  : String = Hello world!
  
  // var val, var是可变的，最好定义val
  var hello3 = "hello"                            //> hello3  : String = hello
  
  hello3 = "new String"
  println(hello3)                                 //> new String
  
  
  // 定义函数
  def max(x : Int, y : Int): Int = {
  	// 这个括号是必需的，不像ruby一样可以省略
  	if( x > y) x else y
  }                                               //> max: (x: Int, y: Int)Int
  
  // 只有一个语句，花括号可以省略，结果可以使用type inference
  def max2(x: Int, y: Int) = if (x > y) x else y  //> max2: (x: Int, y: Int)Int
  
  
 	// 空括号表示没有参数， 返回结果是Unit表示对返回结果不感兴趣，java中的返回void的方法都是返回Unit
 	// 返回结果是Unit，表明调用这个方法，只是为了这个方法产生的side effects.不是函数编程的风格
  def greet() = println("hello")                  //> greet: ()Unit
  
  // 另外一个格式表示返回Unit：移除返回类型和等号，使用花括号将方法体括起来
  def greet0() {
 	 println("hell0")
  }                                               //> greet0: ()Unit
  
  //这种情况下，即使又返回值，也会被抛弃
  def greet1() {
  	"hello" // 最后一个语句的值，就是整个函数的返回值
  }                                               //> greet1: ()Unit
  
  // 因为任何类型都能够转换为Unit类型
  def greet2():Unit = "hello"                     //> greet2: ()Unit
  
  val array = Array(1, 2, 3)                      //> array  : Array[Int] = Array(1, 2, 3)
  // 函数字面量
  // (x: Int, y: Int) => x + y
  array.foreach(arg=>println(arg))                //> 1
                                                  //| 2
                                                  //| 3
  // 这里的v是val，而不是var。这里因为是val，所以不需要写，直接写v就可以了
  for (v <- array)
  	println(v)                                //> 1
                                                  //| 2
                                                  //| 3
      
}