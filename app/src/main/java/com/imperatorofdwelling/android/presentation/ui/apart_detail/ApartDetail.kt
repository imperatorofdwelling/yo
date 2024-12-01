package com.imperatorofdwelling.android.presentation.ui.apart_detail

import androidx.compose.foundation.Image
import androidx.compose.foundation.gestures.snapping.SnapPosition
import androidx.compose.foundation.gestures.snapping.rememberSnapFlingBehavior
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import cafe.adriel.voyager.core.screen.Screen
import com.imperatorofdwelling.android.R
import com.imperatorofdwelling.android.presentation.entities.Review
import com.imperatorofdwelling.android.presentation.entities.amenityListMock
import com.imperatorofdwelling.android.presentation.entities.dwelling.Amenity
import com.imperatorofdwelling.android.presentation.entities.dwelling.Apartment
import com.imperatorofdwelling.android.presentation.entities.reviewListMock
import com.imperatorofdwelling.android.presentation.ui.apart_detail.components.AmenityCard
import com.imperatorofdwelling.android.presentation.ui.apart_detail.components.MapPoint
import com.imperatorofdwelling.android.presentation.ui.apart_detail.components.ProfileCard
import com.imperatorofdwelling.android.presentation.ui.apart_detail.components.ProgressBar
import com.imperatorofdwelling.android.presentation.ui.apart_detail.components.Review
import com.imperatorofdwelling.android.presentation.ui.components.ExtraLargeSpacer
import com.imperatorofdwelling.android.presentation.ui.components.LargeSpacer
import com.imperatorofdwelling.android.presentation.ui.components.Mark
import com.imperatorofdwelling.android.presentation.ui.components.OpenStreetMap
import com.imperatorofdwelling.android.presentation.ui.components.SmallSpacer
import com.imperatorofdwelling.android.presentation.ui.components.TextExpended
import com.imperatorofdwelling.android.presentation.ui.components.TypePlate
import com.imperatorofdwelling.android.presentation.ui.components.buttons.BackButton
import com.imperatorofdwelling.android.presentation.ui.components.buttons.LikeButton
import com.imperatorofdwelling.android.presentation.ui.components.buttons.ShareButton
import com.imperatorofdwelling.android.presentation.ui.theme.GreyDividerColor
import com.imperatorofdwelling.android.presentation.ui.theme.White
import com.imperatorofdwelling.android.presentation.ui.theme.extraLargeDp
import com.imperatorofdwelling.android.presentation.ui.theme.forButtons16dp
import com.imperatorofdwelling.android.presentation.ui.theme.h2
import com.imperatorofdwelling.android.presentation.ui.theme.h3
import com.imperatorofdwelling.android.presentation.ui.theme.h4_grey
import com.imperatorofdwelling.android.presentation.ui.theme.largeDp
import com.imperatorofdwelling.android.presentation.ui.theme.mediumDp
import com.imperatorofdwelling.android.presentation.ui.theme.smallDp
import org.osmdroid.util.GeoPoint

@Composable
@Preview
fun Preview() {
    ApartDetail().Content()
}


class ApartDetail : Screen {

    @Composable
    override fun Content() {
        //val viewModel = koinViewModel<ApartDetailViewModel>()
        //val state = viewModel.state.collectAsState()
        ApartDetailBody(
//            amenityList = state.value.amenityList
            amenityList = amenityListMock,
            reviews = reviewListMock,
            residentsText = "aboba",
            description = """Lorem ipsum dolor sit emet LoremLorem ipsum dolor sit emet Lorem
                 ipsum dolor sit emet Lorem ipsum dolor sit emet Lorem ipsum dolor sit emet Lorem
                 ipsum dolor sit emet Lorem ipsum dolor sit emet 
                 Lorem ipsum dolor sit emet Lorem ipsum dolor sit emet
                 Lorem ipsum dolor sit emet Lorem ipsum dolor sit emetLorem ipsum dolor sit emet Lorem
                 ipsum dolor sit emet Lorem ipsum dolor sit emet 
                 Lorem ipsum dolor sit emet Lorem ipsum dolor sit emet
                 ipsum dolor sit emet Lorem ipsum dolor sit emet 
                 Lorem ipsum dolor sit emet Lorem ipsum dolor sit emet""".trimIndent(),
            mark = 4.5,
            manufacturability = 3.9,
            photoAccuracy = 4.8,
            comfort = 4.9
        )
    }

    @Composable
    fun ApartDetailBody(
        amenityList: List<Amenity>,
        reviews: List<Review>,
        residentsText: String,
        description: String,
        mark: Double?,
        manufacturability: Double?,
        photoAccuracy: Double?,
        comfort: Double?
    ) {

        val scrollState = rememberScrollState()

        Column(
            modifier = Modifier.verticalScroll(scrollState)
        ) {
            Box {
                Image(
                    painterResource(id = R.drawable.example_hotel_image),
                    contentDescription = null,
                    contentScale = ContentScale.Crop,
                    modifier = Modifier
                        .height(270.dp)
                        .fillMaxWidth()
                )
                Row(
                    horizontalArrangement = Arrangement.SpaceBetween,
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(start = largeDp, end = largeDp, top = mediumDp)
                ) {
                    BackButton(onClick = { /*TODO*/ })
                    Row {
                        ShareButton(onClick = { /*TODO*/ })
                        LikeButton(onClick = { /*TODO*/ })
                    }
                }
            }
            Column(
                modifier = Modifier.padding(
                    start = largeDp, end = largeDp, top = extraLargeDp
                )
            ) {
                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.SpaceBetween,
                    modifier = Modifier.fillMaxWidth()
                ) {
                    TypePlate(type = Apartment)
                    Mark(
                        mark ?: 0.0, style = h3, color = White
                    )
                }
                Text(
                    text = stringResource(R.string.example_name_hotel),
                    style = h2,
                    modifier = Modifier.padding(top = 16.dp, start = 4.dp)
                )

                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    modifier = Modifier.padding(start = 4.dp, top = 2.dp)
                ) {
                    Image(
                        painter = painterResource(R.drawable.point), contentDescription = null
                    )
                    Spacer(modifier = Modifier.width(6.dp))
                    Text(
                        text = stringResource(R.string.example_address), style = h4_grey
                    )
                }
                Spacer(modifier = Modifier.height(extraLargeDp))
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Text(text = stringResource(R.string.amenities), style = h2)
                    Text(text = stringResource(R.string.see_details), style = forButtons16dp)
                }

