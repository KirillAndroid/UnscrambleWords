package ru.kirill.unscramblewords

interface GameRepository {

    fun getCurrentWord(): UnscrambleAndAnswer
    fun getNextWord(): UnscrambleAndAnswer
}
