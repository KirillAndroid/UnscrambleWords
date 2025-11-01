package ru.kirill.unscramblewords.di

abstract class AbstractChain(
    private val core: Core,
    private val nextChain: ProvideViewModel,
    private val viewModelClass: Class<out MyViewModel>
) : ProvideViewModel {
    override fun <T : MyViewModel> makeViewModel(claszz: Class<T>): T {
        if (claszz == viewModelClass) {
            return module().viewModel() as T
        } else {
            return nextChain.makeViewModel(claszz)
        }
    }

    protected abstract fun module() : Module<*>
}