package com.example.dbtest.composable.main

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.dbtest.R

@OptIn(ExperimentalMaterial3Api::class)
@Composable
@Preview
fun Main(){
    MaterialTheme {


        Surface(
            modifier = Modifier.fillMaxSize(),
            color = MaterialTheme.colorScheme.background
        ) {
            Column() {
                val state = rememberScrollState()
                Column(horizontalAlignment = Alignment.CenterHorizontally,
                        verticalArrangement = Arrangement.Top,
                        modifier = Modifier
                            .fillMaxWidth()
                            .fillMaxHeight((0.3f - (state.value * state.value) / 100000f))
                            .alpha(1f - (state.value * state.value) / 50000f)) {//Header
                    Image(painter = painterResource(id = R.drawable.openai), contentDescription = "openai",modifier= Modifier
                        .padding(vertical = 20.dp)
                        .fillMaxHeight(0.3f))
                    Text(text = "당신의 점수는", fontWeight = FontWeight.ExtraLight, fontSize = 26.sp,modifier = Modifier
                        .padding(top = 10.dp)
                        .fillMaxHeight(0.3f))
                    Text(text = "${((state.value*state.value)/50000f)}/100점",modifier = Modifier
                        .fillMaxHeight(0.3f))
                }

                Column(modifier = Modifier
                    .background(Color.Gray, RoundedCornerShape(20.dp))
                    .fillMaxWidth()
                    .fillMaxHeight()
                    .verticalScroll(state)//Content
                    ) {
                    Column(Modifier.graphicsLayer {
                        translationY=if (state.value<185)(state.value).toFloat()else 0f
                    }) {

                    }
                }
            }
        }
    }
}
