package com.app.mymainapp.preferences

import android.content.SharedPreferences
import androidx.core.content.edit
import javax.inject.Inject


/** Created by Jishnu P Dileep on 26-05-2021 */

class PreferenceHandler @Inject constructor(
    private val sharedPreferences: SharedPreferences
) {

    var userToken: String
        get() = sharedPreferences.getString("token", "") ?: ""
        set(value) = sharedPreferences.edit { putString("token", value) }

    var isUserLogin: Boolean
        get() = sharedPreferences.getBoolean("isUserLogin",false) ?: false
        set(value) = sharedPreferences.edit { putBoolean("isUserLogin", value) }
}