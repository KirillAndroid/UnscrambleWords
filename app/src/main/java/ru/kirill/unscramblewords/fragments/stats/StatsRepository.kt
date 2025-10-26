package ru.kirill.unscramblewords.fragments.stats

import ru.kirill.unscramblewords.IntCache

interface StatsRepository {
    fun getAnswers() : Pair<Int, Int>
    fun saveIncorrectAnswer()

    fun saveCorrectAnswer()
    fun resetAnswers()

    class Base(private val answerCorrect: IntCache.Base, private val answerIncorrect: IntCache.Base) : StatsRepository {
        override fun getAnswers() : Pair<Int, Int> {
            return Pair(answerCorrect.read(0), answerIncorrect.read(0))
        }

        override fun saveIncorrectAnswer() {
            answerIncorrect.save(answerIncorrect.read(0) + 1)
        }

        override fun saveCorrectAnswer() {
            answerCorrect.save(answerCorrect.read(0) + 1)
        }

        override fun resetAnswers() {
            answerCorrect.save(0)
            answerIncorrect.save(0)
        }
    }
}

