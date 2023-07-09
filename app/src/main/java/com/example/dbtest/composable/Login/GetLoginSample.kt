package com.example.dbtest.composable.Login

import androidx.compose.animation.core.Spring
import androidx.compose.animation.core.animateDpAsState
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.spring
import androidx.compose.animation.core.tween
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
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
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.example.dbtest.R
import com.example.dbtest.composable.widget.DatePicker
import com.example.dbtest.composable.widget.GetNextButtonLogin
import com.example.dbtest.composable.widget.TextFieldLogin

@Composable
fun GetLoginSample(propertyName:String, nextProperty:String, Field:String, property: MutableState<String>, navController: NavHostController){
    MaterialTheme {

        Surface(
            modifier = Modifier.fillMaxSize(),
            color = MaterialTheme.colorScheme.background
        ) {
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
            val turnLogo by animateFloatAsState(
                targetValue = if (enable.value) 0f else -1080f,
                animationSpec = tween(
                    durationMillis = 1200,
                )
            )
            LaunchedEffect(Unit) {
                enable.value = true
            }
            Column(horizontalAlignment = Alignment.CenterHorizontally) {
                Image(modifier = Modifier.size(100.dp)
                    .padding(top = 25.dp)
                    .graphicsLayer(
                        rotationZ = turnLogo
                    )
                    ,painter = painterResource(id = R.drawable.openai), contentDescription = "chatgpt")
                Text(
                    text = "초기 설정",
                    modifier = Modifier.padding(top=15.dp), fontWeight = FontWeight.Light, fontSize = 28.sp, lineHeight = 10.sp
                )
                Text(
                    text = "당신의 ${propertyName}를 입력하여 주세요.",
                    modifier = Modifier.padding(5.dp), fontWeight = FontWeight.ExtraLight, fontSize = 18.sp, lineHeight = 30.sp
                )
                Text(
                    text = propertyName,
                    modifier = Modifier.padding(top = 10.dp, start = 30.dp).fillMaxWidth(), textAlign = TextAlign.Start, fontWeight = FontWeight.ExtraLight, fontSize = 18.sp
                )
                if (Field == "String"){
                    TextFieldLogin(
                        item = property ,modifier = Modifier
                            .offset(x = fieldOffSet)
                            .align(Alignment.CenterHorizontally)
                            .padding()
                            .fillMaxWidth(0.9f)
                            .height(70.dp),
                        isString = true
                    )
                }else if(Field == "Number"){
                    TextFieldLogin(
                        item = property, modifier = Modifier
                            .offset(x = fieldOffSet)
                            .align(Alignment.CenterHorizontally)
                            .fillMaxWidth(0.9f)
                            .height(70.dp),
                        isString = false
                    )
                }else{
                    DatePicker(
                        label = propertyName,
                        value = property.value,
                        onValueChange = { property.value = it },
                        keyboardOptions = KeyboardOptions(imeAction = ImeAction.Done),
                        keyboardActions = KeyboardActions(
                            onDone = { /* handle keyboard done action */ },
                        ),
                        modifier = Modifier.offset(x = fieldOffSet)
                    )
                }

                Text(
                    text = "귀하의 정보는 귀하의 디바이스에\n저장되며 당사는 확인하지 않습니다.",
                    modifier = Modifier.fillMaxWidth().padding(24.dp), lineHeight = 15.sp, fontSize = 12.sp,
                    color = MaterialTheme.colorScheme.primary
                )
                Spacer(modifier = Modifier.fillMaxHeight(0.76f))
                GetNextButtonLogin {
                    navController.navigate(nextProperty)
                }
            }
        }
    }

}