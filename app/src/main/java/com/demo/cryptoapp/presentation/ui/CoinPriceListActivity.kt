package com.demo.cryptoapp.presentation.ui

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import com.demo.cryptoapp.R
import com.demo.cryptoapp.databinding.ActivityCoinPriceListBinding
import com.demo.cryptoapp.presentation.app.CoinInfoApp
import com.demo.cryptoapp.presentation.viewmodels.CoinViewModel
import com.demo.cryptoapp.presentation.viewmodels.ViewModelFactory
import com.demo.cryptoapp.presentation.adapters.CoinInfoAdapter
import javax.inject.Inject

class CoinPriceListActivity : AppCompatActivity() {

    private val binding by lazy {
        ActivityCoinPriceListBinding.inflate(layoutInflater)
    }

    private val component by lazy {
        (application as CoinInfoApp).component
    }

    @Inject
    lateinit var viewModelFactory: ViewModelFactory

    private val viewModel by lazy {
        ViewModelProvider(this, viewModelFactory)[CoinViewModel::class.java]
    }

    @Inject
    lateinit var coinInfoAdapter: CoinInfoAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        component.inject(this)
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        binding.recyclerViewCoinPriceList.adapter = coinInfoAdapter
        binding.recyclerViewCoinPriceList.itemAnimator = null

        viewModel.coinInfoList.observe(this) {
            coinInfoAdapter.submitList(it)
        }

        setOnClickListeners()
    }

    private fun setOnClickListeners() {
        coinInfoAdapter.onCoinClickListener = {
            if (isOnePaneMod()) {
                launchActivity(it.fromSymbol)
            } else {
                launchFragment(it.fromSymbol)
            }
        }
    }

    private fun isOnePaneMod() = binding.coinInfoContainer == null

    private fun launchActivity(fromSymbol: String) {
        val intent = CoinDetailActivity.newIntent(
            this@CoinPriceListActivity,
            fromSymbol
        )
        startActivity(intent)
    }

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