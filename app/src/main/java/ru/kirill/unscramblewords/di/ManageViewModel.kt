package ru.kirill.unscramblewords.di

interface ManageViewModel : ClearViewModel, ProvideViewModel {

    class Factory(private val make: ProvideViewModel) : ManageViewModel {
        private val map = mutableMapOf<Class<out MyViewModel>, MyViewModel?>()
        override fun <T : MyViewModel> makeViewModel(claszz: Class<T>): T {
            return if (map[claszz] != null)
                map[claszz] as T
            else {
                val viewModel = make.makeViewModel(claszz)
                map[claszz] = viewModel
                viewModel
            }
        }

        override fun clear(claszz: Class<out MyViewModel>) {
            map[claszz] = null
        }

    }
}