package com.example.diplomproject

sealed class Screen(val route: String) {
    object First : Screen ("first_screen")
    object MainScreen : Screen ("main_screen")
    object ProfileScreen : Screen ("profile_screen")
    object TicketsScreen : Screen ("tickets_screen")
    object QuestionsScreen : Screen ("questions_screen")
    object QuestionsPreview : Screen ("questions_preview")
    object QuestionsResults : Screen ("questions_results")
}
