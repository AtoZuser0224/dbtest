package com.example.dbtest.db

import android.content.Context
import androidx.annotation.NonNull
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.room.ColumnInfo
import androidx.room.Dao
import androidx.room.Database
import androidx.room.Entity
import androidx.room.PrimaryKey
import androidx.room.Query
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.TypeConverter
import androidx.room.TypeConverters
import androidx.room.Upsert
import com.google.gson.Gson
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Deferred
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.async
import kotlinx.coroutines.launch
class FoodConverter {
    @TypeConverter
    fun fromCustomObject(food: Food): String {
        return Gson().toJson(food)
    }

    @TypeConverter
    fun toCustomObject(json: String): Food {
        return Gson().fromJson(json, Food::class.java)
    }
}
data class Food(
    var name:String,
    var kcal: Int,
    var sugar: Int,
    var protein:Int,
    var fat: Int,
    var ex: String
)
@Entity(tableName = "products")
@TypeConverters(FoodConverter::class)
data class HealthPerDay (
    @PrimaryKey()
    var day: String,
    var height:Int,
    var name: String,
    var gender : Boolean,
    var weight:Int,
    var breakfast: Food,
    var lunch: Food,
    var dinner: Food,
        )



