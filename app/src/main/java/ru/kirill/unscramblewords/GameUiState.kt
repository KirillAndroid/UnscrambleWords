package ru.kirill.unscramblewords

import androidx.core.view.isVisible
import ru.kirill.unscramblewords.databinding.ActivityMainBinding

interface GameUiState {
    fun update(binding: ActivityMainBinding)

    data class Initial(val unscrambleWord: String) : GameUiState {
        override fun update(binding: ActivityMainBinding) {
            binding.unscrambleWordTextView.text = unscrambleWord
            binding.textInputEditText.setText("")
            binding.textInputEditText.isEnabled = true

            binding.checkButton.isEnabled = false

            binding.nextButton.isVisible = false
            binding.nextButton.isEnabled = false

            binding.skipButton.isVisible = true
            binding.skipButton.isEnabled = true

            binding.failTextView.isVisible = false
            binding.correctTextView.isVisible = false
        }

    }

    data class InputVariant(val unscrambleWord: String, val userInput: String, val isCheckAvailable: Boolean) :
        GameUiState {
        override fun update(binding: ActivityMainBinding) {
            binding.unscrambleWordTextView.text = unscrambleWord
            if (binding.textInputEditText.text.toString() != userInput) {
                binding.textInputEditText.setText(userInput)
            }
            binding.textInputEditText.isEnabled = true

            binding.checkButton.isEnabled = isCheckAvailable

            binding.nextButton.isVisible = false
            binding.nextButton.isEnabled = false

            binding.skipButton.isVisible = true
            binding.skipButton.isEnabled = true

            binding.failTextView.isVisible = false
            binding.correctTextView.isVisible = false
        }

    }

    data class Correct(
        val unscrambleWord: String,
        val answer: String,
        val isNextAvailable: Boolean,
        val isCheckAvailable: Boolean,
        val isSkipAvailable: Boolean,
    ) : GameUiState {
        override fun update(binding: ActivityMainBinding) {
            binding.unscrambleWordTextView.text = unscrambleWord
            if (binding.textInputEditText.text.toString() != answer) {
                binding.textInputEditText.setText(answer)
            }
            binding.textInputEditText.isEnabled = true

            binding.checkButton.isEnabled = isCheckAvailable

            binding.nextButton.isVisible = true
            binding.nextButton.isEnabled = isNextAvailable

            binding.skipButton.isVisible = false
            binding.skipButton.isEnabled = isSkipAvailable

            binding.correctTextView.isVisible = true

        }

    }

    data class Incorrect(
        val unscrambleWord: String,
        val answer: String,
        val isNextAvailable: Boolean,
        val isCheckAvailable: Boolean,
        val isSkipAvailable: Boolean
    ) : GameUiState {
        override fun update(binding: ActivityMainBinding) {
            binding.unscrambleWordTextView.text = unscrambleWord
            if (binding.textInputEditText.text.toString() != answer) {
                binding.textInputEditText.setText(answer)
            }
            binding.textInputEditText.isEnabled = true

            binding.checkButton.isEnabled = isCheckAvailable

            binding.nextButton.isVisible = false
            binding.nextButton.isEnabled = isNextAvailable

            binding.skipButton.isVisible = true
            binding.skipButton.isEnabled = isSkipAvailable

            binding.failTextView.isVisible = true
        }

    }

}
