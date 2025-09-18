package ru.kirill.unscramblewords

import android.os.Build
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.core.widget.addTextChangedListener
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
            uiState.update(binding = binding)
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
            uiState.update(binding = binding)
        }
        binding.skipButton.setOnClickListener {
            uiState = viewModel.skip()
            uiState.update(binding = binding)
        }
        binding.checkButton.setOnClickListener {
            uiState = viewModel.check(text = binding.textInputEditText.text.toString())
            uiState.update(binding = binding)
        }
//        binding.textInputEditText.addTextChangedListener {
//            uiState = viewModel.handleUserInput(text = it.toString())
//            uiState.update(binding = binding)
//        }
        uiState = if (savedInstanceState == null) {
            viewModel.init()
        } else {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
                savedInstanceState.getSerializable("uiState", GameUiState::class.java) as GameUiState
            } else {
                savedInstanceState.getSerializable("uiState") as GameUiState

            }
        }
        uiState.update(binding = binding)

    }

    override fun onPause() {
        super.onPause()
        binding.textInputEditText.removeTextChangedListener(textWatcher)
    }

    override fun onResume() {
        super.onResume()
        binding.textInputEditText.addTextChangedListener(textWatcher)
    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        outState.putSerializable("uiState", uiState)
    }
}