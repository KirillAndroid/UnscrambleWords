package ru.kirill.unscramblewords.fragments.game

import ru.kirill.unscramblewords.IntCache
import ru.kirill.unscramblewords.StringCache
import ru.kirill.unscramblewords.UnscrambleAndAnswer

interface GameRepository {

    fun getCurrentWord(): UnscrambleAndAnswer
    fun getNextWord(): UnscrambleAndAnswer

    fun getCurrentUserInput(): String

    fun saveCurrentUserInput(newValue: String)

    class Base(val currentIndex: IntCache,
               val currentInput: StringCache,
               private val words: List<UnscrambleAndAnswer> = listOf<UnscrambleAndAnswer>(
                   UnscrambleAndAnswer(
                       unscrambleWord = "htacw",
                       answer = "watch"
                   ), UnscrambleAndAnswer(
                       unscrambleWord = "olhel",
                       answer = "hello"
                   ), UnscrambleAndAnswer(
                       unscrambleWord = "321",
                       answer = "123"
                   )
    )) : GameRepository {


        override fun getCurrentWord() = words[currentIndex.read(0)]

        override fun getNextWord(): UnscrambleAndAnswer {
            val i = (currentIndex.read(0) + 1) % words.size
            currentIndex.save(i)
            return words[i]
        }

        override fun getCurrentUserInput(): String {
            return currentInput.read()
        }

        override fun saveCurrentUserInput(newValue: String) {
            currentInput.save(newValue)
        }

    }
}