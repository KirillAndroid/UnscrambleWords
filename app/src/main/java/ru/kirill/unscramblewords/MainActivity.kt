package ru.kirill.unscramblewords

import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.core.widget.addTextChangedListener
import ru.kirill.unscramblewords.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        val binding = ActivityMainBinding.inflate(layoutInflater).apply {
            setContentView(root)
        }
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.game_container)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }


        val viewModel: GameViewModel = GameViewModel(repository = GameRepository.Base())
        binding.nextButton.setOnClickListener {
            val uiState: GameUiState = viewModel.next()
            uiState.update(binding = binding)
        }
        binding.skipButton.setOnClickListener {
            val uiState: GameUiState = viewModel.skip()
            uiState.update(binding = binding)
        }
        binding.checkButton.setOnClickListener {
            val uiState: GameUiState = viewModel.check(text = binding.textInputEditText.text.toString())
            uiState.update(binding = binding)
        }
        binding.textInputEditText.addTextChangedListener {
            val uiState: GameUiState = viewModel.handleUserInput(text = it.toString())
            uiState.update(binding = binding)
        }
        val uiState: GameUiState = viewModel.init()
        uiState.update(binding = binding)
    }
}