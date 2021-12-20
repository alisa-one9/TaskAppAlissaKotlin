package com.example.taskappalissakotlin.domain

import androidx.lifecycle.LiveData

class GetShopListUseCase (private val repository: ShopListRepository) {

    fun getShopItemList(): LiveData<List<ShopItem>>{
        return repository.getShopItemList()
    }
}