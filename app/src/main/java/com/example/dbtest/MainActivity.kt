package com.example.dbtest

import android.app.Application
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.ui.Modifier
import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.example.dbtest.ui.theme.DbtestTheme
import androidx.compose.ui.platform.LocalContext
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Button
import androidx.compose.material3.OutlinedTextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.dbtest.composable.LoginPage
import com.example.dbtest.db.Food
import com.example.dbtest.db.HealthPerDay
import com.example.dbtest.db.ProductRepository
import com.example.dbtest.db.ProductRoomDatabase

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            val db = MainViewModel(LocalContext.current.applicationContext
                    as Application)
            val bard = ContentViewModel()
            DbtestTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                        LoginPage()
                    }
                }
            }
        }
    }




















class MainViewModel(application: Application) : ViewModel() {

    val allHealthData: LiveData<List<HealthPerDay>>
    private val repository: ProductRepository

    init {
        val productDb = ProductRoomDatabase.getInstance(application)
        val productDao = productDb.productDao()
        repository = ProductRepository(productDao)

        allHealthData = repository.allProducts
    }
    fun upsertHealthData(product: HealthPerDay) {
        repository.upsertHealthData(product)
    }


    fun deleteHealthData(name: String) {
        repository.deleteHealthData(name)
    }
}