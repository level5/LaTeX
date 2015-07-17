import scala.actors.Actor._
import com.sun.xml.internal.bind.v2.runtime.unmarshaller.Receiver

object concurrency {
  println("Welcome to the Scala worksheet")       //> Welcome to the Scala worksheet
  
  var startTime: Long = 0                         //> startTime  : Long = 0
  val caller = self                               //> caller  : scala.actors.Actor = scala.actors.ActorProxy@471719b6
  
  val myActor = actor{
  	println("receive how many message so far? " + mailboxSize)
  	
  	caller ! "send"
  	
  	Thread.sleep(3000)
  	
  	println("message I have received when I was busy! " + mailboxSize)
  	receive {
  		case msg =>
  			val receiveTime = System.currentTimeMillis() - startTime
  			println("Receive " + msg + "at " + receiveTime)
  	}
  	
  	caller ! "received"
  }                                               //> myActor  : scala.actors.Actor = scala.actors.Actor$$anon$1@4d04a0e8
  
  receive { case _ =>}                            //> receive how many message so far? 0
  
  println("Main thread send messag!")             //> Main thread send messag!
	myActor ! "Hello buddy"
	
	receive {
		case _ =>
	}                                         //> message I have received when I was busy! 0
                                                  //| Receive Hello buddyat 1436511032450
  
  startTime = System.currentTimeMillis();
                                                  }