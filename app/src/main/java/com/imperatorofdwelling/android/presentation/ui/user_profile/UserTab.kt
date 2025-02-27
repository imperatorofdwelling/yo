package com.imperatorofdwelling.android.presentation.ui.user_profile

import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import cafe.adriel.voyager.navigator.Navigator
import cafe.adriel.voyager.navigator.tab.TabOptions
import cafe.adriel.voyager.transitions.SlideTransition
import com.imperatorofdwelling.android.R
import com.imperatorofdwelling.android.presentation.ui.navigation.TabImperatorOfDwelling
import com.imperatorofdwelling.android.presentation.ui.theme.animation.slideAnimationDefault

object UserTab : TabImperatorOfDwelling {
    override val iconSelected: @Composable () -> Painter = {
        painterResource(id = R.drawable.user_filled)
    }

    private fun readResolve(): Any = UserTab
    override val options: TabOptions
        @Composable
        get() {
            val title = stringResource(R.string.profile)
            val icon = painterResource(id = R.drawable.profile)
            return remember {
                TabOptions(
                    index = 2u,
                    title = title,
                    icon = icon
                )
            }
        }

    @Composable
    override fun Content() {
        Navigator(UserProfile()) { navigator ->
            SlideTransition(
                navigator,
                animationSpec = slideAnimationDefault()
            )
        }
    }
}