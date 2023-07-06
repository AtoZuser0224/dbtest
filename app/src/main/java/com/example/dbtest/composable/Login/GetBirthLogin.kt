package com.example.dbtest.composable.Login

import androidx.compose.animation.core.Spring
import androidx.compose.animation.core.animateDpAsState
import androidx.compose.animation.core.spring
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.example.dbtest.composable.widget.DatePicker
import com.example.dbtest.composable.widget.GetNextButtonLogin

@Composable
fun GetBirthLogin(name: MutableState<String>, selectedDate: MutableState<String>, navController: NavHostController){


    MaterialTheme {
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
        Surface(
            modifier = Modifier.fillMaxSize(),
            color = MaterialTheme.colorScheme.background
        ) {
            Column() {
                Text(
                    text = "반갑습니다! ${name.value}님\n생년월일을 입력하여 주세요.",
                    modifier = Modifier
                        .padding(30.dp)
                        .offset(x = fieldOffSet), fontSize = 20.sp, lineHeight = 30.sp
                )
                DatePicker(
                    label = "생일",
                    value = selectedDate.value,
                    onValueChange = { selectedDate.value = it },
                    keyboardOptions = KeyboardOptions(imeAction = ImeAction.Done),
                    keyboardActions = KeyboardActions(
                        onDone = { /* handle keyboard done action */ },
                    ),
                    modifier = Modifier.offset(x = fieldOffSet)
                )
                Spacer(modifier = Modifier.fillMaxHeight(0.8f))
                GetNextButtonLogin {
                    navController.navigate("Ex")
                }
            }
        }
    }

}