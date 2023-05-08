package com.ithirteeng.superfitproject.mybody.data.repository

import com.ithirteeng.superfitproject.mybody.data.api.MyBodyApi
import com.ithirteeng.superfitproject.mybody.domain.entity.BodyParamsEntity
import com.ithirteeng.superfitproject.mybody.domain.repository.MyBodyRepository

class MyBodyRepositoryImpl(
    private val api: MyBodyApi,
) : MyBodyRepository {
    override suspend fun updateParams(bodyParamsEntity: BodyParamsEntity) {
        api.updateParams(bodyParamsEntity = bodyParamsEntity)
    }
}