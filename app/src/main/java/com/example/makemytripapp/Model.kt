package com.example.makemytripapp

import java.io.Serializable

class Model : Serializable{
    var text:String?=null
    var image:Int?=null

    constructor(text:String,image:Int){
        this.text = text;
        this.image = image
    }
}