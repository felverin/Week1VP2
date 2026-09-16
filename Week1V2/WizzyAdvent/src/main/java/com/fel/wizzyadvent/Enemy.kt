package com.fel.wizzyadvent

class Enemy (
    var type: String,
    var maxHP: Int = 30,
    var HP:Int = maxHP
){
    fun getName(): String{
        return type+"mon"
    }
}

