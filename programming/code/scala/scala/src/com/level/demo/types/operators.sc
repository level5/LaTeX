package com.level.demo.types

// 必须要导入这个方法到当前的scope才能生效

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


/*
优先级：
根据方法的第一个字符来决定优先级，优先级由高到低

* / %
+ -
:
= !
< >
&
^
|
all letters
all assignment operators


特例：当以=结束，又不是<= >= !=中的一个时，优先级和assignment一样。

*/

var a0 = new ALLOpts(0)                           //> a0  : com.level.demo.types.ALLOpts = 0
var a1 = new ALLOpts(1)                           //> a1  : com.level.demo.types.ALLOpts = 1
var a2 = new ALLOpts(2)                           //> a2  : com.level.demo.types.ALLOpts = 2
var a3 = new ALLOpts(3)                           //> a3  : com.level.demo.types.ALLOpts = 3
-a0                                               //> res6: com.level.demo.types.ALLOpts = 0

~a1                                               //> res7: com.level.demo.types.ALLOpts = -2

!a2                                               //> res8: com.level.demo.types.ALLOpts = 1

// 执行顺序是 (a0 ++ a1) **= a2 因为后面这个等号的优先级别最低
a0 ++ a1 **= a2                                   //> res9: com.level.demo.types.ALLOpts = 4

// 看上去可变应该等于2（相对于其他语言来说，但这里和具体实现有关），其实还是0
a0 += a2                                          //> res10: com.level.demo.types.ALLOpts = 2
println(a0)                                       //> 0

// 都是相同的级别，为最低级别
a3 += a1 *= a2                                    //> res11: com.level.demo.types.ALLOpts = 8

// 这里应该是因为=不是方法，是保留字
val result = a3 += a1 *= a2                       //> result  : com.level.demo.types.ALLOpts = 8

a0 +*: a2 ++: a3                                  //> res12: com.level.demo.types.ALLOpts = 0
a0 +*: (a2 ++: a3)                                //> res13: com.level.demo.types.ALLOpts = 0
(a0 +*: a2) ++: a3                                //> res14: com.level.demo.types.ALLOpts = 3

(a0 +*: a2) ++ a3                                 //> res15: com.level.demo.types.ALLOpts = 3
// 相同优先级，但是不同结合性的操作符是不能够结合的，这里会有编译错误
// a0 +*: a2 ++ a3


/*
这个是怎么工作的？？
*/
var x = 1                                         //> x  : Int = 1
x += 2
println(x)                                        //> 3

 

// implicit 转换的方法必须导入到当前的scope才能生效
// 上面从Rational的伴生对象import了这个方法。

}