object containers {
  
  //set
  val colors1 = Set("red", "yellow", "blue");     //> colors1  : scala.collection.immutable.Set[String] = Set(red, yellow, blue)
  val colors2 = Set("gray", "black", "red", "blue");
                                                  //> colors2  : scala.collection.immutable.Set[String] = Set(gray, black, red, bl
                                                  //| ue)
  
  val f1 = colors1 filter { color => color startsWith "r" }
                                                  //> f1  : scala.collection.immutable.Set[String] = Set(red)
  val f2 = colors1 filter { _ startsWith "r" }    //> f2  : scala.collection.immutable.Set[String] = Set(red)
  
  
  val s1 = colors1 ++ colors2                     //> s1  : scala.collection.immutable.Set[String] = Set(blue, black, gray, yellow
                                                  //| , red)
  // set.head, set.tail
  def **(set1: Set[String], set2: Set[String]): Set[String] = {
    if (set1.size == 0)
    	Set()
    else
    	**(set1.tail, set2) ++ (if(set2.contains(set1.head)) Set(set1.head) else Set())
  }                                               //> ** : (set1: Set[String], set2: Set[String])Set[String]
  
  val c1 = **(colors1, colors2)                   //> c1  : Set[String] = Set(blue, red)
  
  
}