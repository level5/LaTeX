package com.level.demo.types

object operators {

/*
	操作符其实就是方法，只不过是scala支持的一种方法调用的语法
*/

1 + 2                                             //> res0: Int(3) = 3

// 实际上就是方法
(1).+(2)                                          //> res1: Int(3) = 3

/*
 	只是写法不一样.
 	如果一个方法只有一个参数，就可以写作 base methodname parameter
	如果一个方法有多个参数，写成operator的形式则参数必须带括号 base methodname (parameter...)
*/
"12345" indexOf "2"                               //> res2: Int = 1
"12345" indexOf ("2", 3)                          //> res3: Int = -1

/*
上面是二元操作符，还有一元操作符

对于前置操作符例如-2,会转换成(2).unary_-，只有-, ~, !三个可以当做前置操作符

也就是说他们分别映射到方法 unary_-, unary_~, unary_!上，其他unary_开头的方法都不能映射成前置操作符

*/
(2).unary_-                                       //> res4: Int = -2

val num = -2                                      //> num  : Int = -2

// 对于后置，就是不带参数的方法，一般来说，如果方法有副作用，就会带上括号，如果方法没有副作用就省略括号

"ABCD" toLowerCase                                //> res5: String = abcd

}