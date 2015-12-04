package com.level.demo.regexp

object tt {
  println("Welcome to the Scala worksheet")       //> Welcome to the Scala worksheet

  val unicode = """.""".r                         //> unicode  : scala.util.matching.Regex = .

  55362 toHexString                               //> res0: String = d842

  57271 toHexString                               //> res1: String = dfb7

  134071 toHexString                              //> res2: String = 20bb7

  "\ud842\udfb7"                                  //> res3: String("馉") = 馉

  "\ud842\udfb7" match {
    case unicode() => true
    case _         => false
  }                                               //> res4: Boolean = true

  val canoneql = """a\u030A""".r                  //> canoneql  : scala.util.matching.Regex = a�

  "s".r                                           //> res5: scala.util.matching.Regex = s

  "\u00E5" match {
    case canoneql() => true
    case _          => false
  }                                               //> res6: Boolean = false

  /*=====================================================================*/

  /*
   * 如果使用scala提供的regex对象的话，flags必须嵌套在pattern string中，
   * 因为不提供option来提供flags
   * 例子�(?i)打开忽略大小写， (?-i)关闭忽略大小�
   * 支持的mode
   */

  // i 忽略大小�
  val caseIgnore = """(?i)hello""".r              //> caseIgnore  : scala.util.matching.Regex = (?i)hello
  "HellO" match {
    case caseIgnore() => true
    case _            => false
  }                                               //> res7: Boolean = true

  // x 注释，准许空白和注释出现在pattern�
  val withComment = """(?x)
					(								# group 1: whole matched string
					(\d\d\d\d)			# group 2: year
					-
					(\d\d)					# group 3: month
					-
					(\d\d)					# group 4: date
					)
	""".r                                     //> withComment  : scala.util.matching.Regex = (?x)
                                                  //| 					(					
                                                  //| 			# group 1: whole matched string
                                                  //| 					(\d\d\d\d)			# group 
                                                  //| 2: year
                                                  //| 					-
                                                  //| 					(\d\d)					
                                                  //| # group 3: month
                                                  //| 					-
                                                  //| 					(\d\d)					
                                                  //| # group 4: date
                                                  //| 					)
                                                  //| 	
  for (m <- withComment findFirstMatchIn "today is 2015-09-14") yield {
    val whole = m group 1
    val year = m group 2
    val month = m group 3
    val date = m group 4
    s"matched: $whole. year is $year, month is $month, date is $date"
  }                                               //> res8: Option[String] = Some(matched: 2015-09-14. year is 2015, month is 09,
                                                  //|  date is 14)
  // s dot匹配newline
  // .不匹配换�
  ".".r findFirstIn "\n"                          //> res9: Option[String] = None
  // s是的.匹配换行
  "(?s).".r findFirstIn "\n"                      //> res10: Option[String] = Some(
                                                  //| )
  //

  // m mulitline模式，允许^ $ 匹配字符串中的newline
  val multiline = """(?m)^Haha$""".r              //> multiline  : scala.util.matching.Regex = (?m)^Haha$
  val singleLine = """^Haha$""".r                 //> singleLine  : scala.util.matching.Regex = ^Haha$
  multiline findAllIn "Haha\nHaha\nHaha" toList   //> res11: List[String] = List(Haha, Haha, Haha)
  
  singleLine findAllIn "Haha\nHaha\nHaha" toList  //> res12: List[String] = List()
  
  
  

  }