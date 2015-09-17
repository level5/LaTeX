package com.level.demo.regexp

object syntax {


	//


	// (?:) 不对分组进行capture
  
  // *, ?, +, {num, num}
  
  // *?, ??, +?, {num, num}?
  
  // *+, ?+, ++, {num, num}+
  
  // (?>...)

	// lookahead  (?=...)		(?!...) 匹配一个位置，这个位置之后和括号里面的regex(不)匹配
	
	// lookbehind	(?<=...) 	(?<!..) 匹配一个位置，这个位置之前和括号里面的regex(不)匹配
	
	// atomic grouping (?>...)  就是这个group匹配了的字符不会再吐出来。当然，如果回溯到这个group之前的一个状态，整个group还是会完整的吐出来的。
	
	// greedy
	
	//
	
	//
	
	// 同时支持 lookahead &　capture group
	
	"""(?>regex)""".r                         //> res0: scala.util.matching.Regex = (?>regex)
	
	// 上面和下面等价
	"""(?=(regex))\1""".r                     //> res1: scala.util.matching.Regex = (?=(regex))\1
	
	
	// 匹配最多的text是greedy，匹配最少的text是Lazy，但是下面这个...
	"(?x)tour | to | tournament".r findFirstIn "three tournaments won" getOrElse "Not Found."
                                                  //> res2: String = tour
	
	// 在alternation中，这个叫做ordered
	
	// 这样，就给了作者更多的控制权，可以让regexp先试试这个，再试试那个，最后试试这个。
	
	//对于DFAs或者POSIX NFAs，会匹配最长的text
	
	// 但是对于NFAs来说，是按顺序匹配
	// 对比一下, 会发现，第二个例子，这里不会做greedy匹配
	var opt = for (m <- """(\.\d\d[1-9]?)\d*""".r findFirstMatchIn "3.333333" ) yield m group 1
                                                  //> opt  : Option[String] = Some(.333)
	opt getOrElse "Not Found"                 //> res3: String = .333
	
	opt = for (m <- """(\.\d\d|\.\d\d[1-9])\d*""".r findFirstMatchIn "3.333333" ) yield m group 1
	opt getOrElse "Not Found"                 //> res4: String = .33
	
	
	println("test")                           //> test
}