package com.demo.cryptoapp.presentation

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.demo.cryptoapp.R
import com.demo.cryptoapp.databinding.ActivityCoinDetailBinding

class CoinDetailActivity : AppCompatActivity() {

    private var fromSymbol = SYMBOL_UNKNOWN

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_coin_detail)

        parseIntent()
        if (savedInstanceState == null) {
            launchFragment()
        }
    }

    private fun parseIntent() {
        fromSymbol = intent.getStringExtra(EXTRA_FROM_SYMBOL)
            ?: throw RuntimeException("Intent param from_symbol is absent")
    }

    private fun launchFragment() {
        val fragment = CoinDetailFragment.newInstance(fromSymbol)
        supportFragmentManager
            .beginTransaction()
            .replace(R.id.coin_info_container, fragment)
            .commit()
    }

    companion object {

        private const val SYMBOL_UNKNOWN = ""
        private const val EXTRA_FROM_SYMBOL = "from_symbol"

        fun newIntent(context: Context, fromSymbol: String): Intent {
            val intent = Intent(context, CoinDetailActivity::class.java)
            intent.putExtra(EXTRA_FROM_SYMBOL, fromSymbol)
            return intent
        }
    }
}