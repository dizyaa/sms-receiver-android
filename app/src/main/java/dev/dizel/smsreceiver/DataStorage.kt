package dev.dizel.smsreceiver

import android.content.Context

class DataStorage(context: Context) {
    private val sharedPreferences = context.getSharedPreferences("DATA", Context.MODE_PRIVATE)

    fun saveUserId(userId: String) {
        sharedPreferences.edit()
            .putString("USER_ID", userId)
            .apply()
    }

    fun getUserId(): String? {
        return sharedPreferences.getString("USER_ID", null)
    }

    fun saveToken(token: String) {
        sharedPreferences.edit()
            .putString("TOKEN", token)
            .apply()
    }

    fun getToken(): String? {
        return sharedPreferences.getString("TOKEN", null)
    }
}
