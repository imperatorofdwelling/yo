package com.imperatorofdwelling.android.presentation.ui

import androidx.lifecycle.viewModelScope
import com.imperatorofdwelling.android.domain.user.usecases.IsRegisteredUseCase
import com.imperatorofdwelling.android.presentation.ui.common.BaseViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class MainViewModel(
    val isRegisteredUseCase: IsRegisteredUseCase
): BaseViewModel<MainViewModel.State>(State()) {

    init{
        initIsSkipRegistrationState()
    }

    private fun initIsSkipRegistrationState() {
        var res = false
        viewModelScope.launch(Dispatchers.IO) {
            res = isRegisteredUseCase()
        }.invokeOnCompletion {
            _state.update{
                it.copy(isAuthSkip = res)
            }
        }
    }

    data class State(
        val isAuthSkip: Boolean = true
    )
}