package com.imperatorofdwelling.android.presentation.ui.home_screen.components

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.IntOffset
import com.imperatorofdwelling.android.domain.locations.entities.City
import com.imperatorofdwelling.android.presentation.ui.components.SmallSpacer
import com.imperatorofdwelling.android.presentation.ui.theme.animation.animationSpecSlowly
import com.imperatorofdwelling.android.presentation.ui.theme.extraLargeDp


@Composable
fun CitySelection(
    searchResults: List<City?>,
    defaultCity: City?,
    onCityClick: (City) -> Unit,
    modifier: Modifier = Modifier
) {
    Column(
        modifier = modifier
            .fillMaxSize()
            .padding(horizontal = extraLargeDp)
    ) {
        SmallSpacer()
        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
        ) {
            if (defaultCity != null) {
                item {
                    CityItem(
                        name = defaultCity.city,
                        isDefault = true,
                        modifier = Modifier.clickable { onCityClick(defaultCity) })
                }
            }
            items(
                searchResults,
                key = {
                    it?.toString() ?: ""
                }
            ) { cityItem ->
                if (cityItem != null && cityItem.city != defaultCity?.city) {
                    CityItem(
                        name = cityItem.city,
                        isDefault = false,
                        modifier = Modifier
                            .clickable {
                                onCityClick(cityItem)
                            }
                            .animateItem(
                                fadeInSpec = animationSpecSlowly<Float>(),
                                placementSpec = animationSpecSlowly<IntOffset>(),
                                fadeOutSpec = animationSpecSlowly<Float>()
                            )
                    )
                }
            }
        }
    }
}
