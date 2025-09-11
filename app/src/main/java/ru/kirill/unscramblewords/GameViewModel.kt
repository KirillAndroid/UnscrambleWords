package ru.kirill.unscramblewords

class GameViewModel(private val repository: GameRepository) {
    fun next(): GameUiState {
        return GameUiState.Initial(repository.getNextWord().unscrambleWord)
    }

    fun skip(): GameUiState {
        return GameUiState.Initial(repository.getNextWord().unscrambleWord)
    }

    fun check(text: String): GameUiState {
        val unscrambleWord = repository.getCurrentWord()
        if (unscrambleWord.answer == text)
            return GameUiState.Correct(unscrambleWord.unscrambleWord, true, false, false)
        else
            return GameUiState.Incorrect(unscrambleWord.unscrambleWord, false, true, true)
    }

    fun handleUserInput(text: String): GameUiState {
        val unscrambleWord = repository.getCurrentWord()
        if (text.length == unscrambleWord.unscrambleWord.length)
            return GameUiState.InputVariant(unscrambleWord.unscrambleWord, text, true)
        else
            return GameUiState.InputVariant(unscrambleWord.unscrambleWord, text, false)
    }

    fun init(): GameUiState {
        return GameUiState.Initial(repository.getCurrentWord().unscrambleWord)
    }

}
