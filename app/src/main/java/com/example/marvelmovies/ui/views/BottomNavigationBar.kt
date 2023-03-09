package com.example.marvelmovies.ui.views

import androidx.compose.material.BottomNavigation
import androidx.compose.material.BottomNavigationItem
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.List
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.stringResource
import androidx.navigation.NavController
import androidx.navigation.compose.currentBackStackEntryAsState
import com.example.marvelmovies.R
import com.example.marvelmovies.ui.Screen

sealed class NavigationBar(val title: Int, val icon: ImageVector, val screen: Screen) {
    object ComicList : NavigationBar(
        R.string.navigation_comic_list_label,
        Icons.Default.List,
        Screen.ComicList,
    )
    object Favorites : NavigationBar(
        R.string.navigation_favorites_label,
        Icons.Default.Favorite,
        Screen.Favorites,
    )
}

@Composable
fun BottomNavigationBarView(
    navController: NavController,
) {
    val navItems = listOf(
        NavigationBar.ComicList,
        NavigationBar.Favorites,
    )

    BottomNavigation(
        contentColor = Color.White,
    ) {
        val navBackStackEntry by navController.currentBackStackEntryAsState()
        val currentRoute = navBackStackEntry?.destination?.route

        navItems.onEach { navItem ->
            BottomNavigationItem(
                selected = currentRoute == navItem.screen.route,
                icon = { Icon(imageVector = navItem.icon, contentDescription = stringResource(id = navItem.title)) },
                label = { Text(text = stringResource(id = navItem.title)) },
                onClick = {
                    navController.navigate(navItem.screen.route)
                },
            )
        }
    }
}
