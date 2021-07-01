package com.panu.overseemobileapp

class add {
   lateinit var devicename : String
    lateinit var note : String
    lateinit var type : String

    constructor(devicename: String, note: String, type: String) {

        this.devicename  = devicename
        this.note = note
        this.type = type
}
}