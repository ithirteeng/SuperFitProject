package com.ithirteeng.superfitproject.common.utils

import android.util.Log
import com.ithirteeng.superfitproject.R
import com.ithirteeng.superfitproject.common.entity.ErrorEntity
import com.ithirteeng.superfitproject.common.network.utils.NoConnectivityException
import retrofit2.HttpException

object ErrorHelper {
    fun setupErrorEntity(e: Throwable, messageId: Int? = null): ErrorEntity {
        Log.e("ERROR", "happened: ", e)
        return when (e) {
            is NoConnectivityException -> ErrorEntity(
                exception = e,
                messageId = R.string.no_connectivity_exception,
                errorCode = NoConnectivityException.ERROR_CODE
            )

            is HttpException -> ErrorEntity(
                exception = e,
                messageId = messageId ?: R.string.error,
                errorCode = e.code()
            )

            else -> ErrorEntity(
                exception = Exception(e.message),
                messageId = messageId ?: R.string.error
            )
        }
    }
}