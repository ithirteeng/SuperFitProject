package com.ithirteeng.superfitproject.mybody.presentation

import androidx.lifecycle.ViewModel
import com.ithirteeng.superfitproject.common.exercises.domain.usecase.GetWeightAndHeightUseCase
import com.ithirteeng.superfitproject.common.exercises.domain.usecase.SetWeightAndHeightUseCase
import com.ithirteeng.superfitproject.mybody.domain.usecase.UpdateBodyParamsUseCase
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow

class MyBodyScreenViewModel(
    private val setWeightAndHeightUseCase: SetWeightAndHeightUseCase,
    private val getWeightAndHeightUseCase: GetWeightAndHeightUseCase,
    private val updateBodyParamsUseCase: UpdateBodyParamsUseCase,
) : ViewModel() {

    fun accept(myBodyScreenIntent: MyBodyScreenIntent) {
        when (myBodyScreenIntent) {
            is MyBodyScreenIntent.ChangeHeight -> {}
            is MyBodyScreenIntent.ChangeWeight -> {}
            is MyBodyScreenIntent.DismissError -> {}
            is MyBodyScreenIntent.Initial -> initState()
        }
    }

    private val _state = MutableStateFlow(MyBodyScreenState())

    private val state: StateFlow<MyBodyScreenState> = _state

    private fun initState() {
        _state.value = MyBodyScreenState()
    }


}