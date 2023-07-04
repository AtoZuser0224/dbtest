package com.example.dbtest.composable

import android.app.DatePickerDialog
import androidx.compose.animation.core.Spring
import androidx.compose.animation.core.animateDpAsState
import androidx.compose.animation.core.spring
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowCircleRight
import androidx.compose.material.icons.filled.Edit
import androidx.compose.material3.Button
import androidx.compose.material3.ElevatedCard
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Alignment.Companion.CenterHorizontally
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import java.time.LocalDate
import java.time.format.DateTimeFormatter

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
        composable("Ex"){
            GetExLogin(name = name, navController = navController)
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
            val enable = remember {
                mutableStateOf(false)
            }
            val fieldOffSet by animateDpAsState(targetValue = if(enable.value)0.dp else (-600).dp,
                animationSpec = spring(
                    dampingRatio = Spring.DampingRatioLowBouncy,
                    stiffness = Spring.StiffnessLow
                ))
            LaunchedEffect(Unit){
                enable.value=true
            }
            Column() {
                Text(text = "반갑습니다!\n이름을 입력하여 주세요.",
                    modifier = Modifier.padding(30.dp), fontSize = 20.sp , lineHeight = 30.sp)
                StringTextFieldLogin(item = name, modifier = Modifier
                    .offset(x = fieldOffSet)
                    .align(CenterHorizontally)
                    .padding(top = 5.dp)
                    .fillMaxWidth(0.9f)
                    .height(80.dp))
                Spacer(modifier = Modifier.fillMaxHeight(0.8f))
                GetNextButtonLogin {
                    navController.navigate("Height")
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
            val enable = remember {
                mutableStateOf(false)
            }
            val fieldOffSet by animateDpAsState(targetValue = if(enable.value)0.dp else (-600).dp,
                animationSpec = spring(
                    dampingRatio = Spring.DampingRatioLowBouncy,
                    stiffness = Spring.StiffnessLow
                ))
            LaunchedEffect(Unit){
                enable.value=true
            }
            Column() {
                Text(text = "반갑습니다! ${name.value}님\n키를 입력하여 주세요.",
                    modifier = Modifier
                        .padding(30.dp)
                        .offset(x = fieldOffSet), fontSize = 20.sp , lineHeight = 30.sp)

                NumberTextFieldLogin(item = height, modifier = Modifier
                    .offset(x = fieldOffSet)
                    .align(CenterHorizontally)
                    .padding(top = 5.dp)
                    .fillMaxWidth(0.9f)
                    .height(80.dp), label = "키")

                Spacer(modifier = Modifier.fillMaxHeight(0.8f))

                GetNextButtonLogin {
                    navController.navigate("Weight")
                }
            }
        }
    }

}
@OptIn(ExperimentalMaterial3Api::class, ExperimentalComposeUiApi::class)
@Composable
fun GetWeightLogin(name:MutableState<String>,weight:MutableState<String>,navController: NavHostController){

    MaterialTheme{
        val enable = remember {
            mutableStateOf(false)
        }
        val fieldOffSet by animateDpAsState(targetValue = if(enable.value)0.dp else (-600).dp,
            animationSpec = spring(
                dampingRatio = Spring.DampingRatioLowBouncy,
                stiffness = Spring.StiffnessLow
            ))
        LaunchedEffect(Unit){
            enable.value=true
        }
        Surface(
            modifier = Modifier.fillMaxSize(),
            color = MaterialTheme.colorScheme.background
        ) {
            Column() {
                Text(text = "반갑습니다! ${name.value}님\n키를 입력하여 주세요.",
                    modifier = Modifier
                        .padding(30.dp)
                        .offset(x = fieldOffSet), fontSize = 20.sp , lineHeight = 30.sp)
                NumberTextFieldLogin(item = weight, modifier = Modifier
                    .offset(x = fieldOffSet)
                    .align(CenterHorizontally)
                    .padding(top = 5.dp)
                    .fillMaxWidth(0.9f)
                    .height(80.dp), label = "몸무게")

                Spacer(modifier = Modifier.fillMaxHeight(0.8f))
                GetNextButtonLogin{
                    navController.navigate("Gender")
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
                val enable = remember {
                    mutableStateOf(false)
                }
                val fieldOffSet by animateDpAsState(targetValue = if(enable.value)0.dp else (-600).dp,
                animationSpec = spring(
                    dampingRatio = Spring.DampingRatioLowBouncy,
                    stiffness = Spring.StiffnessLow
                ))
                LaunchedEffect(Unit) {
                    enable.value = true
                }
                Text(text = "반갑습니다! ${name.value}님\n성별을 입력하여 주세요.",
                    modifier = Modifier
                        .padding(30.dp)
                        .offset(x = fieldOffSet), fontSize = 20.sp , lineHeight = 30.sp)
                StringTextFieldLogin(item = gender, modifier = Modifier
                    .align(CenterHorizontally)
                    .padding(top = 5.dp)
                    .fillMaxWidth(0.9f)
                    .height(80.dp)
                    .offset(x = fieldOffSet))
                Spacer(modifier = Modifier.fillMaxHeight(0.8f))
                GetNextButtonLogin {
                    navController.navigate("Birth")
                }
            }
        }
    }

}
@OptIn(ExperimentalMaterial3Api::class, ExperimentalComposeUiApi::class)
@Composable
fun GetBirthLogin(name:MutableState<String>,selectedDate:MutableState<String>,navController: NavHostController){


    MaterialTheme{
        val enable = remember {
            mutableStateOf(false)
        }
        val fieldOffSet by animateDpAsState(targetValue = if(enable.value)0.dp else (-600).dp,
            animationSpec = spring(
                dampingRatio = Spring.DampingRatioLowBouncy,
                stiffness = Spring.StiffnessLow
            ))
        LaunchedEffect(Unit){
            enable.value=true
        }
        Surface(
            modifier = Modifier.fillMaxSize(),
            color = MaterialTheme.colorScheme.background
        ) {
            Column() {
                Text(text = "반갑습니다! ${name.value}님\n생년월일을 입력하여 주세요.",
                    modifier = Modifier
                        .padding(30.dp)
                        .offset(x = fieldOffSet), fontSize = 20.sp , lineHeight = 30.sp)
                DatePicker(
                    label = "생일",
                    value = selectedDate.value,
                    onValueChange = { selectedDate.value = it },
                    keyboardOptions = KeyboardOptions(imeAction = ImeAction.Done),
                    keyboardActions = KeyboardActions(onDone = { /* handle keyboard done action */ },
                    ),
                    modifier = Modifier.offset(x=fieldOffSet)
                )
                Spacer(modifier = Modifier.fillMaxHeight(0.8f))
                GetNextButtonLogin {
                    navController.navigate("Ex")
                }
            }
        }
    }

}



@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun GetExLogin(name:MutableState<String>, navController: NavHostController){
    val scrollState = rememberLazyListState()
    val list = remember {
        mutableStateListOf(Ex("",true))
    }
    val enable = remember {
        mutableStateOf(false)
    }
    val fieldOffSet by animateDpAsState(targetValue = if(enable.value)0.dp else (-600).dp,
        animationSpec = spring(
            dampingRatio = Spring.DampingRatioLowBouncy,
            stiffness = Spring.StiffnessLow
        ))
    LaunchedEffect(Unit){
        enable.value=true
    }
    MaterialTheme{
        Surface(
            modifier = Modifier.fillMaxSize(),
            color = MaterialTheme.colorScheme.background
        ) {
            Column() {
                Text(text = "${name.value}님 마지막 단계 입니다.\n알레르기,지병 등 특이 사항을 적어주세요.",
                    modifier = Modifier.padding(30.dp).offset(x=fieldOffSet), fontSize = 20.sp , lineHeight = 30.sp)
                LazyColumn(
                    modifier = Modifier
                        .fillMaxHeight(0.55f)
                        .fillMaxWidth()
                        .offset(x=fieldOffSet),
                    state = scrollState
                ){
                    itemsIndexed(list){index,item ->
                        if (index!=0){
                            ElevatedCard(modifier = Modifier
                                .align(CenterHorizontally)
                                .padding(horizontal = 15.dp, vertical = 5.dp)
                                .fillMaxWidth()
                                .height(80.dp)) {
                                Row(
                                    verticalAlignment = Alignment.CenterVertically,
                                    modifier = Modifier.fillMaxWidth()
                                ) {
                                    if (list[index].isedit) {
                                        TextField(
                                            value = list[index].ex,
                                            onValueChange = { list[index] = Ex(it, true) },
                                            textStyle = TextStyle(fontSize = 14.sp),
                                            modifier = Modifier
                                                .padding(top = 5.dp)
                                                .weight(0.8f)
                                                .fillMaxHeight(),
                                            maxLines = 4,
                                            colors = TextFieldDefaults.textFieldColors(
                                                focusedIndicatorColor = Color.Transparent,
                                                unfocusedIndicatorColor = Color.Transparent
                                            )
                                        )
                                    } else {
                                        Text(
                                            text = item.ex,
                                            modifier = Modifier
                                                .weight(0.8f)
                                                .fillMaxHeight()
                                                .padding(17.dp)
                                        )
                                    }
                                    IconButton(
                                        onClick = {
                                            list[index] = Ex(list[index].ex, !list[index].isedit)
                                        },
                                        modifier = Modifier
                                            .fillMaxWidth(0.2f)
                                            .fillMaxHeight()
                                            .align(Alignment.CenterVertically)
                                    ) {
                                        if (list[index].isedit){
                                            Icon(imageVector = Icons.Default.ArrowCircleRight,
                                                contentDescription = "arrow",
                                                modifier = Modifier.fillMaxSize(0.5f))
                                        }else{
                                            Icon(
                                                imageVector = Icons.Default.Edit,
                                                contentDescription = "edit",
                                                modifier = Modifier.fillMaxSize(0.5f)
                                            )
                                        }

                                    }
                                }

                            }
                        }
                    }
                    item {
                        ElevatedCard(modifier = Modifier
                            .align(CenterHorizontally)
                            .padding(horizontal = 15.dp, vertical = 5.dp)
                            .fillMaxWidth()
                            .height(80.dp)) {
                            Row() {
                                TextField(value = list[0].ex, onValueChange = {list[0]= Ex(it,true) },
                                    textStyle = TextStyle(fontSize = 14.sp),
                                    modifier = Modifier
                                        .padding(top = 5.dp)
                                        .fillMaxWidth(0.8f)
                                        .fillMaxHeight(),
                                    maxLines = 4,
                                colors = TextFieldDefaults.textFieldColors(
                                    focusedIndicatorColor = Color.Transparent,
                                    unfocusedIndicatorColor = Color.Transparent
                                ))
                                IconButton(onClick = {
                                    list.add(Ex(list[0].ex,false))
                                    list[0]=Ex("",true)
                                },modifier = Modifier.fillMaxSize()) {
                                    Icon(imageVector = Icons.Default.ArrowCircleRight,
                                        contentDescription = "arrow",
                                        modifier = Modifier.fillMaxSize(0.5f))
                                }
                            }
                        }
                    }
                }
                Spacer(modifier = Modifier.fillMaxHeight(0.6f))
                GetNextButtonLogin {

                }
                LaunchedEffect(list.size) {
                    scrollState.animateScrollToItem(list.size - 1)
                }
            }

        }
    }
}
data class Ex(
    var ex:String,
    var isedit:Boolean,
)
@Composable
fun GetNextButtonLogin(modifier: Modifier= Modifier
    .fillMaxWidth()
    .fillMaxHeight()
    .padding(10.dp),onclick :()-> Unit){
    Button(shape = RoundedCornerShape(20.dp)
        ,modifier = modifier,onClick = onclick){
        Text(text = "다음", fontSize = 30.sp)
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
fun StringTextFieldLogin(item: MutableState<String>,modifier:Modifier){
    OutlinedTextField(value = item.value, onValueChange = {item.value = it  },
        shape = RoundedCornerShape(30.dp),
        singleLine = true ,
        textStyle = TextStyle(fontSize = 30.sp, textAlign = TextAlign.Center),
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
    modifier: Modifier
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
        modifier = modifier
            .clickable { dialog.show() }
            .fillMaxWidth()
            .padding(20.dp)
            .height(70.dp)
        ,
        label = { Text(text = label) },
        shape = RoundedCornerShape(15.dp),
        keyboardOptions = keyboardOptions,
        keyboardActions = keyboardActions
    )
}
