package ru.kirill.unscramblewords

import android.content.SharedPreferences

interface IntCache {
    fun save(newValue: Int)
    fun read(defaultValue: Int): Int
    class Base(val sharedPreferences: SharedPreferences, val name: String) : IntCache {
        override fun save(newValue: Int) {
            sharedPreferences.edit().putInt(name, newValue).apply()
        }

        override fun read(defaultValue: Int): Int {
            return sharedPreferences.getInt(name, defaultValue)
        }

    }
}
