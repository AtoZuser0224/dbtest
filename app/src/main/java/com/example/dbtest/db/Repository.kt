package com.example.dbtest.db

import androidx.lifecycle.LiveData
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch


class ProductRepository(private val productDao: ProductDao) {
    val allProducts: LiveData<List<HealthPerDay>> = productDao.getAllProducts()

    private val coroutineScope = CoroutineScope(Dispatchers.Main)

    fun upsertHealthData(newHealthData: HealthPerDay) {
        coroutineScope.launch(Dispatchers.IO) {
            productDao.upsertProduct(newHealthData)
        }
    }

    fun deleteHealthData(day: String) {
        coroutineScope.launch(Dispatchers.IO) {
            productDao.deleteProduct(day)
        }
    }


}