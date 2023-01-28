package com.demo.cryptoapp.presentation

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import com.demo.cryptoapp.R
import com.demo.cryptoapp.databinding.ActivityCoinPriceListBinding

class CoinPriceListActivity : AppCompatActivity() {

    private val binding by lazy {
        ActivityCoinPriceListBinding.inflate(layoutInflater)
    }

    private val viewModel by lazy {
        ViewModelProvider(this)[CoinViewModel::class.java]
    }

    private lateinit var coinInfoAdapter: CoinInfoAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        coinInfoAdapter = CoinInfoAdapter(this)
        binding.recyclerViewCoinPriceList.adapter = coinInfoAdapter

        viewModel.priceList.observe(this) {
            coinInfoAdapter.submitList(it)
        }

        setOnClickListeners()
    }

    private fun setOnClickListeners() {
        coinInfoAdapter.onCoinClickListener = {
            if (isOnePaneMod()) {
                val intent = CoinDetailActivity.newIntent(
                    this@CoinPriceListActivity,
                    it.fromSymbol
                )
                startActivity(intent)
            } else {
                launchFragment(it.fromSymbol)
            }
        }
    }

    private fun isOnePaneMod() = binding.coinInfoContainer == null

    private fun launchFragment(fromSymbol: String) {
        supportFragmentManager.popBackStack()
        val fragment = CoinDetailFragment.newInstance(fromSymbol)
        supportFragmentManager
            .beginTransaction()
            .replace(R.id.coin_info_container, fragment)
            .addToBackStack(null)
            .commit()
    }
}