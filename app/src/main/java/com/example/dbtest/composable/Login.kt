package com.example.dbtest.composable

import android.app.DatePickerDialog
import android.widget.DatePicker
import android.widget.RadioGroup
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.RadioButton
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Alignment.Companion.CenterHorizontally
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.dbtest.ui.theme.skyblue40
import com.example.dbtest.ui.theme.skyblue80
import java.time.LocalDate
import java.time.format.DateTimeFormatter

@Preview
@Composable
fun LoginPage(){
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
        composable("End"){


        }
    }
}
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun GetNameLogin(name:MutableState<String>,navController: NavHostController){
    MaterialTheme{

        Surface(
            modifier = Modifier.fillMaxSize(),
            color = MaterialTheme.colorScheme.background
        ) {
            Column() {
                Text(text = "반갑습니다!\n이름을 입력하여 주세요.",
                    modifier = Modifier.padding(30.dp), fontSize = 20.sp , lineHeight = 30.sp)
                OutlinedTextField(value = name.value, onValueChange = {name.value = it  },
                    shape = RoundedCornerShape(30.dp),
                    singleLine = true ,
                    textStyle = TextStyle(fontSize = 30.sp, textAlign = TextAlign.Center),
                    modifier = Modifier
                        .align(CenterHorizontally)
                        .padding(top = 5.dp)
                        .fillMaxWidth(0.9f)
                        .height(80.dp))
                Spacer(modifier = Modifier.fillMaxHeight(0.8f))
                Button(shape = RoundedCornerShape(20.dp)
                    ,modifier = Modifier
                        .fillMaxWidth()
                        .fillMaxHeight()
                        .padding(10.dp),onClick = {
                            navController.navigate("Height")
                    }){
                    Text(text = "다음", fontSize = 30.sp)
                }
            }
        }
    }

}
@OptIn(ExperimentalMaterial3Api::class, ExperimentalComposeUiApi::class)
@Composable
fun GetHeightLogin(name:MutableState<String>,height:MutableState<String>,navController: NavHostController){

    MaterialTheme{
        Surface(
            modifier = Modifier.fillMaxSize(),
            color = MaterialTheme.colorScheme.background
        ) {
            Column() {
                Text(text = "반갑습니다! ${name.value}님\n키를 입력하여 주세요.",
                    modifier = Modifier.padding(30.dp), fontSize = 20.sp , lineHeight = 30.sp)
                NumberTextFieldLogin(item = height, modifier = Modifier
                    .align(CenterHorizontally)
                    .padding(top = 5.dp)
                    .fillMaxWidth(0.9f)
                    .height(80.dp), label = "키")
                Spacer(modifier = Modifier.fillMaxHeight(0.8f))
                Button(shape = RoundedCornerShape(20.dp)
                    ,modifier = Modifier
                        .fillMaxWidth()
                        .fillMaxHeight()
                        .padding(10.dp),onClick = {
                        navController.navigate("Weight")
                    }){
                    Text(text = "다음", fontSize = 30.sp)
                }
            }
        }
    }

}
@OptIn(ExperimentalMaterial3Api::class, ExperimentalComposeUiApi::class)
@Composable
fun GetWeightLogin(name:MutableState<String>,weight:MutableState<String>,navController: NavHostController){

    MaterialTheme{
        Surface(
            modifier = Modifier.fillMaxSize(),
            color = MaterialTheme.colorScheme.background
        ) {
            Column() {
                Text(text = "반갑습니다! ${name.value}님\n키를 입력하여 주세요.",
                    modifier = Modifier.padding(30.dp), fontSize = 20.sp , lineHeight = 30.sp)
                NumberTextFieldLogin(item = weight, modifier = Modifier
                    .align(CenterHorizontally)
                    .padding(top = 5.dp)
                    .fillMaxWidth(0.9f)
                    .height(80.dp), label = "몸무게")
                Spacer(modifier = Modifier.fillMaxHeight(0.8f))
                Button(shape = RoundedCornerShape(20.dp)
                    ,modifier = Modifier
                        .fillMaxWidth()
                        .fillMaxHeight()
                        .padding(10.dp),onClick = {
                            navController.navigate("Gender")
                    }){
                    Text(text = "다음", fontSize = 30.sp)
                }
            }
        }
    }

}
@OptIn(ExperimentalMaterial3Api::class, ExperimentalComposeUiApi::class)
@Composable
fun GetGenderLogin(name:MutableState<String>,gender:MutableState<String>,navController: NavHostController){

    MaterialTheme{
        Surface(
            modifier = Modifier.fillMaxSize(),
            color = MaterialTheme.colorScheme.background
        ) {
            Column() {
                Text(text = "반갑습니다! ${name.value}님\n성별을 입력하여 주세요.",
                    modifier = Modifier.padding(30.dp), fontSize = 20.sp , lineHeight = 30.sp)
                OutlinedTextField(value = gender.value, onValueChange = {gender.value = it  },
                    shape = RoundedCornerShape(30.dp),
                    singleLine = true ,
                    textStyle = TextStyle(fontSize = 30.sp, textAlign = TextAlign.Center),
                    modifier = Modifier
                        .align(CenterHorizontally)
                        .padding(top = 5.dp)
                        .fillMaxWidth(0.9f)
                        .height(80.dp))
                Spacer(modifier = Modifier.fillMaxHeight(0.8f))
                Button(shape = RoundedCornerShape(20.dp)
                    ,modifier = Modifier
                        .fillMaxWidth()
                        .fillMaxHeight()
                        .padding(10.dp),onClick = {
                        navController.navigate("Birth")
                    }){
                    Text(text = "다음", fontSize = 30.sp)
                }
            }
        }
    }

}
@OptIn(ExperimentalMaterial3Api::class, ExperimentalComposeUiApi::class)
@Composable
fun GetBirthLogin(name:MutableState<String>,selectedDate:MutableState<String>,navController: NavHostController){


    MaterialTheme{
        Surface(
            modifier = Modifier.fillMaxSize(),
            color = MaterialTheme.colorScheme.background
        ) {
            Column() {
                Text(text = "반갑습니다! ${name.value}님\n생년월일을 입력하여 주세요.",
                    modifier = Modifier.padding(30.dp), fontSize = 20.sp , lineHeight = 30.sp)
                DatePicker(
                    label = "생일",
                    value = selectedDate.value,
                    onValueChange = { selectedDate.value = it },
                    keyboardOptions = KeyboardOptions(imeAction = ImeAction.Done),
                    keyboardActions = KeyboardActions(onDone = { /* handle keyboard done action */ })
                )
                Spacer(modifier = Modifier.fillMaxHeight(0.8f))
                Button(shape = RoundedCornerShape(20.dp)
                    ,modifier = Modifier
                        .fillMaxWidth()
                        .fillMaxHeight()
                        .padding(10.dp),onClick = { /*TODO*/ }){
                    Text(text = "다음", fontSize = 30.sp)
                }
            }
        }
    }

}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun NumberTextFieldLogin(item: MutableState<String>,modifier:Modifier,label:String){
    OutlinedTextField(value = item.value, onValueChange = {item.value = it },
        shape = RoundedCornerShape(30.dp),
        singleLine = true ,

        textStyle = TextStyle(fontSize = 30.sp, textAlign = TextAlign.Center),
        keyboardOptions = KeyboardOptions(
            keyboardType = KeyboardType.Number,
            imeAction = ImeAction.Done
        ),

        label = { Text(text = label)},
        modifier = modifier)
}
























@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DatePicker(
    label: String,
    value: String,
    onValueChange: (String) -> Unit = {},
    keyboardActions: KeyboardActions = KeyboardActions.Default,
    keyboardOptions: KeyboardOptions = KeyboardOptions.Default,
    pattern: String = "yyyy-MM-dd",
) {
    val formatter = DateTimeFormatter.ofPattern(pattern)
    val date = if (value.isNotBlank()) LocalDate.parse(value, formatter) else LocalDate.now()
    val dialog = DatePickerDialog(
        LocalContext.current,
        { _, year, month, dayOfMonth ->
            onValueChange(LocalDate.of(year, month + 1, dayOfMonth).toString())
        },
        date.year,
        date.monthValue - 1,
        date.dayOfMonth,
    )

    OutlinedTextField(
        value = value,
        onValueChange = { /* handle value change */ },
        enabled = false,
        modifier = Modifier
            .clickable { dialog.show() }
            .fillMaxWidth()
            .padding(20.dp)
            .height(70.dp),
        label = { Text(text = label) },
        shape = RoundedCornerShape(15.dp),
        keyboardOptions = keyboardOptions,
        keyboardActions = keyboardActions
    )
}
