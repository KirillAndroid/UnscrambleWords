package ru.kirill.unscramblewords

import android.app.Application
import ru.kirill.unscramblewords.fragments.game.GameRepository
import ru.kirill.unscramblewords.fragments.game.GameViewModel
import ru.kirill.unscramblewords.fragments.stats.StatsRepository
import ru.kirill.unscramblewords.fragments.stats.StatsViewModel

class UnscrambleWordsApp : Application() {
    lateinit var gameViewModel: GameViewModel
    lateinit var statsViewModel: StatsViewModel
    override fun onCreate() {
        super.onCreate()
        val sharedPreferences = getSharedPreferences("unscrambleWordsAppData", MODE_PRIVATE)
        val statsRepository = StatsRepository.Base(
            IntCache.Base(sharedPreferences, "correctAnswersCount"),
            IntCache.Base(sharedPreferences, "incorrectAnswersCount")
        )
        gameViewModel = GameViewModel(
             GameRepository.Base(
                currentIndex = IntCache.Base(sharedPreferences, "currentIndex"),
                currentInput = StringCache.Base(sharedPreferences, "currentUserInput"),
            ), statsRepository = statsRepository
        )

        statsViewModel = StatsViewModel(
            statsRepository
        )
    }

}