package com.example.worlsnap.navigation

import android.R.attr.type
import androidx.compose.runtime.Composable
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.example.worlsnap.screens.DetailScreen
import com.example.worlsnap.screens.HomeScreen

sealed class Screen(val route: String) {
    object Home : Screen("home")
    object Detail : Screen("detail/{destinoId}") {
        fun createRoute(destinoId: String) = "detail/$destinoId"
    }
}

@Composable
fun WorldSnapNavigation() {
    val navController = rememberNavController()
    NavHost(navController = navController, startDestination = Screen.Home.route) {
        composable(Screen.Home.route) {
            HomeScreen(onDestinoClick = { id ->
                navController.navigate(Screen.Detail.createRoute(id))
            })
        }
        composable(
            route = Screen.Detail.route,
            arguments = listOf(navArgument("destinoId") { type = NavType.StringType })
        ) { backStackEntry ->
            val destinoId = backStackEntry.arguments?.getString("destinoId") ?: return@composable
            DetailScreen(destinoId = destinoId, onBackClick = { navController.popBackStack() })
        }
    }
}