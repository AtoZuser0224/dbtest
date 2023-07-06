package com.example.dbtest.composable.Login

import androidx.compose.animation.core.Spring
import androidx.compose.animation.core.animateDpAsState
import androidx.compose.animation.core.spring
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.example.dbtest.composable.widget.GetNextButtonLogin
import com.example.dbtest.composable.widget.StringTextFieldLogin

@Composable
fun GetGenderLogin(name: MutableState<String>, gender: MutableState<String>, navController: NavHostController){

    MaterialTheme {
        Surface(
            modifier = Modifier.fillMaxSize(),
            color = MaterialTheme.colorScheme.background
        ) {
            Column() {
                val enable = remember {
                    mutableStateOf(false)
                }
                val fieldOffSet by animateDpAsState(
                    targetValue = if (enable.value) 0.dp else (-600).dp,
                    animationSpec = spring(
                        dampingRatio = Spring.DampingRatioLowBouncy,
                        stiffness = Spring.StiffnessLow
                    )
                )
                LaunchedEffect(Unit) {
                    enable.value = true
                }
                Text(
                    text = "반갑습니다! ${name.value}님\n성별을 입력하여 주세요.",
                    modifier = Modifier
                        .padding(30.dp)
                        .offset(x = fieldOffSet), fontSize = 20.sp, lineHeight = 30.sp
                )
                StringTextFieldLogin(
                    item = gender, modifier = Modifier
                        .align(Alignment.CenterHorizontally)
                        .padding(top = 5.dp)
                        .fillMaxWidth(0.9f)
                        .height(80.dp)
                        .offset(x = fieldOffSet)
                )
                Spacer(modifier = Modifier.fillMaxHeight(0.8f))
                GetNextButtonLogin {
                    navController.navigate("Birth")
                }
            }
        }
    }

}