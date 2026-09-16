package com.fel.lib

interface MenuInterface {
    val menuItems: MutableList<MenuItem>
    fun fetchMenuItems(): List<MenuItem>
    fun addMenuItem(menuItem: MenuItem): String
    fun updateMenuItem(index: Int, menuItem: MenuItem): String
    fun deleteMenuItem(index: Int): String
}
