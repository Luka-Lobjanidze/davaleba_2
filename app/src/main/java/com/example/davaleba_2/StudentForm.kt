package com.example.davaleba_2

import android.os.Bundle
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.DateRange
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.davaleba_2.ui.theme.Davaleba_2Theme
import java.text.SimpleDateFormat
import java.util.*



@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun StudentFormScreen() {
    val context = LocalContext.current
    var name by remember { mutableStateOf("") }
    var email by remember { mutableStateOf("") }
    var selectedDate by remember { mutableStateOf("") }

    var selectedPlatform by remember {
        mutableStateOf("")
    }

    var showDatePicker by remember {
        mutableStateOf(false)
    }
    var agreedToTerms by remember {
        mutableStateOf(false)
    }

    val datePickerState = rememberDatePickerState()

    Scaffold(

        bottomBar = {

            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(16.dp)
            ) {

                Button(
                    onClick = {
                        if (name!=""&&selectedDate!=""&&email!=""&&selectedPlatform!=""&&agreedToTerms!=false){
                            Toast.makeText(
                                context,
                                "მონაცემები გაიგზავნა!",
                                Toast.LENGTH_SHORT
                            ).show()


                        }
                        else{
                            Toast.makeText(
                                context,
                                "შეავსეთ ყველა ველი!",
                                Toast.LENGTH_SHORT
                            ).show()
                        }

                    },
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(55.dp),
                    shape = RoundedCornerShape(30.dp),
                    colors = ButtonDefaults.buttonColors(
                        containerColor = Color(0xFFFBDB29)
                    )
                ) {

                    Text(
                        text = "Submit",
                        fontSize = 16.sp
                    )
                }
            }
        }

    ) { paddingValues ->

        Column(
            modifier = Modifier
                .fillMaxSize()
                .background(Color(0xFFF0F0F0))
                .verticalScroll(rememberScrollState())
                .padding(paddingValues)
        ) {

            // HEADER
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .background(Color(0xFFFDDD29))
                    .padding(20.dp)
            ) {

                Text(
                    text = "Student Form",
                    color = Color.White,
                    fontSize = 24.sp,
                    fontWeight = FontWeight.Bold
                )

                Spacer(modifier = Modifier.height(4.dp))

                Text(
                    text = "Fill in your details",
                    color = Color.White.copy(alpha = 0.8f)
                )
            }

            Spacer(modifier = Modifier.height(20.dp))

            Column(
                modifier = Modifier
                    .padding(horizontal = 16.dp)
            ) {

                Surface(
                    modifier = Modifier
                        .padding(start = 20.dp),
                    color = Color.White.copy(0.0f)
                ) {
                    Text(
                        text = "Your Name",
                        fontWeight = FontWeight.SemiBold,

                    )
                }
                Spacer(modifier = Modifier.height(10.dp))

                OutlinedTextField(
                    value = name,
                    onValueChange = {
                        name = it
                    },
                    placeholder = {
                        Text("Enter your name")
                    },
                    modifier = Modifier.fillMaxWidth(),
                    shape = RoundedCornerShape(30.dp)
                )

                Spacer(modifier = Modifier.height(30.dp))

                Surface(
                    modifier = Modifier
                        .padding(start = 20.dp),
                    color = Color.White.copy(0.0f)
                    ) {
                    Text(
                        text = "Select Date",
                        fontWeight = FontWeight.SemiBold
                    )
                }

                Spacer(modifier = Modifier.height(10.dp))

                OutlinedTextField(
                    value = selectedDate,
                    onValueChange = {},
                    readOnly = true,
                    placeholder = {
                        Text("Pick a date")
                    },
                    trailingIcon = {

                        IconButton(
                            onClick = {
                                showDatePicker = true
                            }
                        ) {

                            Icon(
                                imageVector = Icons.Default.DateRange,
                                contentDescription = null
                            )
                        }
                    },
                    modifier = Modifier.fillMaxWidth(),
                    shape = RoundedCornerShape(30.dp)
                )

                Spacer(modifier = Modifier.height(30.dp))

                Surface(
                    modifier = Modifier
                        .padding(start = 20.dp),
                    color = Color.White.copy(0.0f)
                    ) {
                    Text(
                        text = "Email Address",
                        fontWeight = FontWeight.SemiBold
                    )
                }

                Spacer(modifier = Modifier.height(10.dp))

                OutlinedTextField(
                    value = email,
                    onValueChange = {
                        email = it
                    },
                    placeholder = {
                        Text("your.email@example.com")
                    },
                    modifier = Modifier.fillMaxWidth(),
                    shape = RoundedCornerShape(30.dp)
                )

                Spacer(modifier = Modifier.height(30.dp))

                // RADIO QUESTION
                Text(
                    text = "რომელია შენი ფავორიტი მიმართულება?",
                    fontWeight = FontWeight.Bold,
                    fontSize = 16.sp
                )

                Spacer(modifier = Modifier.height(12.dp))

                val options = listOf(
                    "Android",
                    "iOS",
                    "Web"
                )

                options.forEach { option ->

                    Row(
                        verticalAlignment = Alignment.CenterVertically
                    ) {

                        RadioButton(
                            selected = selectedPlatform == option,
                            onClick = {
                                selectedPlatform = option
                            }
                        )

                        Text(text = option)
                    }
                }
                Spacer(modifier = Modifier.height(30.dp))

                Row(
                    verticalAlignment = Alignment.CenterVertically
                ) {

                    Switch(
                        checked = agreedToTerms,
                        onCheckedChange = {
                            agreedToTerms = it
                        }
                    )

                    Spacer(modifier = Modifier.width(8.dp))

                    Text(
                        text = "ვეთანხმები წესებს და პირობებს"
                    )
                }


            }
        }
    }

    // DATE PICKER
    if (showDatePicker) {

        DatePickerDialog(
            onDismissRequest = {
                showDatePicker = false
            },
            confirmButton = {

                TextButton(
                    onClick = {

                        datePickerState.selectedDateMillis?.let { millis ->

                            val formatter = SimpleDateFormat(
                                "MMMM dd, yyyy",
                                Locale.getDefault()
                            )

                            selectedDate =
                                formatter.format(Date(millis))
                        }

                        showDatePicker = false
                    }
                ) {

                    Text("OK")
                }
            },
            dismissButton = {

                TextButton(
                    onClick = {
                        showDatePicker = false
                    }
                ) {

                    Text("Cancel")
                }
            }
        ) {

            DatePicker(
                state = datePickerState
            )
        }
    }
}

@Preview
@Composable
fun StudentFormScreenPreview(){
    Davaleba_2Theme() {
        StudentFormScreen()
    }
}