                Spacer(modifier = Modifier.height(smallDp))

                LazyRow {
                    items(amenityList) { item ->
                        AmenityCard(amenity = item)
                        Spacer(modifier = Modifier.width(8.dp))
                    }
                }
                Spacer(modifier = Modifier.height(extraLargeDp))


                Text(text = stringResource(id = R.string.residents), style = h2)

                Spacer(modifier = Modifier.height(smallDp))

                Text(text = residentsText, style = h4_grey)

                Spacer(modifier = Modifier.height(extraLargeDp))


                Text(text = stringResource(R.string.description), style = h2)

                Spacer(modifier = Modifier.height(smallDp))

                TextExpended(text = description, collapsedLength = 200)

                ExtraLargeSpacer()

                ProfileCard(onClickChatButton = { /*TODO*/ })

                Spacer(modifier = Modifier.height(24.dp))

                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(170.dp),
                    contentAlignment = Alignment.Center
                ) {
                    OpenStreetMap(
                        geoPointCenter = GeoPoint(52.2978, 104.296)
                    )
                    MapPoint(dwellingType = Apartment)
                }

                ExtraLargeSpacer()
            }
            HorizontalDivider(thickness = 0.5.dp, color = GreyDividerColor)
            Spacer(modifier = Modifier.height(extraLargeDp))
            Column(
                modifier = Modifier.padding(
                    start = largeDp, end = largeDp
                )
            ) {
                Row(
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically,
                    modifier = Modifier.fillMaxWidth()
                ) {
                    Text(text = stringResource(R.string.rating), style = h2)
                    Row {
                        Mark(
                            mark = mark ?: 0.0,
                            color = White,
                            style = h3
                        )
                    }
                }
                LargeSpacer()

                HorizontalDivider(thickness = 0.5.dp, color = GreyDividerColor)

                LargeSpacer()
                Column(
                    modifier = Modifier.fillMaxWidth()
                ) {
                    Row(
                        horizontalArrangement = Arrangement.SpaceBetween,
                        verticalAlignment = Alignment.CenterVertically,
                        modifier = Modifier.fillMaxWidth()
                    ) {
                        Text(text = stringResource(R.string.manufacturability), style = h2)
                        Row {
                            Text(
                                text = manufacturability?.toString() ?: "0.0",
                                color = White,
                                style = h3
                            )
                        }
                    }
                    SmallSpacer()
                    ProgressBar(progress = (manufacturability?.toFloat() ?: 0f) / 5f)

                }

                LargeSpacer()
                Column(
                    modifier = Modifier.fillMaxWidth()
                ) {
                    Row(
                        horizontalArrangement = Arrangement.SpaceBetween,
                        verticalAlignment = Alignment.CenterVertically,
                        modifier = Modifier.fillMaxWidth()
                    ) {
                        Text(text = stringResource(R.string.photo_accuracy), style = h2)
                        Row {
                            Text(
                                text = photoAccuracy?.toString() ?: "0.0",
                                color = White,
                                style = h3
                            )
                        }
                    }
                    SmallSpacer()
                    ProgressBar(progress = (photoAccuracy?.toFloat() ?: 0f) / 5f)

                }
                LargeSpacer()
                Column(
                    modifier = Modifier.fillMaxWidth()
                ) {
                    Row(
                        horizontalArrangement = Arrangement.SpaceBetween,
                        verticalAlignment = Alignment.CenterVertically,
                        modifier = Modifier.fillMaxWidth()
                    ) {
                        Text(text = stringResource(R.string.comfort), style = h2)
                        Row {
                            Text(
                                text = comfort?.toString() ?: "0.0",
                                color = White,
                                style = h3
                            )
                        }
                    }

                    SmallSpacer()
                    ProgressBar(progress = (comfort?.toFloat() ?: 0f) / 5f)

                }

                ExtraLargeSpacer()

                ExtraLargeSpacer()

            }

            val lazyListState = rememberLazyListState()
            LazyRow(
                state = lazyListState,
                flingBehavior = rememberSnapFlingBehavior(
                    lazyListState = lazyListState,
                    snapPosition = SnapPosition.Center
                ),
                modifier = Modifier.fillMaxWidth()
            ) {
                itemsIndexed(reviews) { index, item ->
                    if(index == 0){
                        Spacer(modifier = Modifier.width(largeDp))
                    }
                    Review(
                        review = item,
                        modifier = Modifier.width(280.dp)
                    )
                    Spacer(modifier = Modifier.width(largeDp))
                }
            }
        }
    }
}