package io.h3llo.architectcoders.ui.screens

import androidx.compose.runtime.Composable
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import io.h3llo.architectcoders.movies
import io.h3llo.architectcoders.ui.screens.detail.DetailScreen
import io.h3llo.architectcoders.ui.screens.home.HomeScreen


@Composable
fun Navigation() {

    val navController = rememberNavController()
    NavHost(navController = navController, startDestination =  "home"){
        composable("home"){
            HomeScreen(onClick = {movie ->
                navController.navigate("detail/${movie.id}")

            } )
        }
        composable("detail/{movieId}",
            arguments = listOf(navArgument("movieId"){ type = NavType.IntType })
        ){ backStackEntry ->
            val movieId = backStackEntry.arguments?.getInt("movieId")
            DetailScreen(
                movie = movies.first { it.id == movieId },
                onBack = { navController.popBackStack() }
            )
        }

    }

}