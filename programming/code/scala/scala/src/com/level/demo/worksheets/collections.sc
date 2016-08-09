package com.level.demo.worksheets

import java.util.NoSuchElementException

object collections {

	val map = Map( 1 -> "One", 2 -> "Two")    //> map  : scala.collection.immutable.Map[Int,String] = Map(1 -> One, 2 -> Two)
                                                  //| 
	
	// apply方法返回的是String
	map(1)                                    //> res0: String = One
	
	// get方法返回的是Option[String]
	map get 1                                 //> res1: Option[String] = Some(One)
	
	// 直接抛异常了
	val three = try {
		map(3)
	} catch {
		case e: NoSuchElementException => "Not found"
	}                                         //> three  : String = Not found
                                                  
   map get 3                                      //> res2: Option[String] = None

}