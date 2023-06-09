package com.ithirteeng.superfitproject.statistics.presentation

import androidx.lifecycle.ViewModel
import com.ithirteeng.superfitproject.common.exercises.domain.usecase.GetTrainingsUseCase
import com.ithirteeng.superfitproject.statistics.domain.usecase.GetBodyParamsHistoryUseCase
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow

class StatisticsViewModel(
    private val getBodyParamsHistoryUseCase: GetBodyParamsHistoryUseCase,
    private val getTrainingsUseCase: GetTrainingsUseCase,
) : ViewModel() {

    fun accept(intent: StatisticsIntent) {
        when (intent) {
            is StatisticsIntent.BackButtonClick -> TODO()
            is StatisticsIntent.Initial -> TODO()
            is StatisticsIntent.TrainingTypeButtonClick -> TODO()
        }
    }

    private val _state = MutableStateFlow(StatisticsState())
    val state: StateFlow<StatisticsState> = _state
}