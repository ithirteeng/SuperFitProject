package com.ithirteeng.superfitproject.common.photos.data.api

import com.ithirteeng.superfitproject.common.photos.domain.entity.ImageEntity
import okhttp3.MultipartBody
import okhttp3.ResponseBody
import retrofit2.http.GET
import retrofit2.http.Multipart
import retrofit2.http.POST
import retrofit2.http.Part
import retrofit2.http.Path

interface PhotosApi {
    @Multipart
    @POST("profile/photos")
    suspend fun uploadPhoto(@Part image: MultipartBody.Part): ImageEntity

    @GET("profile/photos")
    suspend fun getPhotos(): List<ImageEntity>

    @GET("profile/photos/{id}")
    suspend fun downloadPhoto(@Path("id") id: String): ResponseBody
}