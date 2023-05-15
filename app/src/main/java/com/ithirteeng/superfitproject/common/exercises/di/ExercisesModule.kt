package com.ithirteeng.superfitproject.common.exercises.di

import com.ithirteeng.superfitproject.common.exercises.data.repository.ExerciseRepositoryImpl
import com.ithirteeng.superfitproject.common.exercises.data.storage.ExerciseStorage
import com.ithirteeng.superfitproject.common.exercises.domain.repository.ExerciseRepository
import com.ithirteeng.superfitproject.common.exercises.domain.usecase.AddExerciseUseCase
import com.ithirteeng.superfitproject.common.exercises.domain.usecase.ClearExerciseStorageUseCase
import com.ithirteeng.superfitproject.common.exercises.domain.usecase.GetCrunchesAmountUseCase
import com.ithirteeng.superfitproject.common.exercises.domain.usecase.GetExercisesUseCase
import com.ithirteeng.superfitproject.common.exercises.domain.usecase.GetPlankAmountUseCase
import com.ithirteeng.superfitproject.common.exercises.domain.usecase.GetPushUpsAmountUseCase
import com.ithirteeng.superfitproject.common.exercises.domain.usecase.GetRunningAmountUseCase
import com.ithirteeng.superfitproject.common.exercises.domain.usecase.GetSquatsAmountUseCase
import com.ithirteeng.superfitproject.common.exercises.domain.usecase.GetWeightAndHeightUseCase
import com.ithirteeng.superfitproject.common.exercises.domain.usecase.SetCrunchesAmountUseCase
import com.ithirteeng.superfitproject.common.exercises.domain.usecase.SetHeightUseCase
import com.ithirteeng.superfitproject.common.exercises.domain.usecase.SetPlankAmountUseCase
import com.ithirteeng.superfitproject.common.exercises.domain.usecase.SetPushUpsAmountUseCase
import com.ithirteeng.superfitproject.common.exercises.domain.usecase.SetRunningAmountUseCase
import com.ithirteeng.superfitproject.common.exercises.domain.usecase.SetSquatsAmountUseCase
import com.ithirteeng.superfitproject.common.exercises.domain.usecase.SetWeightUseCase
import org.koin.dsl.module

val exercisesModule = module {
    single { ExerciseStorage(context = get()) }

    factory<ExerciseRepository> { ExerciseRepositoryImpl(storage = get()) }

    factory { GetExercisesUseCase(repository = get()) }
    factory { AddExerciseUseCase(repository = get()) }
    factory { ClearExerciseStorageUseCase(repository = get()) }
    factory { GetWeightAndHeightUseCase(repository = get()) }
    factory { SetHeightUseCase(repository = get()) }
    factory { SetWeightUseCase(repository = get()) }

    factory { GetPlankAmountUseCase(repository = get()) }
    factory { SetPlankAmountUseCase(repository = get()) }

    factory { GetPushUpsAmountUseCase(repository = get()) }
    factory { SetPushUpsAmountUseCase(repository = get()) }

    factory { GetSquatsAmountUseCase(repository = get()) }
    factory { SetSquatsAmountUseCase(repository = get()) }

    factory { GetCrunchesAmountUseCase(repository = get()) }
    factory { SetCrunchesAmountUseCase(repository = get()) }

    factory { GetRunningAmountUseCase(repository = get()) }
    factory { SetRunningAmountUseCase(repository = get()) }
}