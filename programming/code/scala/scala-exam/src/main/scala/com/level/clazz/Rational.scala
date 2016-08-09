package com.level.clazz

/**
 * @author huangshi
 */
//                                      V 不带语句的话，后面的大括号可以直接省略
class Rational2(var n: Int, var d: Int)
//               ^ 当然可以自己将他强制定义为var, 好像不能强制定义成public的

class Rational(n: Int, d: Int) {
  //               ^  n , d 是 private 的 val. 
  //                          这个私有限制的比java的强。只能访问自己的实例的私有变量

  // 语句,包含初始语句会被加入到primary constructor
  require(d != 0)

  private val g = gcd(n.abs, d.abs)
  val numer = n / g
  val denom = d / g

  def this(n: Int) = this(n, 1) // Auxiliary constructor

  /**
   * 对于Auxiliary constructor来说：
   *
   * 第一行是调用定义在他前面的Auxiliary constructor或者primary constructor.
   * 这个限制是为了保证所有的调用最后都到达primary constructor
   */
  def this() = {
    this(1)
    // ... 其他语句
  }

  /**
   * private 定义的是私有方法；
   *
   * scala默认的访问级别是public。不带修饰符的访问级别是public的
   *
   * 递归调用没办法做到类型推导，必须显式的指定函数的返回类型
   *
   * gcd之所以不需要判断a > b, 是因为第一次调用会自动将他们掉过了头来
   */
  private def gcd(a: Int, b: Int): Int = if (b == 0) a else gcd(b, a % b)

  override def toString: String = numer + "/" + denom

  /* ==========================================================================
   * 
   * equals方法
   * 
   * ==========================================================================
   */

  /**
   * 下面说的情况都是x，y，z非空的情况下
   *
   * 1. x == x 成立
   *
   * 2. x == y, 则 y == z
   *
   * 3. x == y, y == z 则 x == z
   *
   * 4. x == y, 则 x == y一直成立
   *
   * 5. x == null 的结果是false
   *
   */
  override def equals(other: Any) = other match {
    case that: Rational => (that canEqual this) && (numer == that.numer) && (denom == that.denom)
    //                        ^ 这个地方有点绕
    case _              => false
  }

  /**
   * 和hashCode，equals方法一起定义，用来保证第2条，第3条
   */
  def canEqual(other: Any): Boolean = other.isInstanceOf[Rational]
  //                                                     ^ 重新定义canEqual方法的Class    

  // hashCode方法也要跟着一起重写
  override def hashCode(): Int = 41 * (41 * numer) + denom

  // 三个方法,equals, hashCode和canEqual一起重写 

  /* ==========================================================================
   * 
   * 运算符部分
   * 
   * ==========================================================================
   */

  def +(that: Rational) =
    Rational(numer * that.denom + that.numer * denom, denom * that.denom)

  def *(that: Rational) = Rational(numer * that.numer, denom * that.denom)
  
  def *(i: Int) = Rational(numer * i, denom)
  
}

//      V 伴生对象，要定义在同一个源文件里面，不会有参数，因为不能实例化
object Rational {

  // 直接对象带括号，就是调用对应的apply方法
  def apply(n: Int, d: Int) = new Rational(n, d)

  def apply(n: Int) = new Rational(n)

  def apply() = new Rational()
}
