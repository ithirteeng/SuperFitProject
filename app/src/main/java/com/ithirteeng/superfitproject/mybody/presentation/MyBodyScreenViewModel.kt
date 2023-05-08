package com.ithirteeng.superfitproject.mybody.presentation

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import com.ithirteeng.superfitproject.R
import com.ithirteeng.superfitproject.common.exercises.domain.usecase.GetWeightAndHeightUseCase
import com.ithirteeng.superfitproject.common.exercises.domain.usecase.SetWeightAndHeightUseCase
import com.ithirteeng.superfitproject.mybody.domain.usecase.UpdateBodyParamsUseCase
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow

class MyBodyScreenViewModel(
    private val application: Application,
    private val setWeightAndHeightUseCase: SetWeightAndHeightUseCase,
    private val getWeightAndHeightUseCase: GetWeightAndHeightUseCase,
    private val updateBodyParamsUseCase: UpdateBodyParamsUseCase,
) : AndroidViewModel(application) {

    fun accept(myBodyScreenIntent: MyBodyScreenIntent) {
        when (myBodyScreenIntent) {
            is MyBodyScreenIntent.ChangeHeight -> {}
            is MyBodyScreenIntent.ChangeWeight -> {}
            is MyBodyScreenIntent.DismissError -> {}
            is MyBodyScreenIntent.Initial -> initState()
        }
    }

    private val _state = MutableStateFlow(MyBodyScreenState())

    val state: StateFlow<MyBodyScreenState> = _state

    private fun initState() {
        _state.value = MyBodyScreenState(
            weight = getWeightAndHeight().first,
            height = getWeightAndHeight().second
        )
    }

    private fun getWeightAndHeight(): Pair<String, String> {
        val weightAndHeight = getWeightAndHeightUseCase()
        var first = weightAndHeight.first
        var second = weightAndHeight.second
        if (first == null) first = application.resources.getString(R.string.undefined)
        if (second == null) second = application.resources.getString(R.string.undefined)
        return Pair(first, second)
    }


}