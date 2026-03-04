package com.example.apiappactividad.data.RecyclerView

import android.R
import android.content.Context
import android.content.SharedPreferences

class SettingsRepository(context: Context) {
    private val sharedPreferences: SharedPreferences =
        context.getSharedPreferences("AppSettings", Context.MODE_PRIVATE)

    fun guardarMode(modeDark: Boolean) {
        sharedPreferences.edit().putBoolean("mode_dark", modeDark).apply()
    }

    fun obtenerMode(): Boolean {
        return sharedPreferences.getBoolean("mode_dark", false)
    }

    fun guardarShowMode(showMode: String ){
        sharedPreferences.edit().putString("show_mode", showMode).apply()
    }
    fun obtenerShowMode(): String {
        return  sharedPreferences.getString("show_mode", "List") ?: "Grid"
    }
    fun guardarFlagMode(flag: Boolean ){
        sharedPreferences.edit().putBoolean("show_flag", flag).apply()
    }
    fun obtenerFlagMode(): Boolean {
        return  sharedPreferences.getBoolean("show_flag", false)
    }

}