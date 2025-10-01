package ru.kirill.unscramblewords

import org.junit.Assert.*;
import org.junit.Before
import org.junit.Test
import ru.kirill.unscramblewords.fragments.game.GameRepository
import ru.kirill.unscramblewords.fragments.game.GameUiState
import ru.kirill.unscramblewords.fragments.game.GameViewModel

class GameViewModelTest {
    private lateinit var viewModel: GameViewModel

    @Before
    fun init() {
        viewModel = GameViewModel(repository = FakeRepository())
    }

    @Test
    fun testNumber1() {
        var actual : GameUiState = viewModel.init()
        var expected : GameUiState = GameUiState.Initial(
            unscrambleWordText = "htacw",
        )

        assertEquals(expected, actual)

        actual = viewModel.handleUserInput(text = "watch")
        expected = GameUiState.InputVariant(
            userInput = "watch",
            isCheckAvailable = true,
        )
        assertEquals(expected, actual)

        actual = viewModel.check(text = "watch")
        expected = GameUiState.Correct(
            answer = "watch",
            isNextAvailable = true,
            isCheckAvailable = false,
            isSkipAvailable = false,
        )
        assertEquals(expected, actual)

        actual = viewModel.next()
        expected = GameUiState.Initial(
            unscrambleWordText = "olhel",
        )
        assertEquals(expected, actual)
    }

    @Test
    fun testNumber2() {
        var actual : GameUiState = viewModel.init()
        var expected : GameUiState = GameUiState.Initial(
            unscrambleWordText = "htacw",
        )

        assertEquals(expected, actual)

        actual = viewModel.handleUserInput(text = "wathc") //неправильный ответ
        expected = GameUiState.InputVariant(
            userInput = "wathc",
            isCheckAvailable = true,
        )
        assertEquals(expected, actual)

        actual = viewModel.check(text = "wathc")
        expected = GameUiState.Incorrect(
            answer = "watch",
            isNextAvailable = false,
            isCheckAvailable = true,
            isSkipAvailable = true,
        )
        assertEquals(expected, actual)

        actual = viewModel.handleUserInput(text = "watch") //правильный ответ
        expected = GameUiState.InputVariant(
            userInput = "watch",
            isCheckAvailable = true,
        )
        assertEquals(expected, actual)

        actual = viewModel.next()
        expected = GameUiState.Initial(
            unscrambleWordText = "olhel",
        )
        assertEquals(expected, actual)
    }

    @Test
    fun testNumber3() {
        var actual : GameUiState = viewModel.init()
        var expected : GameUiState = GameUiState.Initial(
            unscrambleWordText = "htacw",
        )
        assertEquals(expected, actual)

        actual = viewModel.handleUserInput(text = "12345") //кол-во букв равно кол-ву букв в загаданном слове
        expected = GameUiState.InputVariant(
            userInput = "12345",
            isCheckAvailable = true,
        )
        assertEquals(expected, actual)
    }

    @Test
    fun testNumber4() {
        var actual : GameUiState = viewModel.init()
        var expected : GameUiState = GameUiState.Initial(
            unscrambleWordText = "htacw",
        )
        assertEquals(expected, actual)

        actual = viewModel.handleUserInput(text = "12345678") //кол-во букв равно кол-ву букв в загаданном слове
        expected = GameUiState.InputVariant(
            userInput = "12345678",
            isCheckAvailable = false,
        )
        assertEquals(expected, actual)
    }

    @Test
    fun testNumber5() {
        var actual : GameUiState = viewModel.init()
        var expected : GameUiState = GameUiState.Initial(
            unscrambleWordText = "htacw",
        )
        assertEquals(expected, actual)

        actual = viewModel.skip()
        expected = GameUiState.Initial(
            unscrambleWordText = "olhel",
        )
        assertEquals(expected, actual)
    }

    /**
     * проверка что skip доступен
     */
    @Test
    fun testNumber6() {
        var actual : GameUiState = viewModel.init()
        var expected : GameUiState = GameUiState.Initial(
            unscrambleWordText = "htacw",
        )
        assertEquals(expected, actual)

        actual = viewModel.handleUserInput(text = "wathc")
        expected = GameUiState.InputVariant(
            userInput = "wathc",
            isCheckAvailable = true,
        )
        assertEquals(expected, actual)

        actual = viewModel.skip()
        expected = GameUiState.Initial(
            unscrambleWordText = "olhel",
        )
        assertEquals(expected, actual)
    }

    @Test
    fun testNumber7() {
        var actual : GameUiState = viewModel.init()
        var expected : GameUiState = GameUiState.Initial(
            unscrambleWordText = "htacw",
        )

        assertEquals(expected, actual)

        actual = viewModel.handleUserInput(text = "wathc")
        expected = GameUiState.InputVariant(
            userInput = "wathc",
            isCheckAvailable = true,
        )
        assertEquals(expected, actual)

        actual = viewModel.check(text = "wathc")
        expected = GameUiState.Incorrect(
            answer = "watch",
            isNextAvailable = false,
            isCheckAvailable = true,
            isSkipAvailable = true,
        )
        assertEquals(expected, actual)

        actual = viewModel.skip()
        expected = GameUiState.Initial(
            unscrambleWordText = "olhel",
        )
        assertEquals(expected, actual)
    }
}

class FakeRepository : GameRepository {
    private val words = listOf<UnscrambleAndAnswer>(UnscrambleAndAnswer(
        unscrambleWord = "htacw",
        answer = "watch"
    ), UnscrambleAndAnswer(
        unscrambleWord = "olhel",
        answer = "hello"
    ))
    private var currentIndex: Int = 0

    override fun getCurrentWord() = words[currentIndex]

    override fun getNextWord(): UnscrambleAndAnswer {
        currentIndex = (currentIndex + 1) % words.size
        return words[currentIndex]
    }

    private var userInput: String = ""

    override fun getCurrentUserInput(): String {
        return userInput
    }

    override fun saveCurrentUserInput(newValue: String) {
        userInput = newValue
    }

}