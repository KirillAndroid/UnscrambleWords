package ru.kirill.unscramblewords.fragments.game

import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import ru.kirill.unscramblewords.fragments.stats.NavigateToStats
import ru.kirill.unscramblewords.UnscrambleWordsApp
import ru.kirill.unscramblewords.databinding.GameFragmentBinding

class GameFragment : Fragment() {
    private lateinit var viewModel: GameViewModel
    private lateinit var uiState: GameUiState
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

    private var _binding: GameFragmentBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = GameFragmentBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel = (requireActivity().application as UnscrambleWordsApp).gameViewModel
        val update: () -> Unit = {
            uiState.update(
                inputTextView = binding.inputView,
                failTextView = binding.failTextView,
                correctTextView = binding.correctTextView,
                unscrambleWord = binding.unscrambleWordTextView,
                checkButton = binding.checkButton,
                nextButton = binding.nextButton,
                skipButton = binding.skipButton)
        }
        binding.nextButton.setOnClickListener {
            uiState = viewModel.next()
            update.invoke()
            uiState.navigate(requireActivity() as NavigateToStats)
        }
        binding.skipButton.setOnClickListener {
            uiState = viewModel.skip()
            update.invoke()
            uiState.navigate(requireActivity() as NavigateToStats)
        }
        binding.checkButton.setOnClickListener {
            uiState = viewModel.check(text = binding.inputView.binding.textInputEditText.text.toString())
            update.invoke()
        }

        uiState = if (savedInstanceState == null) {
            viewModel.init()
        } else {
            GameUiState.Empty
        }
        update.invoke()
    }


    override fun onPause() {
        super.onPause()
        binding.inputView.removeTextChangedListener(textWatcher)
    }

    override fun onResume() {
        super.onResume()
        binding.inputView.addTextChangedListener(textWatcher)

    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

}
