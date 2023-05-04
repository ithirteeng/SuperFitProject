package com.ithirteeng.superfitproject.common.exercises.di

import com.ithirteeng.superfitproject.common.exercises.data.repository.ExerciseRepositoryImpl
import com.ithirteeng.superfitproject.common.exercises.data.storage.ExerciseStorage
import com.ithirteeng.superfitproject.common.exercises.domain.repository.ExerciseRepository
import com.ithirteeng.superfitproject.common.exercises.domain.usecase.AddExerciseUseCase
import com.ithirteeng.superfitproject.common.exercises.domain.usecase.ClearExerciseStorageUseCase
import com.ithirteeng.superfitproject.common.exercises.domain.usecase.GetExercisesUseCase
import com.ithirteeng.superfitproject.common.exercises.domain.usecase.GetWeightAndHeightUseCase
import com.ithirteeng.superfitproject.common.exercises.domain.usecase.SetWeightAndHeightUseCase
import org.koin.dsl.module

val exercisesModule = module {
    single { ExerciseStorage(context = get()) }

    factory<ExerciseRepository> { ExerciseRepositoryImpl(storage = get()) }

    factory { GetExercisesUseCase(repository = get()) }
    factory { AddExerciseUseCase(repository = get()) }
    factory { ClearExerciseStorageUseCase(repository = get()) }
    factory { SetWeightAndHeightUseCase(repository = get()) }
    factory { GetWeightAndHeightUseCase(repository = get()) }
}