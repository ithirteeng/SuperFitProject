package com.ithirteeng.superfitproject.mybody.domain.repository

import com.ithirteeng.superfitproject.mybody.domain.entity.BodyParamsEntity

interface MyBodyRepository {
    suspend fun updateParams(bodyParamsEntity: BodyParamsEntity)
}