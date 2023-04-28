package com.ithirteeng.superfitproject.common.network.utils

import java.io.IOException

class NoConnectivityException : IOException() {

    companion object {
        const val ERROR_CODE = 1309
    }

    fun code(): Int = ERROR_CODE

    override val message: String
        get() = "No Internet Connection"
}