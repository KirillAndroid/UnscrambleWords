package ru.kirill.unscramblewords

import ru.kirill.unscramblewords.databinding.ActivityMainBinding

interface GameUiState {
    fun update(binding: ActivityMainBinding): Unit = throw IllegalStateException("Not implemented")

    data class Initial(val unscrambleWord: String) : GameUiState {

    }

    data class InputVariant(val unscrambleWord: String, val userInput: String, val isCheckAvailable: Boolean) :
        GameUiState {

    }

    data class Correct(
        val unscrambleWord: String,
        val isNextAvailable: Boolean,
        val isCheckAvailable: Boolean,
        val isSkipAvailable: Boolean
    ) : GameUiState {

    }

    data class Incorrect(
        val unscrambleWord: String,
        val isNextAvailable: Boolean,
        val isCheckAvailable: Boolean,
        val isSkipAvailable: Boolean
    ) : GameUiState {

    }

}
