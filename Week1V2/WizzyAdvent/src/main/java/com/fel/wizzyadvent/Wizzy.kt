package com.fel.wizzyadvent

class Wizzy {
    private lateinit var wizard: Wizard
    private lateinit var enemy: Enemy

    fun start(){
        println("what is your name?")
        val name=readLine()
        wizard = Wizard(name!!)
        var choice: String?
        do{
            println("goodluck ${wizard.name}, you're gonna need it!")
            println("what are you going to do?")
            println("1. View stats")
            println("2. Enter battle")
            println("0. Quit")
            choice=readLine()

            when (choice){
                "1" -> viewStats(wizard)
                "2" -> enterBattle(wizard)
                "0" -> println("exiting..")
                else -> println("invalid input! must input from number 1-2.")
            }
        }while (choice!="0")
    }

    private fun viewStats(wizard: Wizard){
        while (true) {
            println("${wizard.name}'s stats")
            println("HP: ${wizard.HP}/${wizard.maxHP}")
            println("Mana: ${wizard.mana}/${wizard.maxMana}")
            println("Kills needed to evolve: ${wizard.kills}/5")
            println("Mana Potions Held: ${wizard.manaPotions}")
            println("Health Potions Held: ${wizard.healthPotions}")
            println("Options: ")
            println("1. Drink mana potion")
            println("2. Drink health potion")
            println("3. Rename self")
            println("4. Back")
            val choice = readLine()
            when (choice) {
                "1" -> wizard.drinkManaPotion()
                "2" -> wizard.drinkHealthPotion()
                "3" -> setNewName(wizard)
                "4" -> return
                else -> println("invalid input! must input from number 1-4.")
            }
        }
    }

    private fun enterBattle(wizard: Wizard){
        val enemies = listOf(
            Enemy("Grass", 30, 30),
            Enemy("Fire", 40, 40),
            Enemy("Water", 35, 35),
        )
        enemy = enemies.random()
        val battle = Battle()
        while (enemy.HP > 0 && wizard.HP > 0) {
            println("Battle")
            println(wizard.name)
            println("HP: ${wizard.HP}/${wizard.maxHP}")
            println("Mana: ${wizard.mana}/${wizard.maxMana}")
            println("Kills needed to evolve: ${wizard.kills}/5")
            println("Mana Potions Held: ${wizard.manaPotions}")
            println("Health Potions Held: ${wizard.healthPotions}")
            println("")
            println(enemy.getName())
            println("HP: ${enemy.HP}")
            println("Type: ${enemy.type}")
            println("")
            println("what will you do?")
            println("1. Fire attack")
            println("2. Water attack")
            println("3. Grass attack")
            println("4. Drink Potion")
            println("5. Flee")
            var option= readLine()
            when(option){
                "1" -> if (wizard.mana >= 10){
                    val damage = battle.calculateDamage(
                        wizard, "Fire", enemy.type)
                    enemy.HP -= damage
                    wizard.mana-=10
                    println("${wizard.name} used fire!")
                    println("dealt $damage to ${enemy.getName()}!")
                    println("${enemy.getName()} attacks!")
                    wizard.HP-=10
                    println("${enemy.getName()} dealt 10 damage!")
                }else println("not enough mana!")
                "2" -> if (wizard.mana >= 10){
                    val damage = battle.calculateDamage(
                        wizard, "Water", enemy.type)
                    enemy.HP -= damage
                    wizard.mana-=10
                    println("${wizard.name} used Water!")
                    println("dealt $damage to ${enemy.getName()}!")
                    println("${enemy.getName()} attacks!")
                    wizard.HP-=10
                    println("${enemy.getName()} dealt 10 damage!")
                }else println("not enough mana!")
                "3" -> if (wizard.mana >= 10){
                    val damage = battle.calculateDamage(
                        wizard, "Grass", enemy.type)
                    enemy.HP -= damage
                    wizard.mana-=10
                    println("${wizard.name} used Grass!")
                    println("dealt $damage to ${enemy.getName()}!")
                    println("${enemy.getName()} attacks!")
                    wizard.HP-=10
                    println("${enemy.getName()} dealt 10 damage!")
                }else println("not enough mana!")
                "4" -> {
                    println("which potion?")
                    println("1. health")
                    println("2. mana")
                    println("choose:")
                    val potionpick = readLine()
                    when(potionpick){
                        "1" -> wizard.drinkHealthPotion()
                        "2" -> wizard.drinkManaPotion()
                    }
                }
                "5" -> {println("${wizard.name} has fled!")
                return
                }
                else -> println("invalid input! please choose from 1-5!")
            }
            if (wizard.HP<=0){
                wizard.HP=wizard.maxHP
                enemy.HP=enemy.maxHP
            }
        }

            println("congrats! you won!")
            wizard.kills++
            wizard.HP=wizard.maxHP
            wizard.mana=wizard.maxMana

            if (wizard.kills >= 5 && wizard is Wizard && wizard !is StrongWizard) {
                evolveWizard()
            }

    }

    private fun evolveWizard() {
        wizard = StrongWizard(wizard.name)

        println()
        println("Congratulations!")
        println("${wizard.name} has evolved into a Strong Wizard!")
    }

    private fun setNewName(wizard: Wizard) {
        println("New name:")
        val newName = readln()

        if (newName.isNotBlank()) {
            wizard.name = newName
        } else {
            println("Name cannot be empty!")
        }
    }
}