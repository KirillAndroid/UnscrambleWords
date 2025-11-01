package ru.kirill.unscramblewords.fragments.stats.di

import ru.kirill.unscramblewords.IntCache
import ru.kirill.unscramblewords.di.AbstractChain
import ru.kirill.unscramblewords.di.Core
import ru.kirill.unscramblewords.di.Module
import ru.kirill.unscramblewords.di.ProvideViewModel
import ru.kirill.unscramblewords.fragments.stats.StatsRepository
import ru.kirill.unscramblewords.fragments.stats.StatsViewModel

class ProvideStatsViewModel(private val core: Core, nextChain: ProvideViewModel) : AbstractChain(core, nextChain, StatsViewModel::class.java) {
    override fun module(): Module<*> = StatsModule(core)
}

class StatsModule(private val core: Core) : Module<StatsViewModel> {
    override fun viewModel(): StatsViewModel {
        val statsRepository = StatsRepository.Base(
            IntCache.Base(core.sharedPreferences, "correctAnswersCount"),
            IntCache.Base(core.sharedPreferences, "incorrectAnswersCount")
        )
        return StatsViewModel(
            statsRepository
        )
    }

}