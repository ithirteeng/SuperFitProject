package com.ithirteeng.superfitproject.common.token.data.storage

import android.content.Context
import androidx.security.crypto.EncryptedSharedPreferences
import androidx.security.crypto.MasterKeys
import com.ithirteeng.superfitproject.common.token.domain.entity.TokenEntity

class TokenStorage(
    context: Context,
) {
    companion object {
        const val TOKEN_STORAGE_NAME = "token storage name"
        const val ACCESS_TOKEN_KEY = "ACCESS_TOKEN_KEY"
        const val REFRESH_TOKEN_KEY = "REFRESH_TOKEN_KEY"
        const val USERNAME_KEY = "USERNAME_KEY"
        const val PASSWORD_KEY = "PASSWORD_KEY"
        const val CURRENT_USER_NAME_KEY = "CURRENT_USER_NAME_KEY"
        const val USER_ENTRY_FLAG_KEY = "USER_ENTRY_FLAG_KEY"
    }

    private val masterKeyAlias = MasterKeys.getOrCreate(MasterKeys.AES256_GCM_SPEC)

    private val sharedPreferences = EncryptedSharedPreferences.create(
        TOKEN_STORAGE_NAME,
        masterKeyAlias,
        context,
        EncryptedSharedPreferences.PrefKeyEncryptionScheme.AES256_SIV,
        EncryptedSharedPreferences.PrefValueEncryptionScheme.AES256_GCM
    )

    fun saveTokenEntity(tokenEntity: TokenEntity) {
        sharedPreferences.edit()
            .putString(USERNAME_KEY, tokenEntity.userName)
            .putString(PASSWORD_KEY, tokenEntity.password)
            .putString(ACCESS_TOKEN_KEY, tokenEntity.accessToken)
            .putString(REFRESH_TOKEN_KEY, tokenEntity.refreshToken)
            .apply()
    }

    fun getTokenEntity(): TokenEntity? {
        return if (sharedPreferences.getString(PASSWORD_KEY, null) == null) {
            null
        } else {
            TokenEntity(
                userName = sharedPreferences.getString(USERNAME_KEY, null).toString(),
                password = sharedPreferences.getString(PASSWORD_KEY, null).toString(),
                accessToken = sharedPreferences.getString(ACCESS_TOKEN_KEY, null).toString(),
                refreshToken = sharedPreferences.getString(REFRESH_TOKEN_KEY, null).toString()
            )
        }
    }

    fun clearTokenStorage() {
        sharedPreferences.edit()
            .remove(USERNAME_KEY)
            .remove(PASSWORD_KEY)
            .remove(ACCESS_TOKEN_KEY)
            .remove(REFRESH_TOKEN_KEY)
            .remove(CURRENT_USER_NAME_KEY)
            .apply()
    }

    fun saveCurrentUserName(userName: String) =
        sharedPreferences.edit().putString(CURRENT_USER_NAME_KEY, userName).apply()


    fun getCurrentUserName(): String? =
        sharedPreferences.getString(CURRENT_USER_NAME_KEY, null)

    fun removeCurrentUserName() =
        sharedPreferences.edit().remove(CURRENT_USER_NAME_KEY).apply()

    fun setUserEntryFlag(flag: Boolean) =
        sharedPreferences.edit().putBoolean(USER_ENTRY_FLAG_KEY, flag).apply()

    fun getUserEntryFlag(): Boolean =
        sharedPreferences.getBoolean(USER_ENTRY_FLAG_KEY, false)
}