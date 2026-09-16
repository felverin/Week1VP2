package com.fel.wizzyadvent;

class Battle {

    fun calculateDamage(
        wizard: Wizard,
        spellType: String,
        enemyType: String
    ): Int {

        var damage = wizard.attack()

        if (
            (spellType == "Fire" && enemyType == "Grass") ||
            (spellType == "Grass" && enemyType == "Water") ||
            (spellType == "Water" && enemyType == "Fire")
        ) {
            damage *= 2
        }

        return damage
    }
}
