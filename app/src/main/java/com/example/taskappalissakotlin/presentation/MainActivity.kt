package com.example.taskappalissakotlin.presentation

import android.os.Bundle
import android.util.Log
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import by.kirich1409.viewbindingdelegate.viewBinding
import com.example.taskappalissakotlin.R
import com.example.taskappalissakotlin.databinding.ActivityMainBinding
import com.example.taskappalissakotlin.domain.ShopItem

class MainActivity : AppCompatActivity(R.layout.activity_main) {

    private val viewModel: MainViewModel by viewModels()
    private val binding: ActivityMainBinding by viewBinding()
    private lateinit var shopItem: ShopItem
    private lateinit var adapterMain: MainAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setupRecyclerView()

        viewModel.shopList.observe(this, Observer {
            Log.e("TAG", "onCreate: ${it.size}")
            adapterMain.setList(it)
        })

    }

    private fun setupRecyclerView() {
        binding.rv.apply {
            adapterMain = MainAdapter { item ->
                viewModel.changeEnableState(item)
            }

            adapter = adapterMain
        }
    }

    companion object {
        const val VIEW_TYPE_ENABLED = 100
        const val VIEW_TYPE_DISABLED = 101
    }

}
