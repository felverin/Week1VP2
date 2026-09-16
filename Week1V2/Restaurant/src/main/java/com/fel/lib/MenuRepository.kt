package com.fel.lib

class MenuRepository: MenuInterface {
    override val menuItems = mutableListOf<MenuItem>()

    override fun fetchMenuItems(): List<MenuItem>{
        return menuItems.toList()
    }

    override fun addMenuItem(menuItem: MenuItem): String {
        menuItems.add(menuItem)
        return "Item added successfully"
    }

    override fun updateMenuItem(index: Int, menuItem: MenuItem): String {
        if (index in menuItems.indices) {
            menuItems[index] = menuItem
            return "Item updated successfully"
        }
        return "Item not found"
    }

    override fun deleteMenuItem(index: Int): String {
        if (index in menuItems.indices) {
            menuItems.removeAt(index)
            return "Item deleted successfully"
        }
        return "Item not found"
    }
}
