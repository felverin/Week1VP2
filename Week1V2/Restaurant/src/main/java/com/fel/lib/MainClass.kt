package com.fel.lib

class MainClass {
    private val menuRepository = MenuRepository()
    private val orders = mutableListOf<Orders>()

    fun start() {
        var choice: String?
        do {
            println("\n=== Welcome to My Restaurant ===")
            println("1. Make Orders")
            println("2. View Orders")
            println("3. View Menu")
            println("4. Add Menu")
            println("5. Edit Menu")
            println("6. Delete Menu")
            println("0. Exit")
            print("Choose from 0-6: ")
            choice = readLine()

            when (choice) {
                "1" -> makeOrder()
                "2" -> viewOrders()
                "3" -> viewMenu()
                "4" -> addMenu()
                "5" -> editMenu()
                "6" -> deleteMenu()
                "0" -> println("Exiting...")
                else -> println("Invalid input! Please choose a number from 0-6.")
            }
        } while (choice != "0")
    }

    private fun viewMenu() {
        val items = menuRepository.fetchMenuItems()
        if (items.isEmpty()) {
            println("Menu is empty.")
            return
        }
        println("\n--- Menu List ---")
        items.forEachIndexed { index, item ->
            println("${index + 1}. ${item.name} - ${item.description} (Price: ${item.price})")
        }
    }

    private fun addMenu() {
        print("Enter name: ")
        val name = readLine() ?: ""
        print("Enter description: ")
        val desc = readLine() ?: ""
        print("Enter price: ")
        val price = readLine()?.toDoubleOrNull() ?: 0.0

        val newItem = MenuItem(name, desc, price)
        println(menuRepository.addMenuItem(newItem))
    }

    private fun editMenu() {
        viewMenu()
        val items = menuRepository.fetchMenuItems()
        if (items.isEmpty()) return

        print("Enter index to edit (1-${items.size}): ")
        val index = readLine()?.toIntOrNull()?.minus(1) ?: -1

        if (index in items.indices) {
            print("Enter new name (leave empty to keep current): ")
            val name = readLine().takeIf { !it.isNullOrBlank() } ?: items[index].name
            print("Enter new description (leave empty to keep current): ")
            val desc = readLine().takeIf { !it.isNullOrBlank() } ?: items[index].description
            print("Enter new price (leave empty to keep current): ")
            val priceInput = readLine()
            val price = if (priceInput.isNullOrBlank()) items[index].price else priceInput.toDoubleOrNull() ?: items[index].price

            val updatedItem = MenuItem(name, desc, price)
            println(menuRepository.updateMenuItem(index, updatedItem))
        } else {
            println("Invalid index.")
        }
    }

    private fun deleteMenu() {
        viewMenu()
        val items = menuRepository.fetchMenuItems()
        if (items.isEmpty()) return

        print("Enter index to delete (1-${items.size}): ")
        val index = readLine()?.toIntOrNull()?.minus(1) ?: -1
        println(menuRepository.deleteMenuItem(index))
    }

    private fun makeOrder() {
        val menuItems = menuRepository.fetchMenuItems()
        if (menuItems.isEmpty()) {
            println("Cannot make order. Menu is empty.")
            return
        }

        print("Enter customer name: ")
        val customerName = readLine() ?: "Guest"
        val orderItems = mutableListOf<OrderItem>()

        do {
            viewMenu()
            print("Choose item by index (or 0 to finish): ")
            val itemIndex = readLine()?.toIntOrNull()?.minus(1) ?: -1

            if (itemIndex == -1) break

            if (itemIndex in menuItems.indices) {
                print("Enter quantity: ")
                val quantity = readLine()?.toIntOrNull() ?: 1
                if (quantity > 0) {
                    orderItems.add(OrderItem(menuItems[itemIndex], quantity))
                    println("Added to order.")
                } else {
                    println("Quantity must be greater than 0.")
                }
            } else {
                println("Invalid item index.")
            }

            print("Add more items? (y/n): ")
        } while (readLine()?.lowercase() == "y")

        if (orderItems.isNotEmpty()) {
            val newOrder = Orders(customerName, orderItems)
            orders.add(newOrder)
            println("Order placed successfully for $customerName. Total: ${newOrder.getTotal()}")
        } else {
            println("Order canceled (no items added).")
        }
    }

    private fun viewOrders() {
        if (orders.isEmpty()) {
            println("No orders placed yet.")
            return
        }
        println("\n--- Orders History ---")
        orders.forEachIndexed { index, order ->
            println("Order #${index + 1} - Customer: ${order.customerName}")
            order.items.forEach { item ->
                println("  - ${item.menuItem.name} x${item.quantity} (Subtotal: ${item.getSubtotal()})")
            }
            println("Total: ${order.getTotal()}")
            println("-----------------------")
        }
    }
}
