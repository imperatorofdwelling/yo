package com.imperatorofdwelling.android.presentation.ui.apart_detail

import com.imperatorofdwelling.android.presentation.entities.amenityListMock
import com.imperatorofdwelling.android.presentation.entities.dwelling.Amenity
import com.imperatorofdwelling.android.presentation.ui.common.BaseViewModel

class ApartDetailViewModel : BaseViewModel<ApartDetailViewModel.State>(State()) {


    data class State(
        val amenityList: List<Amenity> = amenityListMock,
        val description: String = ""
    )
}