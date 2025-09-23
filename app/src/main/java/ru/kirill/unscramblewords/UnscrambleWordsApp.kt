package ru.kirill.unscramblewords

import android.app.Application

class UnscrambleWordsApp : Application() {
    public lateinit var viewModel: GameViewModel
    override fun onCreate() {
        super.onCreate()
        val sharedPreferences = getSharedPreferences("unscrambleWordsAppData", MODE_PRIVATE)
        viewModel = GameViewModel(GameRepository.Base(
            IntCache.Base(sharedPreferences, "currentIndex"),
            StringCache.Base(sharedPreferences, "currentUserInput")
        ))
    }

}