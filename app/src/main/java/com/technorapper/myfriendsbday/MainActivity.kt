@file:OptIn(ExperimentalAnimationApi::class)

package com.technorapper.myfriendsbday

import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.material.rememberScaffoldState
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.technorapper.myfriendsbday.ui.component.DatePickerCompose
import com.technorapper.myfriendsbday.ui.component.EditTextState
import com.technorapper.myfriendsbday.ui.component.PTEditText
import com.technorapper.myfriendsbday.ui.theme.MyFriendsBdayTheme
import dagger.hilt.android.AndroidEntryPoint
import dagger.hilt.android.HiltAndroidApp

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    lateinit var editTextState: EdittextStateCl
    lateinit var dateState: DateState
    lateinit var textChangeState: TextChangeState
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MyFriendsBdayTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(), color = MaterialTheme.colors.background
                ) {
                    editTextState = remember {
                        EdittextStateCl(
                            EditTextState(
                                "Name", false, 1, 1, 1, "error", true
                            )
                        )
                    }
                    dateState = remember {
                        DateState("")
                    }
                    textChangeState = remember {
                        TextChangeState("")
                    }
                    Column() {
                        PTEditText(editTextState,
                            onTextChanged = { textChangeState.textChange = it },
                            onFocusChanged = {})
                        DatePickerCompose(dateState)

                    }
                }

                Log.d("Data", textChangeState.textChange + "--" + dateState.dateStateChange)
            }
        }
    }
}

@Composable
fun Greeting(name: String) {
    Text(text = "Hello $name!")
}

@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    MyFriendsBdayTheme {
        Greeting("Android")
    }
}

class EdittextStateCl(edittext: EditTextState) {
    var edittextState by mutableStateOf(edittext)
}

class DateState(date: String) {
    var dateStateChange by mutableStateOf(date)
}

class TextChangeState(state: String) {
    var textChange by mutableStateOf(state)
}
