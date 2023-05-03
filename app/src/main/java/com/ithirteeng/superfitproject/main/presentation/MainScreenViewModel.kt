package com.ithirteeng.superfitproject.main.presentation

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow

class MainScreenViewModel : ViewModel() {

    fun accept(mainScreenIntent: MainScreenIntent) {

    }

    private val _state = MutableStateFlow(MainScreenState())

    val state: StateFlow<MainScreenState> = _state


}