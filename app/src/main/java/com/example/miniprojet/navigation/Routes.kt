package com.example.miniprojet.navigation

sealed class Screen(val route: String) {

    object List : Screen("list")

    object Details : Screen("details/{id}") {
        fun create(id: Int) = "details/$id"
    }

    object Form : Screen("form")

    object Edit : Screen("form/{id}") {
        fun create(id: Int) = "form/$id"
    }
}
