package com.fel.lib

class OrderItem(
    val menuItem: MenuItem,
    var quantity: Int
) {
    fun getSubtotal(): Double {
        return menuItem.price * quantity
    }
}