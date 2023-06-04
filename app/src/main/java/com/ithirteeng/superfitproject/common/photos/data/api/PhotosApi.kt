package com.ithirteeng.superfitproject.common.photos.data.api

import com.ithirteeng.superfitproject.common.photos.domain.entity.ImageEntity
import okhttp3.MultipartBody
import retrofit2.http.GET
import retrofit2.http.Multipart
import retrofit2.http.POST
import retrofit2.http.Part

interface PhotosApi {
    @Multipart
    @POST("profile/photos")
    suspend fun uploadPhoto(@Part image: MultipartBody.Part): ImageEntity

    @GET("profile/photos")
    suspend fun getPhotos(): List<ImageEntity>
}