    package com.imperatorofdwelling.android.presentation.ui.navigation

    import androidx.compose.animation.AnimatedVisibility
    import androidx.compose.foundation.layout.Box
    import androidx.compose.foundation.layout.RowScope
    import androidx.compose.foundation.layout.fillMaxHeight
    import androidx.compose.foundation.layout.fillMaxSize
    import androidx.compose.foundation.layout.padding
    import androidx.compose.material3.Icon
    import androidx.compose.material3.NavigationBar
    import androidx.compose.material3.NavigationBarItem
    import androidx.compose.material3.NavigationBarItemDefaults
    import androidx.compose.material3.Scaffold
    import androidx.compose.material3.Text
    import androidx.compose.runtime.Composable
    import androidx.compose.runtime.collectAsState
    import androidx.compose.ui.Modifier
    import cafe.adriel.voyager.core.screen.Screen
    import cafe.adriel.voyager.navigator.tab.CurrentTab
    import cafe.adriel.voyager.navigator.tab.LocalTabNavigator
    import cafe.adriel.voyager.navigator.tab.TabNavigator
    import com.imperatorofdwelling.android.presentation.ui.favorites.FavoritesTab
    import com.imperatorofdwelling.android.presentation.ui.home_screen.HomeTab
    import com.imperatorofdwelling.android.presentation.ui.theme.DarkGrey
    import com.imperatorofdwelling.android.presentation.ui.theme.Transparent
    import com.imperatorofdwelling.android.presentation.ui.theme.White
    import com.imperatorofdwelling.android.presentation.ui.theme.h5
    import com.imperatorofdwelling.android.presentation.ui.user_profile.UserTab
    import org.koin.compose.koinInject

    class MainNavigation : Screen {

        @Composable
        override fun Content() {
            BottomNavigationMenu()
        }

        @Composable
        fun BottomNavigationMenu() {
            val navigationState = koinInject<NavigationModel>().state.collectAsState()
            TabNavigator(HomeTab) {
                Scaffold(
                    content = { contentPadding ->
                        Box(
                            modifier = Modifier
                                .padding(contentPadding)
                                .fillMaxSize()
                        ) {
                            CurrentTab()
                        }
                    },
                    bottomBar = {
                        AnimatedVisibility(navigationState.value.showBottomNavigation){
                            NavigationBar(
                                contentColor = Transparent,
                                containerColor = DarkGrey,
                                modifier = Modifier.fillMaxHeight(0.08f)
                            ) {
                                TabNavigationItem(HomeTab)
                                TabNavigationItem(FavoritesTab)
                                TabNavigationItem(UserTab)
                            }
                        }
                    }
                )
            }
        }

        @Composable
        private fun RowScope.TabNavigationItem(tab: TabImperatorOfDwelling) {
            val tabNavigator = LocalTabNavigator.current
            val isSelected = tabNavigator.current.key == tab.key
            NavigationBarItem(
                selected = tabNavigator.current.key == tab.key,
                onClick = { tabNavigator.current = tab },
                icon = {
                    if(isSelected){
                        Icon(
                            painter = tab.iconSelected(),
                            contentDescription = tab.options.title
                        )
                    } else {
                        Icon(
                            painter = tab.options.icon!!,
                            contentDescription = tab.options.title
                        )
                    }

                },
                label = {
                    Text(text = tab.options.title, style = h5)
                },
                colors = NavigationBarItemDefaults.colors(
                    selectedIconColor = White,
                    unselectedIconColor = White,
                    selectedTextColor = White,
                    unselectedTextColor = White,
                    indicatorColor = Transparent
                )
            )
        }
    }