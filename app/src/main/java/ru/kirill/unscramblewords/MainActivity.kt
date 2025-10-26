package ru.kirill.unscramblewords

import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import ru.kirill.unscramblewords.fragments.game.GameScreen
import ru.kirill.unscramblewords.fragments.game.NavigateToGame
import ru.kirill.unscramblewords.fragments.stats.NavigateToStats
import ru.kirill.unscramblewords.fragments.stats.StatsScreen

class MainActivity : AppCompatActivity(), Navigable {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.container)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
        if (savedInstanceState == null) navigateToGame()
    }

    override fun navigate(screen: Screen) {
        screen.show(R.id.container, supportFragmentManager)
    }
}

interface Navigable : NavigateToGame, NavigateToStats {
    fun navigate(screen: Screen)

    override fun navigateToGame() {
        navigate(GameScreen)
    }

    override fun navigateToStats() {
        navigate(StatsScreen)
    }
}

