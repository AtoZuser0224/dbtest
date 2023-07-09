package com.example.dbtest.composable.Login

import androidx.compose.animation.core.Spring
import androidx.compose.animation.core.animateDpAsState
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.spring
import androidx.compose.animation.core.tween
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Edit
import androidx.compose.material.icons.filled.Send
import androidx.compose.material.icons.outlined.Done
import androidx.compose.material3.ElevatedCard
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.snapshots.SnapshotStateList
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.example.dbtest.R
import com.example.dbtest.composable.widget.GetNextButtonLogin

data class Ex(
    var ex:String,
    var isedit:Boolean,
)

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun GetExLogin(name: MutableState<String>,list:SnapshotStateList<Ex>, navController: NavHostController){
    val scrollState = rememberLazyListState()

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
    MaterialTheme {
        Surface(
            modifier = Modifier.fillMaxSize(),
            color = MaterialTheme.colorScheme.background
        ) {
            Column() {

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
                    Image(
                        modifier = Modifier
                            .size(100.dp)
                            .padding(top = 25.dp)
                            .graphicsLayer(
                                rotationZ = turnLogo
                            ),
                        painter = painterResource(id = R.drawable.openai),
                        contentDescription = "chatgpt"
                    )
                    Text(
                        text = "초기 설정",
                        modifier = Modifier.padding(top = 15.dp),
                        fontWeight = FontWeight.Light,
                        fontSize = 28.sp,
                        lineHeight = 10.sp
                    )
                    Text(
                        text = "당신의 특이사항을 입력하여 주세요.",
                        modifier = Modifier.padding(5.dp),
                        fontWeight = FontWeight.ExtraLight,
                        fontSize = 18.sp,
                        lineHeight = 30.sp
                    )
                    LazyColumn(
                        modifier = Modifier
                            .fillMaxHeight(0.55f)
                            .fillMaxWidth()
                            .offset(x = fieldOffSet),
                        state = scrollState
                    ) {
                        itemsIndexed(list) { index, item ->
                            if (index != 0) {
                                ElevatedCard(
                                    modifier = Modifier
                                        .align(Alignment.CenterHorizontally)
                                        .padding(horizontal = 15.dp, vertical = 5.dp)
                                        .fillMaxWidth()
                                        .height(80.dp)
                                ) {
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
                                                list[index] =
                                                    Ex(list[index].ex, !list[index].isedit)
                                            },
                                            modifier = Modifier
                                                .fillMaxWidth(0.2f)
                                                .fillMaxHeight()
                                                .align(Alignment.CenterVertically)
                                        ) {
                                            if (list[index].isedit) {
                                                Icon(
                                                    imageVector = Icons.Outlined.Done,
                                                    contentDescription = "arrow",
                                                    modifier = Modifier.fillMaxSize(0.5f)
                                                )
                                            } else {
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
                        item {//리스트를 추가하는 텍스트필드
                            ElevatedCard(
                                modifier = Modifier
                                    .align(Alignment.CenterHorizontally)
                                    .padding(horizontal = 15.dp, vertical = 5.dp)
                                    .fillMaxWidth()
                                    .height(80.dp)
                            ) {
                                Row() {
                                    TextField(
                                        value = list[0].ex,
                                        onValueChange = { list[0] = Ex(it, true) },
                                        textStyle = TextStyle(fontSize = 14.sp),
                                        modifier = Modifier
                                            .padding(top = 5.dp)
                                            .fillMaxWidth(0.8f)
                                            .fillMaxHeight(),
                                        maxLines = 4,
                                        colors = TextFieldDefaults.textFieldColors(
                                            focusedIndicatorColor = Color.Transparent,
                                            unfocusedIndicatorColor = Color.Transparent
                                        )
                                    )
                                    IconButton(onClick = {
                                        list.add(Ex(list[0].ex, false))
                                        list[0] = Ex("", true)
                                    }, modifier = Modifier.fillMaxSize()) {
                                        Icon(
                                            imageVector = Icons.Default.Send,
                                            contentDescription = "arrow",
                                            modifier = Modifier.fillMaxSize(0.5f)
                                        )
                                    }
                                }
                            }
                        }
                    }
                    Spacer(modifier = Modifier.fillMaxHeight(0.6f))
                    Row() {
                        Text(
                            text = "귀하의 정보는 귀하의 디바이스에\n저장되며 당사는 확인하지 않습니다.",
                            modifier = Modifier.fillMaxWidth(0.6f).padding(top=38.dp, start = 24.dp), lineHeight = 15.sp, fontSize = 12.sp,
                            color = MaterialTheme.colorScheme.primary
                        )
                        GetNextButtonLogin {
                            navController.navigate("End"){
                                popUpTo("Ex"){
                                    inclusive = true
                                }
                            }
                        }
                    }

                    LaunchedEffect(list.size) {//버튼을 눌렀을때 리스트를 아래로 강제로 내리는 구문//
                        scrollState.animateScrollToItem(list.size - 1)
                    }
                }
            }
        }
    }}