import com.level.scala.Resource

object helloworld {

	val str = "hello world!"                  //> str  : String = hello world!
   
  def inject(arr: Array[Int], initial: Int, operator: (Int, Int) => Int) : Int =
  {
  	var carryover = initial
  	arr.foreach(element => carryover = operator(carryover, element))
  	carryover
  }                                               //> inject: (arr: Array[Int], initial: Int, operator: (Int, Int) => Int)Int
  
  val numbers = Array(1,2,3,4,5, 12, 44, 0, 8, 9) //> numbers  : Array[Int] = Array(1, 2, 3, 4, 5, 12, 44, 0, 8, 9)
  
  val sum = inject(numbers, 0, (a:Int, b:Int)=> a + b)
                                                  //> sum  : Int = 88
  val max = inject(numbers, Integer.MIN_VALUE, Math.max)
                                                  //> max  : Int = 44
  
	
	// inject 方法类库实际上已经有了实现，就是 foldLeft()， 也就是 /:方法，:结尾的方法，实际上是调用方法名之后的参数的方法
  
  val sum2 = (0 /: numbers){(sum, elem) => sum + elem}
                                                  //> sum2  : Int = 88
  
  val max2 = (Integer.MIN_VALUE /: numbers) { Math.max}
                                                  //> max2  : Int = 44
                                                  
	val sum3 = (0 /: numbers) { _ + _ }       //> sum3  : Int = 88
                                                  

	
	def add(a: Int, b : Int, c: Int): Int = {
		a + b + c
	}                                         //> add: (a: Int, b: Int, c: Int)Int
	
	val addOne = add(1, _:Int, _:Int)         //> addOne  : (Int, Int) => Int = <function2>
	
	addOne(1, 2)                              //> res0: Int = 4
	
	def add2(a: Int)(b : Int)(c: Int): Int = {
		a + b + c
	}                                         //> add2: (a: Int)(b: Int)(c: Int)Int
	

  Resource.use {resource =>
    resource.op1
    resource.op2
    resource.op3
    resource.op2
    resource.op1
  }                                               //> [Resource] start transaction.
                                                  //| [Resource] op1 start ...
                                                  //| [Resource] op2 start ...
                                                  //| [Resource] op3 start ...
                                                  //| [Resource] op2 start ...
                                                  //| [Resource] op1 start ...
                                                  //| [Resource] end transaction.


 def one = 101                                    //> one: => Int
 // 可以不带括号
 val o = one                                      //> o  : Int = 101
 
 
 val printlna = println _                         //> printlna  : () => Unit = <function0>
 val printlnb = println                           //> 
                                                  //| printlnb  : Unit = ()
 
 printlna == printlnb                             //> res1: Boolean = false
 
 println == println                               //> 
                                                  //| 
                                                  //| res2: Boolean = true
 
}