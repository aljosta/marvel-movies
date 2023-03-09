package com.example.marvelmovies.ui

import androidx.compose.foundation.layout.padding
import androidx.compose.material.Scaffold
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Home
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.example.marvelmovies.R
import com.example.marvelmovies.TopBar
import com.example.marvelmovies.favorites.ui.FavoriteListView
import com.example.marvelmovies.moviedetail.ui.ComicView
import com.example.marvelmovies.movies.ui.ComicListView
import com.example.marvelmovies.ui.views.BottomNavigationBarView
import com.example.marvelmovies.utils.Constants

sealed class Screen(val route: String) {
    object ComicList : Screen("comiclist")
    object ComicDetail : Screen("comic/{${NavArgs.ComicId.key}}") {
        fun createRoute(comicId: String) = "comic/$comicId"
    }
    object Favorites : Screen("favs")
}

enum class NavArgs(val key: String) {
    ComicId("comicId"),
}

@Composable
fun MarvelAppNavHost(
    modifier: Modifier = Modifier,
    navController: NavHostController = rememberNavController(),
    startDestination: String = Screen.ComicList.route,
) {
    Scaffold(
        topBar = {
            TopBar(
                title = stringResource(id = R.string.comic_detail_top_bar_title),
                icon = Icons.Default.Home,
            )
        },
        bottomBar = { BottomNavigationBarView(navController = navController) },
    ) { paddingValues ->
        NavHost(
            modifier = modifier.padding(paddingValues),
            navController = navController,
            startDestination = startDestination,
        ) {
            composable(Screen.ComicList.route) {
                ComicListView(
                    navigateToDetail = { comicId ->
                        navController.navigate(Screen.ComicDetail.createRoute(comicId))
                    },
                )
            }

            composable(
                route = Screen.ComicDetail.route,
                arguments = listOf(
                    navArgument(NavArgs.ComicId.key) {
                        defaultValue = Constants.EMPTY_STRING
                        type = NavType.StringType
                    },
                ),
            ) {
                ComicView()
            }

            composable(
                route = Screen.Favorites.route,
            ) {
                FavoriteListView()
            }
        }
    }
}
