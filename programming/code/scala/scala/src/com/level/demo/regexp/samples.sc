package com.level.demo.regexp

object samples {
  
  // *, ?, +, {num, num}
  
  // *?, ??, +?, {num, num}?
  
  // *+, ?+, ++, {num, num}+
  
  /*=====================================================================*/
  
  // 例子1�匹配双引号中的字符串
  
  // 错误的例�
  val mcDonald = """The name "McDonald's" is said "makudonarudo" in Japanese"""
                                                  //> mcDonald  : String = The name "McDonald's" is said "makudonarudo" in Japanes
                                                  //| e
  // 匹配了错误答�
  """".*"""".r findFirstIn mcDonald getOrElse "Not Found."
                                                  //> res0: String = "McDonald's" is said "makudonarudo"
  """"[^"\n]*"""".r findFirstIn mcDonald getOrElse "Not Found."
                                                  //> res1: String = "McDonald's"
  
  // 如果这里是用非贪婪的匹配�
  """".*?"""".r findFirstIn mcDonald getOrElse "Not Found."
                                                  //> res2: String = "McDonald's"
 
 	// 如何匹配带转义的\"�
 
  /*=====================================================================*/
  /*=====================================================================*/
 	// 例子2. 匹配<B>...</B>
 
 	val html = """look <B>Billions</B> and <B>Zillions</B> of suns"""
                                                  //> html  : String = look <B>Billions</B> and <B>Zillions</B> of suns
  val nestHtml = """look <B>Billions and <B>Zillions</B> of</B> suns"""
                                                  //> nestHtml  : String = look <B>Billions and <B>Zillions</B> of</B> suns
            
  """<B>.*?</B>""".r findFirstIn html getOrElse "not found."
                                                  //> res3: String = <B>Billions</B>
 
  //但是对于嵌套版本的是不能正确工作�
  """<B>.*?</B>""".r findFirstIn nestHtml getOrElse "not found."
                                                  //> res4: String = <B>Billions and <B>Zillions</B>
                                                  
                                                  
	// 使用lookahead语法来做
	"""(?x)					# flag，说明忽略空格和注释
	<B>							# 匹配开�B>
	(								#
		(?! <B>)       # 匹配一个位置，这个位置之后不是<B>
		.							# 的任意字�
	)*?
	</B>
	""".r findFirstIn nestHtml getOrElse "Not Found."
                                                  //> res5: String = <B>Zillions</B>
    
  // 看上去感觉这个版本的效率更高啊？对于匹配�/B>时，可以直接匹配对应位置
	"""(?x)
	<B>
	(
		(?! </?B> )
		.
	)*
	</B>
	""".r findFirstIn nestHtml getOrElse "Not Found."
                                                  //> res6: String = <B>Zillions</B>
  // 是之后的话，这个版本就说的通了
  """(?x)
	<B>
	(
		(?! </?B> )
		.
	)*
	</B>
	""".r findFirstIn "<B>test</B></B>" getOrElse "Not Found."
                                                  //> res7: String = <B>test</B>
        
 
  /*=====================================================================*/
  /*=====================================================================*/
  // 匹配日期 例子就是 Jan 31
  
  // 错误的做�可以匹配 Jan 00 Jan 38
  "Jan [0123][0-9]".r findFirstIn "Jan 39" getOrElse "Correct"
                                                  //> res8: String = Jan 39
  
  // 错误做法 因为顺序选择的话，第一个选择可以成功匹配1位或者两位，匹配就结束了 所�Jan 31 匹配的是 Jan 3
  "Jan (0?[1-9]|[12][0-9]|3[01])".r findFirstIn "Jan 31" getOrElse "Not Found"
                                                  //> res9: String = Jan 3
                                                  
  // 拆分日期的几种方案：
  
}