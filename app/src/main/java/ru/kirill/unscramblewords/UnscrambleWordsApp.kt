package ru.kirill.unscramblewords

import android.app.Application

class UnscrambleWordsApp : Application() {
    public lateinit var viewModel: GameViewModel
    override fun onCreate() {
        super.onCreate()
        viewModel = GameViewModel(GameRepository.Base())
    }

}