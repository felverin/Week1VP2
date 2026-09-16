package com.fel.wizzyadvent

class StrongWizard(
    name: String,
    MaxHP: Int=75,
    HP: Int=75,
    MaxMana: Int=45,
    mana: Int=45
) : Wizard(name, MaxHP, HP, MaxMana, mana){
    override fun attack(): Int{
        return 15
    }
}