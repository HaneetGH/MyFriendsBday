package com.technorapper.myfriendsbday

import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.interaction.Interaction
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.interaction.PressInteraction
import androidx.compose.foundation.interaction.PressInteraction.Press
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.SolidColor
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.lifecycle.Observer
import com.technorapper.myfriendsbday.domain.DataState
import com.technorapper.myfriendsbday.ui.component.DatePickerCompose
import com.technorapper.myfriendsbday.ui.component.EditTextState
import com.technorapper.myfriendsbday.ui.component.PTEditText
import com.technorapper.myfriendsbday.ui.theme.MyFriendsBdayTheme
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collect
@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    lateinit var editTextState: EdittextStateCl
    lateinit var dateState: DateState
    lateinit var textChangeState: TextChangeState
    private val viewModel by viewModels<MainActivityViewModel>()
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
                    Column {
                        PTEditText(editTextState,
                            onTextChanged = { textChangeState.textChange = it },
                            onFocusChanged = {})
                        DatePickerCompose(dateState)


                        val interactionSource = remember { MutableInteractionSource() }
                        MyButton(interactionSource)
                        LaunchedEffect(interactionSource) {
                            interactionSource.interactions.collect { interaction ->
                                when (interaction) {
                                    is PressInteraction.Press -> {
                                        viewModel.setStateEvent(
                                            MainStateEvent.SaveData(
                                                textChangeState.textChange,
                                                dateState.dateStateChange
                                            )
                                        )
                                        viewModel.setStateEvent(
                                            MainStateEvent.getAllData
                                        )
                                    }
                                }
                            }
                        }
                    }
                }

                Log.d("Data", textChangeState.textChange + "--" + dateState.dateStateChange)
            }
        }
        viewModel.getUiState().observe(this, Observer {
            var res = it as DataState
            when(res)
            {
                is DataState.Success<*> ->{
                    Log.d("DataState",it.toString())
                }
            }
        })
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

@Composable
fun MyButton(interactionSource: MutableInteractionSource) {

    Column(

        // below line is used for specifying
        // vertical arrangement.
        verticalArrangement = Arrangement.Center,

        // below line is used for specifying
        // horizontal arrangement.
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        // below line is use to get
        // the context for our app.
        val context = LocalContext.current

        // below line is use to create a button.
        Button(
            interactionSource = interactionSource,
            // below line is use to add onclick
            // parameter for our button onclick
            onClick = {
                // when user is clicking the button
                // we are displaying a toast message.
                Toast.makeText(context, "Welcome to Geeks for Geeks", Toast.LENGTH_LONG).show()
            },
            // in below line we are using modifier
            // which is use to add padding to our button
            modifier = Modifier.padding(all = Dp(10F)),

            // below line is use to set or
            // button as enable or disable.
            enabled = true,

            // below line is use to
            // add border to our button.
            border = BorderStroke(width = 1.dp, brush = SolidColor(Color.Blue)),

            // below line is use to add shape for our button.
            shape = MaterialTheme.shapes.medium,
        )
        // below line is use to
        // add text on our button
        {
            Text(text = "Save Data", color = Color.White)
        }
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

