package com.fel.lib

class Orders(
    val customerName: String,
    val items: MutableList<OrderItem>
) {
    fun getTotal(): Double {
        var total = 0.0

        for (item in items) {
            total += item.getSubtotal()
        }

        return total
    }
}