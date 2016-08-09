package com.level.function

import org.scalatest.FlatSpec
import org.scalatest.Matchers
import org.junit.Test
import org.hamcrest.core.IsInstanceOf

/**
 * @author huangshi
 */
class FunctionDeclareTest extends FlatSpec with Matchers {

  /**
   * 安心写注释吧：
   *    这里是函数定义的几种格式
   */
  @Test def functionDeclareTest() {

    // 参数列表，返回类型，等于后的if表达式(不叫if语句，因为这个表达式是返回值的)
    def max(x: Int, y: Int): Int = {
      // 返回值是函数块的最后一条表达式的值
      if (x >= y) x else y
    }

    // 只有一条表达式的时候，大括号可以省略，但是参数列表的括号是必需有的，不像ruby一样可以省略
    def max2(x: Int, y: Int): Int = if (x >= y) x else y

    // 这里同样可以使用类型推导来推导返回值
    def max3(x: Int, y: Int) = if (x >= y) x else y
    
    def max4:(Int, Int) ⇒ Int = max3 

    max(1, 2) should equal(2)
    max2(1, 2) should equal(2)
    max3(1, 2) should equal(2)
    
    max4(1, 2) should equal (2)

  }
  
  @Test def testDiffBetweenValDef() {
    var tmp = 10
    
    val lengthVal = tmp
    def lengthDef = tmp
    
    lengthVal should equal (10)
    lengthDef should equal (10)
    
    tmp = 20
    
    lengthVal should equal (10)
    lengthDef should equal (20)
  }

  @Test def unitResult() {

    // 返回结果是Unit表示对返回结果不感兴趣，java中的返回void的方法都是返回Unit
    // 返回结果是Unit，表明调用这个方法，只是为了这个方法产生的side effects.不是函数编程的风格
    def greeting(): Unit = "hello"

    // 这种情况下，即使又返回值，也会被抛弃
    def greet1() {
      "hello" // 最后一个语句的值，就是整个函数的返回值.但是这里因为省略了等号，所以返回值是Unit
    }

    // TODO: 添加一个 greeting() should be (anInstanceOf[Unit])。 目前还不太懂
    greeting().isInstanceOf[Unit] should be(true)
    // 不带参数，这个括号可以省略(这里也会调用，看输出有两次的)(操蛋了，这样也可以继续带点)
    greeting.isInstanceOf[Unit] should be(true)
    greet1.isInstanceOf[Unit] should be(true) // 返回的String被忽略了
  }

  /**
   * scala 有四种类型的标识符
   *
   * <ol>
   * <li>alphanumeric identifier 字母或者下划线开始，接着是数字字母或者下划线。$被当做是字母，但是被scala编译器当做保留字来生成标识符，程序中不应该包含$</li>
   * <li>operator identifier 非字母，数字，下划线的ASCII字符组成</li>
   * <li>mixed identifier <alphanumeric>_<operator></li>
   * <li>literal identifier 使用`<any string>`的格式</li>
   * </ol>
   *
   */
  @Test def testIdentified() {

    class Sample(n: Int) {

      override def toString: String = "sample: " + n
    }

    // 还不能算constant
    val notConstant = "not a constant"

    // 这个是constant，约定命名规则和Java不一样，不是X_OFFSET,而是XOffset
    scala.math.Pi

    // 使用  `<identifier>`的格式，可以使用关键词来作为方法名
    def `class`(o: Any): Class[_] = o.getClass()

  }

  @Test def testFunctionValue() {

    // 格式 1
    val increase = (x: Int) => x + 99
    increase(1) should equal(100)

    // 格式 2，带上大括号，可以更上语句块，最后一个表达式是返回值
    val increase2 = (x: Int) => {
      x + 11
      x + 22
      x + 99 // 这条表达式的值才是方法的返回值
    }
    increase2(1) should equal(100)

    // 格式3，参数类型可以自动推导.
    List(1) map {
      (x) => x + 99
    } should equal(List(100))

    // 格式4，如果只有一个参数，而且参数的类型可以推导，那么可以省略他的括号
    List(1, 2, 3, 4) filter {
      x => x % 2 == 0
    } should equal(List(2, 4))

    // 格式5， 下划线替代参数.当所有参数都只在函数中出现一次。就可以用下划线来代替他们
    // 效果和上面的相同
    List(1, 2, 3, 4) filter (_ % 2 == 0) should equal(List(2, 4))

    // 当编译器需要的信息不够时，需要自己提供
    val f = (_: Int) + (_: Int)
    f(5, 5) should equal(10)
    // 实际上这是一个scala自动生成的function对象的实例，有一个apply方法
    f.apply(5, 5) should equal(f(5, 5))

    // 格式6， 可以使用一个下划线代替整个的参数列表
    // 此处increase3接收一个带两个参数的函数
    def increase3(f: (Int, Int) => Int): Int = f(5, 5) + 5
    def func(x: Int, y: Int) = x + y
    increase3(func _) should equal(15)

    // TODO: 为啥不能用变量引用的函数,编译器解释需要跟随在一个函数后面.使用def定义的是什么？
    // increase3(f _)

  }

  @Test def testValVsDef() {

    // 每次都会生成新的Function1的实例
    def defEven: Int => Boolean = _ % 2 == 0
    (defEven eq defEven) should be(false)

    val valEven: Int => Boolean = _ % 2 == 0
    (valEven eq valEven) should be(true)
  }

  @Test def testRadom() {

    val test: () => Int = {
      val r = util.Random.nextInt
      () => r
    }
    test() should equal(test())
  }

  @Test def testPartiallyApplyFunction() {

    def sum(a: Int, b: Int, c: Int): Int = a + b + c

    val sum2 = sum _

    // 貌似这样得到的是一个Function Value
    val sum3: (Int, Int, Int) => Int = sum

  }

  @Test def testClosure() {

    val more = 7
    val add7: Int => Int = _ + more
    add7(3) should be(10)

    def add7_2(a: Int) = a + more
    add7_2(3) should be(10)

  }

  /**
   *  闭包引用的是变量，而不是变量引用的值
   */
  @Test def testClosure2() {
    var more = 7
    val addMore = (_: Int) + more

    addMore(3) should equal(10)

    more = 17
    addMore(3) should equal(20)
  }

  /**
   * 闭包引用的free variable和闭包创建的时候的外部变量的实例
   */
  @Test def testClosure3() {

    def add(n: Int) = (x: Int) => x + n

    val add3 = add(3)
    val add7 = add(7)

    add3(7) should equal(10)
    add7(13) should equal(20)
  }

  @Test def testRepeatParameters() {

    def sum(numbers: Int*): Int = (0 /: numbers) { _ + _ }
    sum(1, 2, 3, 4, 5, 6, 7, 8, 9, 10) should be(55)

    // 应用List的话
    sum(List(1, 2, 3, 4, 5, 6, 7, 8, 9, 10): _*) should be(55)
    sum(Array(1,2,3,4,5): _*) should be (15)
    
  }
  
  @Test def testNamedParameters() {
    
  }

}