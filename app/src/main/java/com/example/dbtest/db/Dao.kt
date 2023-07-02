package com.example.dbtest.db

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Query
import androidx.room.Upsert

@Dao
interface ProductDao {

    @Upsert
    fun upsertProduct(product: HealthPerDay)

    @Query("SELECT * FROM products WHERE day = :name")
    fun findProduct(name: String): List<HealthPerDay>

    @Query("DELETE FROM products WHERE day = :name")
    fun deleteProduct(name: String)

    @Query("SELECT * FROM products")
    fun getAllProducts(): LiveData<List<HealthPerDay>>
}