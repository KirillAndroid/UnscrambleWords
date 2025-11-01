package ru.kirill.unscramblewords.di

import ru.kirill.unscramblewords.ProvideGameViewModel
import ru.kirill.unscramblewords.ProvideStatsViewModel

interface ProvideViewModel {
    fun <T : MyViewModel> makeViewModel(claszz: Class<T>) : T

    class Make(core: Core) : ProvideViewModel {
        private lateinit var chain: ProvideViewModel

        init {
            chain = Error()
            chain = ProvideGameViewModel(core, chain)
            chain = ProvideStatsViewModel(core, chain)
        }

        override fun <T : MyViewModel> makeViewModel(claszz: Class<T>): T {
            return chain.makeViewModel(claszz)
        }
    }

    class Error : ProvideViewModel {
        override fun <T : MyViewModel> makeViewModel(claszz: Class<T>): T {
            throw IllegalStateException("no chain found")
        }
    }
}