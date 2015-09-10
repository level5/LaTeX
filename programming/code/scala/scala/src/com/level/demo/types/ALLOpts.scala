package com.level.demo.types

/**
 * @author huangshi
 */
class ALLOpts(num: Int) {
  
  val n = num // 暂时这么写了，应该有更好的方法
  
  /*
   * 三个前置的一元操作符- ~ !  
   */
  def unary_-() = new ALLOpts(-n)
  
  def unary_~() = new ALLOpts(~n)
  
  def unary_!() = new ALLOpts(if (n != 0) 1 else 0)
  
  // 这些方法就不属于前置一元操作符了。
  def unary_##() = new ALLOpts(0)
  
  
  /*
   * 各个优先级别的操作符，根据操作符的第一个字符来判断，下面由高到低
   * 
   * 操作标识符，就是7位ASCII中除去字母和数字的操作符
   * 
   */
  
  // 编译错误
  // def u#() = new ALLOpts(0)
  
  // mixed identifier. 字母和符号下划线分开是可以的 
  def uu_!() = new ALLOpts(0)
  def uu_!@%() = new ALLOpts(0)
  
  // 但是这样是不行的
  // def @@_uu() = new ALLOpts(0)
  
  
  // * / %
  
  def *(o: ALLOpts) = new ALLOpts(n * o.n)
  
  def /(o: ALLOpts) = new ALLOpts(n / o.n)
  
  def %(o: ALLOpts) = new ALLOpts(n % o.n)
  
  // + -
  
  def +(o: ALLOpts) = new ALLOpts( n + o.n)
  def -(o: ALLOpts) = new ALLOpts( n - o.n)
  
  def +++(o: ALLOpts) = new ALLOpts( n + 2 * o.n)
  def ---(o: ALLOpts) = new ALLOpts( n - 2 * o.n)
  
  // 特殊规则
  
  def *=(o: ALLOpts) = new ALLOpts(n * o.n)
  def +=(o: ALLOpts) = new ALLOpts(n + o.n)
  
  def **=(o: ALLOpts) = new ALLOpts(n * o.n * o.n)
  
  
  // 当优先级别一样的时候，就看结合性了。不单单是谁是调用者啊，下面的这段描述也是满足的。
  
  /*
   * Associativity means whether an expression like x R y R z (where R is a operator such as + or <=) 
   * should be evaluated `left-to-right' i.e. as (x R y) R z or `right-to-left' i.e. as x R (y R z). 
   * Precedence determines how an expression like x R y S z should be evaluated (now R and S are different operators). 
   * If R has higher precedence than S, it will be evaluated as (x R y) S z, 
   * while if S has higher precedence than R it will be treated as x R (y S z).
   */
  
  
  // 由操作符的最后一个字符决定，:表示右边的是接收方法调用的对象，左边是参数
  
  def +*:(o: ALLOpts) = new ALLOpts(n * o.n)
  def ++:(o: ALLOpts) = new ALLOpts(n + o.n)
  
  def +*(o: ALLOpts) = new ALLOpts(n * o.n)
  def ++(o: ALLOpts) = new ALLOpts(n + o.n)
  
  
  def u_:(o: ALLOpts) = new ALLOpts(if(n == o.n) 0 else 1)
  
  override def toString = n + ""
  
}