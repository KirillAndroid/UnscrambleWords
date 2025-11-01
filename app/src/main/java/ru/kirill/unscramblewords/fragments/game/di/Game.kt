package ru.kirill.unscramblewords.fragments.game.di

import ru.kirill.unscramblewords.IntCache
import ru.kirill.unscramblewords.StringCache
import ru.kirill.unscramblewords.di.AbstractChain
import ru.kirill.unscramblewords.di.Core
import ru.kirill.unscramblewords.di.Module
import ru.kirill.unscramblewords.di.ProvideViewModel
import ru.kirill.unscramblewords.fragments.game.GameRepository
import ru.kirill.unscramblewords.fragments.game.GameViewModel
import ru.kirill.unscramblewords.fragments.stats.StatsRepository

class GameModule(private val core: Core) : Module<GameViewModel> {
    override fun viewModel(): GameViewModel {
        val statsRepository = StatsRepository.Base(
            IntCache.Base(core.sharedPreferences, "correctAnswersCount"),
            IntCache.Base(core.sharedPreferences, "incorrectAnswersCount")
        )
        return  GameViewModel(
            GameRepository.Base(
                currentIndex = IntCache.Base(core.sharedPreferences, "currentIndex"),
                currentInput = StringCache.Base(core.sharedPreferences, "currentUserInput"),
            ), statsRepository = statsRepository
        )
    }

}

class ProvideGameViewModel(private val core: Core, nextChain: ProvideViewModel) : AbstractChain(core, nextChain, GameViewModel::class.java) {
    override fun module(): Module<*> = GameModule(core)

}