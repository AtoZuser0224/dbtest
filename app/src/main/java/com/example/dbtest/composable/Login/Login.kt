package com.example.dbtest.composable.Login

import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateListOf
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
    val list = remember {
        mutableStateListOf(Ex("", true))
    }
    // 네비게이션 컨트롤러
    val navController = rememberNavController()

    NavHost(navController = navController, startDestination = "Name") {

        composable("Name"){
            GetLoginSample(
                propertyName = "이름",
                nextProperty = "Height",
                Field = "String",
                property = name,
                navController = navController
            )
        }
        composable("Height"){
            GetLoginSample(
                propertyName = "키",
                nextProperty = "Weight",
                Field = "Number",
                property = name,
                navController = navController
            )
        }
        composable("Weight"){
            GetLoginSample(
                propertyName = "몸무게",
                nextProperty = "Gender",
                Field = "Number",
                property = weight,
                navController = navController
            )
        }
        composable("Gender"){
            GetLoginSample(
                propertyName = "성별",
                nextProperty = "Birth",
                Field = "String",
                property = gender,
                navController = navController
            )
        }
        composable("Birth"){
            GetLoginSample(
                propertyName = "생일",
                nextProperty = "Ex",
                Field = "Date",
                property = birth,
                navController = navController
            )
        }
        composable("Ex"){
            GetExLogin(name = name,list, navController = navController)
        }
        composable("End"){
            ShowDialogEndLogin(name,height,weight,gender,birth,dataManager,navController)
        }
    }
}



