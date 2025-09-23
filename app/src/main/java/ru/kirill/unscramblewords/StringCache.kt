package ru.kirill.unscramblewords

import android.content.SharedPreferences

interface StringCache {
    fun read(): String
    fun save(newValue: String)

    class Base(val sharedPreferences: SharedPreferences, val name: String) : StringCache {
        override fun read(): String {
            return sharedPreferences.getString(name, "") ?: ""
        }

        override fun save(newValue: String) {
            sharedPreferences.edit().putString(name, newValue).apply()
        }

    }
}