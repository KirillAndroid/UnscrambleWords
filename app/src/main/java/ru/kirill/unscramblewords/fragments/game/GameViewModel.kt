package ru.kirill.unscramblewords.fragments.game

import ru.kirill.unscramblewords.di.MyViewModel
import ru.kirill.unscramblewords.fragments.stats.StatsRepository

class GameViewModel(private val repository: GameRepository, private val statsRepository: StatsRepository) : MyViewModel {
    fun next(): GameUiState {
        if (repository.isLastQuestion()) {
            repository.resetIndex()
            return GameUiState.Finish
        }
        return GameUiState.Initial(repository.getNextWord().unscrambleWord, "")
    }

    fun skip(): GameUiState {
        if (repository.isLastQuestion()) {
            repository.resetIndex()
            statsRepository.saveIncorrectAnswer()
            return GameUiState.Finish
        }
        statsRepository.saveIncorrectAnswer()
        return GameUiState.Initial(repository.getNextWord().unscrambleWord, "")
    }

    fun check(text: String): GameUiState {
        val unscrambleWord = repository.getCurrentWord()
        if (unscrambleWord.answer == text){
            statsRepository.saveCorrectAnswer()
            return GameUiState.Correct( unscrambleWord.answer, true, false, false)
        } else {
            statsRepository.saveIncorrectAnswer()
            return GameUiState.Incorrect( unscrambleWord.answer, false, true, true)
        }
    }

    fun handleUserInput(text: String): GameUiState {
        repository.saveCurrentUserInput(text)
        val unscrambleWord = repository.getCurrentWord()
        if (text.length == unscrambleWord.unscrambleWord.length)
            return GameUiState.InputVariant(text, true)
        else
            return GameUiState.InputVariant(text,  false)
    }

    fun init(): GameUiState {
        statsRepository.resetAnswers()
        return GameUiState.Initial(repository.getCurrentWord().unscrambleWord, repository.getCurrentUserInput())
    }

}

