package com.example.dbtest.composable.widget

import android.app.DatePickerDialog
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import java.time.LocalDate
import java.time.format.DateTimeFormatter

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun StringTextFieldLogin(item: MutableState<String>, modifier: Modifier){
    OutlinedTextField(
        value = item.value, onValueChange = { item.value = it },
        shape = RoundedCornerShape(30.dp),
        singleLine = true,
        textStyle = TextStyle(fontSize = 30.sp, textAlign = TextAlign.Center),
        modifier = modifier
    )
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
            .height(70.dp),
        label = { Text(text = label) },
        shape = RoundedCornerShape(15.dp),
        keyboardOptions = keyboardOptions,
        keyboardActions = keyboardActions
    )
}

@Composable
fun GetNextButtonLogin(modifier: Modifier = Modifier, onclick :()-> Unit){
    Button(
        shape = RoundedCornerShape(20.dp),
        modifier = modifier.fillMaxWidth().fillMaxHeight().padding(7.dp),
        onClick = onclick
    ) {
        Text(text = "다음", fontSize = 30.sp)
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun NumberTextFieldLogin(item: MutableState<String>, modifier: Modifier, label:String){
    OutlinedTextField(
        value = item.value, onValueChange = { item.value = it },
        shape = RoundedCornerShape(30.dp),
        singleLine = true,

        textStyle = TextStyle(fontSize = 30.sp, textAlign = TextAlign.Center),
        keyboardOptions = KeyboardOptions(
            keyboardType = KeyboardType.Number,
            imeAction = ImeAction.Done
        ),

        label = { Text(text = label) },
        modifier = modifier
    )
}