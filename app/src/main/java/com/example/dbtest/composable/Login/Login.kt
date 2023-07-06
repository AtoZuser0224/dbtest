package com.example.dbtest.composable.Login

import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.dbtest.DataManager

@Composable
fun LoginPage(dataManager: DataManager){
    val name = remember { mutableStateOf("") }
    val height = remember { mutableStateOf("") }
    val weight = remember { mutableStateOf("") }
    val gender = remember { mutableStateOf("") }
    val birth = remember { mutableStateOf("") }

    // 네비게이션 컨트롤러
    val navController = rememberNavController()

    NavHost(navController = navController, startDestination = "Name") {
        composable("Name"){
            GetNameLogin(name, navController = navController)
        }
        composable("Height"){
            GetHeightLogin(name = name,height = height, navController = navController)
        }
        composable("Weight"){
            GetWeightLogin(name = name,weight = weight, navController = navController)
        }
        composable("Gender"){
            GetGenderLogin(name = name,gender = gender, navController = navController)
        }
        composable("Birth"){
            GetBirthLogin(name = name, selectedDate = birth, navController = navController)
        }
        composable("Ex"){
            GetExLogin(name = name, navController = navController)
        }
        composable("End"){
            ShowDialogEndLogin(name,height,weight,gender,birth,dataManager,navController)
        }
    }
}



