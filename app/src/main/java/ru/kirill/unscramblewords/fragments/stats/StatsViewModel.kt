package ru.kirill.unscramblewords.fragments.stats

import ru.kirill.unscramblewords.di.MyViewModel

class StatsViewModel(private val repository: StatsRepository) : MyViewModel {

    fun getStatsUiState(): StatsUiState {
        val (correct, incorrect) = repository.getAnswers()
        return StatsUiState.Base(correct, incorrect)
    }

}
