package com.level.scala

/**
 * @author huangshi
 */
  private class Resource {
    println("[Resource] start transaction.")
    
    def cleanup = {println("[Resource] end transaction.")}
    
    def op1 = println("[Resource] op1 start ...")
    def op2 = println("[Resource] op2 start ...")
    def op3 = println("[Resource] op3 start ...")
      
  }
  
  object Resource {
    def use(codeBlock: Resource => Unit) {
      val resource = new Resource
      try {
        codeBlock(resource)
      } finally {
        resource cleanup
      }
    }
  }
  
