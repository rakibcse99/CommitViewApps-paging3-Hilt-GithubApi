package com.rakib.commitviewapp.utils

import android.content.Context
import android.content.SharedPreferences
import com.rakib.commitviewapp.utils.Constants.PREFS_TOKEN_FILE
import com.rakib.commitviewapp.utils.Constants.USER_TOKEN
import dagger.hilt.android.qualifiers.ApplicationContext
import javax.inject.Inject

class TokenManager @Inject constructor(@ApplicationContext context: Context) {
    private var prefs: SharedPreferences =
        context.getSharedPreferences(PREFS_TOKEN_FILE, Context.MODE_PRIVATE)

    fun saveToken(token: String) {
        val editor = prefs.edit()
        editor.putString(USER_TOKEN, token)
        editor.apply()
    }

    fun getToken(): String? {
        return prefs.getString(USER_TOKEN, "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")
    }
}