package ru.kirill.unscramblewords.di

interface Module<T : MyViewModel> {
    fun viewModel() : T
}