package ru.kirill.unscramblewords

import android.os.Build
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import ru.kirill.unscramblewords.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private val textWatcher: TextWatcher = object : TextWatcher {
        override fun afterTextChanged(s: Editable?) = Unit

        override fun beforeTextChanged(
            s: CharSequence?,
            start: Int,
            count: Int,
            after: Int
        ) = Unit

        override fun onTextChanged(
            s: CharSequence?,
            start: Int,
            before: Int,
            count: Int
        ) {
            uiState = viewModel.handleUserInput(text = s.toString())
            uiState.update(
                failTextView = binding.failTextView,
                correctTextView = binding.correctTextView,
                unscrambleWord = binding.unscrambleWordTextView,
                inputTextView = binding.inputView,
                checkButton = binding.checkButton,
                nextButton = binding.nextButton,
                skipButton = binding.skipButton)
        }

    }
    private lateinit var viewModel: GameViewModel
    private lateinit var binding: ActivityMainBinding
    private lateinit var uiState: GameUiState

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        binding = ActivityMainBinding.inflate(layoutInflater).apply {
            setContentView(root)
        }
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.game_container)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }


        viewModel = (application as UnscrambleWordsApp).viewModel
//        val viewModel: GameViewModel = GameViewModel(GameRepository.Base())

        binding.nextButton.setOnClickListener {
            uiState = viewModel.next()
            uiState.update(
                inputTextView = binding.inputView,
                failTextView = binding.failTextView,
                correctTextView = binding.correctTextView,
                unscrambleWord = binding.unscrambleWordTextView,
                checkButton = binding.checkButton,
                nextButton = binding.nextButton,
                skipButton = binding.skipButton)
        }
        binding.skipButton.setOnClickListener {
            uiState = viewModel.skip()
            uiState.update(
                inputTextView = binding.inputView,
                failTextView = binding.failTextView,
                correctTextView = binding.correctTextView,
                unscrambleWord = binding.unscrambleWordTextView,
                checkButton = binding.checkButton,
                nextButton = binding.nextButton,
                skipButton = binding.skipButton)
        }
        binding.checkButton.setOnClickListener {
            uiState = viewModel.check(text = binding.inputView.binding.textInputEditText.text.toString())
            uiState.update(
                inputTextView = binding.inputView,
                failTextView = binding.failTextView,
                correctTextView = binding.correctTextView,
                unscrambleWord = binding.unscrambleWordTextView,
                checkButton = binding.checkButton,
                nextButton = binding.nextButton,
                skipButton = binding.skipButton)
        }

        uiState = if (savedInstanceState == null) {
            viewModel.init()
        } else {
            GameUiState.Empty


        }
        uiState.update(
            inputTextView = binding.inputView,
            failTextView = binding.failTextView,
            correctTextView = binding.correctTextView,
            unscrambleWord = binding.unscrambleWordTextView,
            checkButton = binding.checkButton,
            nextButton = binding.nextButton,
            skipButton = binding.skipButton)
    }

    override fun onPause() {

        super.onPause()
        binding.inputView.removeTextChangedListener(textWatcher)

    }

    override fun onResume() {

        super.onResume()
        binding.inputView.addTextChangedListener(textWatcher)

    }
}