package com.level.traits

import org.junit.Test
import org.scalatest.Matchers
import org.scalatest.FlatSpec
import scala.xml.Null
import scala.collection.mutable.ArrayBuffer

/**
 * @author huangshi
 */
class TraitsTutorial extends FlatSpec with Matchers {

  @Test def testTrait() {

    /**
     *  没有显示定义superclass， 所以superclass是 AnyRef
     */
    trait Philosophical {

      def philosophize: String = "I consume memory, therefore I am!"

    }

    /**
     *  因为extends了trait，所以Frog就隐式的继承了trait的supperclass
     */
    class Frog extends Philosophical {

      override def toString = "Green"
    }

    // 这里可以指定为trait的类型
    val frog: Philosophical = new Frog
    frog.philosophize should equal("I consume memory, therefore I am!")

    /*==================================================================*/

    class Animal
    trait HasLeg

    // 可以显示指定superclass，而使用with来mix trait, 还可以with多个trait
    class Frog2 extends Animal with Philosophical with HasLeg {
      override def toString = "Green"
    }

    /*===================================================================*/

  }

  /**
   * 只需要实现少量的基础方法，然后mix基于这少量方法的trait实现更多功能
   */
  @Test def testThintoRichInterfaceSample() {

    class Point(val x: Int, val y: Int)
    object Point {
      def apply(x: Int, y: Int) = new Point(x, y)
    }

    trait Rectangular {

      def topLeft: Point
      def bottomRight: Point

      def left = topLeft.x
      def right = bottomRight.x

      def width = right - left

    }

    abstract class Component extends Rectangular

    // 这里 只需要实现topLeft和bottomRight,就可以取得left，right， width这些功能了。
    class Rectangle(val topLeft: Point, val bottomRight: Point) extends Rectangular
    object Rectangle {
      def apply(topLeft: Point, bottomRight: Point) = new Rectangle(topLeft, bottomRight)
    }

    val rect = Rectangle(Point(1, 1), Point(10, 10))

    rect.left should equal(1)
    rect.right should equal(10)
    rect.width should equal(9)

  }

  @Test def testThintoRichInterfaceSample2() {

    // 实现compare方法。通过Ordered来获得<, >, <=, >=方法。没办法获得==，因为equals方法涉及到类型的判断
    class Num(private val x: Int) extends Ordered[Num] {
      def compare(that: Num): Int = this.x - that.x
    }
    object Num {
      def apply(n: Int) = new Num(n)
    }

    (Num(0) < Num(1)) should be(true)
    (Num(0) <= Num(1)) should be(true)

    (Num(2) > Num(1)) should be(true)

  }

  @Test def testStackableModifications() {

    abstract class IntQueue {
      def get(): Int
      def put(n: Int): Unit
    }

    class BasicIntQueue extends IntQueue {
      private val buf = new ArrayBuffer[Int]
      override def get = buf remove 0
      override def put(n: Int) { buf += n }
    }

    trait Doubling extends IntQueue {
      //               ^ 表示trait只能被继承子IntQueue的class来mix
      abstract override def put(n: Int) { super.put(2 * n) }
    }

    val queue = new BasicIntQueue with Doubling

    queue put 1
    queue.get should equal(2)

    trait Increment extends IntQueue {
      abstract override def put(n: Int) { super.put(1 + n) }
    }

    trait Filter extends IntQueue {
      abstract override def put(n: Int) {
        if (n > 0) super.put(n)
      }
    }

    // mix从左到右生效
    val mixedQueue = new BasicIntQueue with Doubling with Increment
    
    mixedQueue put 1
    mixedQueue.get should equal (4)
    
  }
  
  /**
   *  super是顺序的从最右到左调用线性的调用
   */
  @Test def testOrderForMutiTraitsMix() {
    
    class Animal {
      def check = "Animal"
    }
    
    trait Furry extends Animal {
      override def check = super.check + " Furry"
    }
    
    trait HasLegs extends Animal {
      override def check = super.check + " HasLegs"
    }
    
    trait FourLegged extends HasLegs {
      override def check = super.check + " FourLegged"
    }
    
    class Cat extends Animal with Furry with FourLegged {
      
      override def check = super.check + " Cat"
    }
    
    (new Cat).check should equal ("Animal Furry HasLegs FourLegged Cat")
    
  }
  
  @Test def testPackageObject() {
    
    import com.level.traits.test
    
    test should equal ("This is a function in package object")
  }

}


