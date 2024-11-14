package com.example.gallery

enum class NavRoutes(val route: String, val header: String) {
    Start("Start", "Start"),
    EntreeMenu("EntreeMenuActivity", "Choose Entree"),
    SideDishMenu("SideDishMenu", "Choose Side Dish"),
    AccompanimentMenu("AccompanimentMenu", "Choose Accompaniment"),
    OrderCheckout("OrderCheckout", "Order Checkout")
}