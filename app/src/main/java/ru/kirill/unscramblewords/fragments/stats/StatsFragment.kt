package ru.kirill.unscramblewords.fragments.stats

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import ru.kirill.unscramblewords.fragments.game.NavigateToGame
import ru.kirill.unscramblewords.R
import ru.kirill.unscramblewords.UnscrambleWordsApp
import ru.kirill.unscramblewords.customviews.VisibilityButtonState
import ru.kirill.unscramblewords.databinding.StatisticsFragmentBinding

class StatsFragment : Fragment() {
    private var _binding: StatisticsFragmentBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = StatisticsFragmentBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val statsViewModel = (requireActivity().application as UnscrambleWordsApp).statsViewModel
        val statsUiState = statsViewModel.getStatsUiState()
        statsUiState.update(binding.statistics, binding.newGameButton)
        binding.newGameButton.setOnClickListener {
            (requireActivity() as NavigateToGame).navigateToGame()
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }
}