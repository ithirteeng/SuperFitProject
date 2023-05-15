package com.ithirteeng.superfitproject.common.exercises.data.api

import com.ithirteeng.superfitproject.common.exercises.domain.entity.TrainingEntity
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST

interface ExerciseApi {
    @POST("trainings")
    suspend fun saveTraining(@Body trainingEntity: TrainingEntity)

    @GET("trainings")
    suspend fun getTrainings(): List<TrainingEntity>

}