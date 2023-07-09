package com.example.dbtest.composable.Login

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.navigation.NavController
import com.example.dbtest.db.DataManager

@Composable
fun ShowDialogEndLogin(name: MutableState<String>,
                       height: MutableState<String>,
                       weight: MutableState<String>,
                       gender: MutableState<String>,
                       birth: MutableState<String>,
                       dataManager: DataManager,
                       navController: NavController){
    val showDialog = remember { mutableStateOf(true) }
    if (showDialog.value) {
        AlertDialog(
            onDismissRequest = { showDialog.value = false },
            title = { Text(text = "당신의 정보와 일치한가요?") },
            
            confirmButton = {
                Button(
                    onClick = {
                                showDialog.value = false
                                navController.navigate("Main"){
                                    popUpTo("Login"){
                                        inclusive = true
                                    }
                                }
                              },
                    modifier = Modifier.fillMaxWidth()
                ) {
                    Text(text = "네")
                }
                Button(
                    onClick = {
                        showDialog.value = false
                        navController.navigate("Ex"){
                        popUpTo("End"){
                            inclusive = true
                        }
                    } },
                    modifier = Modifier.fillMaxWidth(),
                    colors = ButtonDefaults.buttonColors(
                        containerColor = Color.Gray
                    )
                ) {
                    Text(text = "아니요")
                }
            }
        )
    }
}