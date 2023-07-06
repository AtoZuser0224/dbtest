package com.example.dbtest

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.stringPreferencesKey
import androidx.datastore.preferences.preferencesDataStore
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

val Context.dataStore: DataStore<Preferences> by preferencesDataStore(name = "user_prefs")
class DataManager(
    private val dataStore: DataStore<Preferences>
) {
    suspend fun storeData(key:String,value:String) {
        dataStore.edit {
            it[stringPreferencesKey(key)] = value
        }
    }

    fun GetData(key:String): Flow<String?> {
        return dataStore.data.map {
            it[stringPreferencesKey(key)]
        }
    }


}