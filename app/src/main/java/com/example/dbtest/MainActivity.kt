package com.example.dbtest

import android.app.Application
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.animateContentSize
import androidx.compose.animation.core.Spring
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.spring
import androidx.compose.animation.core.tween
import androidx.compose.animation.expandIn
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.shrinkOut
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.navigation.NavController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.dbtest.composable.Login.LoginPage
import com.example.dbtest.composable.main.Main
import com.example.dbtest.db.DataManager
import com.example.dbtest.db.HealthPerDay
import com.example.dbtest.db.ProductRepository
import com.example.dbtest.db.ProductRoomDatabase
import com.example.dbtest.db.dataStore
import com.example.dbtest.ui.theme.DbtestTheme
import kotlinx.coroutines.delay
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            val db = MainViewModel(LocalContext.current.applicationContext
                    as Application)
            val bard = ContentViewModel()
            val dataManager = DataManager(dataStore = dataStore)
            DbtestTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                )   {
                    val navController = rememberNavController()

                    NavHost(navController = navController, startDestination = "Start") {

                        composable("Start") {
                            Start(navController)
                        }
                        composable("Login") {
                            LoginPage(dataManager,navController,)
                        }
                        composable("Main"){
                            Main()
                        }
                    }

                }
                }
            }
        }
    }
    @Composable
    fun Start(navController: NavController){
        MaterialTheme{
            val enable = remember {
                mutableStateOf(false)
            }
            val enable2 = remember {
                mutableStateOf(false)
            }
            val enable3 = remember {
                mutableStateOf(false)
            }

            val fieldOffSet by animateFloatAsState(targetValue = if(enable.value)800f else 2000f,
                animationSpec = spring(
                    dampingRatio = Spring.DampingRatioLowBouncy,
                    stiffness = Spring.StiffnessLow,
                )
            )
            val turn by animateFloatAsState(targetValue = if(enable2.value)0.0f else -3000.0f,
                animationSpec = tween(durationMillis = 1000)
            )
            LaunchedEffect(Unit){
                enable.value=true
                delay(700)
                enable2.value = true
                delay(1000)
                enable3.value =true
                delay(1200)
                enable3.value =false
                enable2.value = false
                delay(1000)
                navController.navigate("Login"){
                    popUpTo("Start"){
                        inclusive = true
                    }
                }
            }

            Surface(
                modifier = Modifier.fillMaxSize(),
                color = MaterialTheme.colorScheme.background
            ) {

                Column() {
                    Row(modifier = Modifier.align(Alignment.CenterHorizontally)) {
                        Image(
                            painter = painterResource(id = R.drawable.openai),
                            contentDescription = "openAi",
                            modifier = Modifier
                                .size(120.dp)
                                .graphicsLayer(
                                    translationY = fieldOffSet,
                                    rotationZ = turn
                                )
                        )
                        AnimatedVisibility(
                            visible = enable3.value,
                            enter = fadeIn() + expandIn(),
                            exit = fadeOut() + shrinkOut()
                        ) {
                            Text(
                                text = "CHAT FIT",
                                fontSize = 40.sp,
                                fontWeight = FontWeight.ExtraBold,
                                modifier = Modifier
                                    .fillMaxHeight()
                                    .padding(30.dp)
                                    .animateContentSize()
                                    .align(Alignment.CenterVertically)
                                    .graphicsLayer(
                                        translationY = fieldOffSet,
                                    )
                                )
                            }
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