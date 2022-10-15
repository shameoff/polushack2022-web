package com.example.polushackhatonproject.data.storage

import android.content.Context
import androidx.security.crypto.EncryptedSharedPreferences
import androidx.security.crypto.MasterKeys
import com.example.polushackhatonproject.domain.signup.model.UserCredits

class SharedPreferencesStorage(context: Context) : UserCreditsStorage {

    companion object {
        const val SHARED_PREFS_NAME = "encryptedSharedPreferences"
    }

    private val masterKeysAlias = MasterKeys.getOrCreate(MasterKeys.AES256_GCM_SPEC)

    private val sharedPreferences = EncryptedSharedPreferences.create(
        SHARED_PREFS_NAME,
        masterKeysAlias,
        context,
        EncryptedSharedPreferences.PrefKeyEncryptionScheme.AES256_SIV,
        EncryptedSharedPreferences.PrefValueEncryptionScheme.AES256_GCM
    )

    override fun getUserCredits(): UserCredits {
        return UserCredits(
            sharedPreferences.getString(
                UserCreditsStorage.EMAIL_KEY,
                UserCreditsStorage.EMPTINESS_CASE
            ).toString(),
            sharedPreferences.getString(
                UserCreditsStorage.PASSWORD_KEY,
                UserCreditsStorage.EMPTINESS_CASE
            ).toString()
        )
    }

    override fun ifAuthenticationPassed(): Boolean {
        val result = sharedPreferences.getString(
            UserCreditsStorage.EMAIL_KEY,
            UserCreditsStorage.EMPTINESS_CASE
        )
        return result != UserCreditsStorage.EMPTINESS_CASE
    }

    override fun saveUserCredits(userCredits: UserCredits) {
        sharedPreferences.edit()
            .putString(UserCreditsStorage.EMAIL_KEY, userCredits.email)
            .putString(UserCreditsStorage.PASSWORD_KEY, userCredits.password)
            .apply()
    }

    override fun saveToken(token: String) {
        sharedPreferences.edit()
            .putString(UserCreditsStorage.TOKEN_KEY, token)
            .apply()
    }

    override fun getToken(): String {
        return sharedPreferences.getString(
            UserCreditsStorage.TOKEN_KEY,
            UserCreditsStorage.EMPTINESS_CASE
        ).toString()
    }


}