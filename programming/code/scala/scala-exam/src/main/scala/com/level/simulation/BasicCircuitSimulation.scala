package com.level.simulation

abstract class BasicCircuitSimulation extends Simulation {
  
  def InverterDelay: Int
  def AndGateDelay: Int
  def OrGateDelay: Int
  
  class Wire {
    private var sigVal = false
    private var actions: List[Action] = List()
    def getSignal = sigVal
    
    def setSignal(s: Boolean) = 
      if (s != sigVal) {
        sigVal = s
        actions foreach (_ ())
      }
    
    def addAction(a: Action) = {
      actions = a :: actions
      a()
    }
  }
}