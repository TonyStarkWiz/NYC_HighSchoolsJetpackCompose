package com.example.nyc_highschools.compose

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.nyc_highschools.compose.schoollist.NYCSchoolsListScreen


@Composable
fun MainScreen() {
    val navController = rememberNavController()
    NYCNavHost(navController = navController)
}

@Composable
fun NYCNavHost(navController: NavHostController) {

    NavHost(
        navController = navController,
        startDestination = "home"
    ) {
        composable("home") {
            NYCSchoolsListScreen(
                onSchoolClick = {}
            )
        }
    }
}
