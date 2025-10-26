package ru.kirill.unscramblewords.fragments.stats

class StatsViewModel(private val repository: StatsRepository) {

    fun getStatsUiState(): StatsUiState {
        val (correct, incorrect) = repository.getAnswers()
        return StatsUiState.Base(correct, incorrect)
    }

}
