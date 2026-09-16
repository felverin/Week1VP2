package com.fel.wizzyadvent

open class Wizard(
    var name: String,
    var maxHP: Int = 50,
    var HP: Int=50,
    var maxMana: Int=30,
    var mana: Int=30,
    var manaPotions: Int=5,
    var healthPotions: Int=5,
    var kills: Int=0
){
    fun drinkManaPotion() {
        if (manaPotions > 0){
            mana+=15
            manaPotions--
        }else{
            println("not enough mana potion!")
        }
    }

    fun drinkHealthPotion(){
        if(healthPotions > 0){
            HP+=25
            healthPotions--
        }else {
            println("not enough mana potion!")
        }
    }

    open fun attack(): Int{
        return 10
    }
}

