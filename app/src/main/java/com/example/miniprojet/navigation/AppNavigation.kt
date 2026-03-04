package com.example.miniprojet.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.example.miniprojet.screens.PostDetailScreen
import com.example.miniprojet.screens.PostFormScreen
import com.example.miniprojet.screens.PostListScreen

@Composable
fun AppNavigation() {
    val navController = rememberNavController()

    NavHost(navController = navController, startDestination = "posts") {
        composable("posts") {
            PostListScreen(
                onOpenPost = { id -> navController.navigate("post/$id") },
                onCreatePost = { navController.navigate("post_form") }
            )
        }

        composable(
            route = "post/{postId}",
            arguments = listOf(navArgument("postId") { type = NavType.IntType })
        ) { backStackEntry ->
            val postId = backStackEntry.arguments?.getInt("postId") ?: return@composable
            PostDetailScreen(
                postId = postId,
                onEdit = { navController.navigate("post_form?postId=$postId") },
                onBack = { navController.popBackStack() }
            )
        }

        composable(
            route = "post_form?postId={postId}",
            arguments = listOf(navArgument("postId") {
                type = NavType.IntType
                defaultValue = -1
            })
        ) { backStackEntry ->
            val postIdArg = backStackEntry.arguments?.getInt("postId") ?: -1
            val postId = if (postIdArg == -1) null else postIdArg
            PostFormScreen(
                postId = postId,
                onSaved = { navController.popBackStack() },
                onBack = { navController.popBackStack() }
            )
        }
    }
}
