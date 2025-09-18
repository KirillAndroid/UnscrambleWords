package ru.kirill.unscramblewords

interface GameRepository {

    fun getCurrentWord(): UnscrambleAndAnswer
    fun getNextWord(): UnscrambleAndAnswer

    class Base(private val words: List<UnscrambleAndAnswer> = listOf<UnscrambleAndAnswer>(UnscrambleAndAnswer(
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
        private var currentIndex: Int = 0

        override fun getCurrentWord() = words[currentIndex]

        override fun getNextWord(): UnscrambleAndAnswer {
            currentIndex = (currentIndex + 1) % words.size
            return words[currentIndex]
        }

    }
}
