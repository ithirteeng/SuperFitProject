package com.ithirteeng.superfitproject.main.presentation

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow

class MainScreenViewModel(
    private val application: Application,
) : AndroidViewModel(application) {

    fun accept(mainScreenIntent: MainScreenIntent) {

    }

    private val _state = MutableStateFlow(MainScreenState())

    val state: StateFlow<MainScreenState> = _state


}