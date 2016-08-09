package samples

import org.specs2._
import org.junit.runner.RunWith
import org.specs2.runner.JUnitRunner


@RunWith(classOf[JUnitRunner])
class MySpecTest extends Specification { def is = s2"""
  This is a specification to check the 'Hello world' string
  The 'Hello world' string satisfies the following properties:
   ${ "Hello world" must haveSize(11)       }
   ${ "Hello world" must startWith("Hello") }
   ${ "Hello world" must endWith("world")   }
                                                               """
}