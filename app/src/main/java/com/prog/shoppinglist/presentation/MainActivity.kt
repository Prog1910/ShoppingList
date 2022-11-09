package com.prog.shoppinglist.presentation

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.widget.TextView
import androidx.lifecycle.ViewModelProvider
import com.prog.shoppinglist.R
import com.prog.shoppinglist.databinding.ActivityMainBinding
import com.prog.shoppinglist.domain.ShopItem

class MainActivity : AppCompatActivity() {

    private lateinit var viewModel: MainViewModel

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        viewModel = ViewModelProvider(this@MainActivity)[MainViewModel::class.java]
        viewModel.shopList.observe(this@MainActivity) {
            showList(it)
        }
    }

    private fun showList(list: List<ShopItem>) {
        for (shopItem in list) {
            val layoutId = if (shopItem.enabled) {
                R.layout.shop_item_enabled
            } else {
                R.layout.shop_item_disabled
            }
            val view =
                LayoutInflater.from(this@MainActivity).inflate(layoutId, binding.llShopList, false)
            val tvName = view.findViewById<TextView>(R.id.tv_name)
            val tvCount = view.findViewById<TextView>(R.id.tv_count)
            tvName.text = shopItem.name
            tvCount.text = shopItem.count.toString()

            binding.llShopList.addView(view)
        }
    }

}