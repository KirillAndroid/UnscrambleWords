package ru.kirill.unscramblewords

import android.app.Application
import ru.kirill.unscramblewords.di.ClearViewModel
import ru.kirill.unscramblewords.di.Core
import ru.kirill.unscramblewords.di.ManageViewModel
import ru.kirill.unscramblewords.di.MyViewModel
import ru.kirill.unscramblewords.di.ProvideViewModel

class UnscrambleWordsApp : Application(), ProvideViewModel {

    lateinit var factory: ManageViewModel
    override fun onCreate() {
        super.onCreate()
        val core = Core(
            context = this,
            clear = object : ClearViewModel {
                override fun clear(claszz: Class<out MyViewModel>) {
                    factory.clear(claszz)
                }
            }
        )
        val make = ProvideViewModel.Make(
            core
        )
        factory = ManageViewModel.Factory(make)
    }

    override fun <T : MyViewModel> makeViewModel(claszz: Class<T>): T = factory.makeViewModel(claszz)

}





