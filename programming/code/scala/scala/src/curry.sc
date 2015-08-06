object curry {
  
  def add(x:Int)(y:Int) = x + y                   //> add: (x: Int)(y: Int)Int
  
  add(1)(2)                                       //> res0: Int = 3
  

	def add2(x:Int) = (y:Int) => x + y        //> add2: (x: Int)Int => Int
	
	add2(1)                                   //> res1: Int => Int = <function1>
	
	add2(1)(2)                                //> res2: Int = 3
	
}