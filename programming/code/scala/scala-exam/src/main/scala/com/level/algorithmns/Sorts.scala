package com.level.algorithmns

class Sorts {
  
  
  /*
   *	插入排序 
   */
  def isSort(input: List[Int]): List[Int] = input match {
    case List() => input
    case x:: xs => insert(x, isSort(xs))
  }
  
  private def insert(x: Int, xs: List[Int]): List[Int] = xs match {
    case List() => x :: Nil
    case y:: ys => if (x < y) x :: xs else y :: insert(x, ys)
  }
  
  
  
  /*
   * 归并排序
   */
  
  
  
}