package ru.kirill.unscramblewords

import android.app.Application
import ru.kirill.unscramblewords.fragments.game.GameRepository
import ru.kirill.unscramblewords.fragments.game.GameViewModel

class UnscrambleWordsApp : Application() {
    public lateinit var gameViewModel: GameViewModel
    override fun onCreate() {
        super.onCreate()
        val sharedPreferences = getSharedPreferences("unscrambleWordsAppData", MODE_PRIVATE)
        gameViewModel = GameViewModel(
            GameRepository.Base(
                IntCache.Base(sharedPreferences, "currentIndex"),
                StringCache.Base(sharedPreferences, "currentUserInput")
            )
        )
    }

}