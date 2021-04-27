package com.panu.overseemobileapp

class Datarealtime {
    var voltage : Double = 0.00
    var current : Double? = 0.00
    var energy : Double? = 0.00
    var watt : Double? = 0.00
    var frequency : Double? = 0.00
    var powerfactor : Double? = 0.00

    constructor(voltage:Double,current:Double,energy:Double,watt:Double,frequency:Double,powerfactor:Double) {
        this.voltage = voltage
       this.current = current
        this.energy = energy
        this.watt = watt
        this.frequency = frequency
        this.powerfactor = powerfactor

    }

}