package ru.kirill.unscramblewords.fragments.stats

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import ru.kirill.unscramblewords.fragments.game.NavigateToGame
import ru.kirill.unscramblewords.R
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
        binding.newGameButton.update(VisibilityButtonState(View.VISIBLE, true))
        binding.newGameButton.setOnClickListener {
            (requireActivity() as NavigateToGame).navigateToGame()
        }
        val text = getString(R.string.statistics, 1, 2)
        binding.statistics.update(text)
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }
}