package com.technorapper.myfriendsbday.ui.component

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.material.Divider
import androidx.compose.material.Icon
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.CheckCircle

import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusState
import androidx.compose.ui.focus.onFocusEvent
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.technorapper.myfriendsbday.EdittextStateCl
import com.technorapper.myfriendsbday.R

data class EditTextState(
    val hint: String,
    val isError: Boolean,
    val maxLines: Int = 1,
    val tailingResId: Int,
    val errorResId: Int,
    val errorMessage: String = "Something Went Wrong!",
    val isValid: Boolean = false
)

@Composable
fun PTEditText(
    state: EdittextStateCl,
    onTextChanged: (String) -> Unit = {},
    onFocusChanged: (isFocused: Boolean) -> Unit = {},
) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(start = 16.dp, end = 16.dp)
            .height(75.dp)
    ) {
        var editTextValue by remember { mutableStateOf(TextFieldValue("")) }
        var isHintVisible by remember { mutableStateOf(false) }
        var isEditTextFocused by remember { mutableStateOf(false) }
        if (isHintVisible) {
            Text(
                text = state.edittextState.hint,
                style = MaterialTheme.typography.body2,
                color = if (state.edittextState.isError) {
                    Color.Red
                } else {
                    Color.Unspecified
                }
            )
            Spacer(modifier = Modifier.height(5.dp))
        } else {
            Spacer(
                modifier = Modifier
                    .width(200.dp)
                    .height(19.dp)
            )
        }
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .wrapContentHeight(),
            contentAlignment = Alignment.Center
        ) {
            BasicTextField(value = editTextValue,
                onValueChange = {
                    editTextValue = it
                    onTextChanged.invoke(it.text)
                },
                modifier = Modifier
                    .fillMaxWidth()
                    .wrapContentHeight()
                    .onFocusEvent { ft: FocusState ->
                        if (ft.isFocused) {
                            isHintVisible = true
                        }
                        isEditTextFocused = ft.isFocused
                        onFocusChanged.invoke(ft.isFocused)
                    },
                singleLine = state.edittextState.maxLines == 1,
                maxLines = state.edittextState.maxLines,
                decorationBox = { innerTextField ->
                    Row(
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        if (editTextValue.text.isEmpty()) {
                            isHintVisible = false
                            if (!isEditTextFocused) {
                                Text(
                                    text = state.edittextState.hint,
                                    style = MaterialTheme.typography.body2,
                                    color = if (state.edittextState.isError) {
                                        Color.Red
                                    } else {
                                        Color.Unspecified
                                    },
                                )
                            }
                        }
                        innerTextField() //<-- Add this
                    }
                })
            if (state.edittextState.isValid) {
                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(end = 8.dp)
                        .wrapContentHeight(),
                    contentAlignment = Alignment.CenterEnd
                ) {
                    Icon(
                        contentDescription = "",
                        modifier = Modifier.size(16.dp),
                        imageVector = Icons.Filled.CheckCircle,
                        tint = Color.Green
                    )
                }
            }
        }
        Spacer(modifier = Modifier.height(6.dp))
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .wrapContentHeight(),
            contentAlignment = Alignment.Center
        ) {
            Divider(
                color = if (state.edittextState.isError) {
                    Color.Red
                } else {
                    Color.LightGray
                },
            )
        }
        if (state.edittextState.isError) {
            Spacer(modifier = Modifier.height(3.dp))
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .wrapContentHeight(),
                horizontalAlignment = Alignment.End
            ) {
                Row {
                    Icon(
                        contentDescription = "",
                        modifier = Modifier.size(16.dp),
                        imageVector = Icons.Filled.CheckCircle,
                        tint = Color.Red
                    )
                    Spacer(modifier = Modifier.width(14.dp))
                    Text(
                        state.edittextState.errorMessage, color = Color.Red, maxLines = 1
                    )
                }
            }
        }
    }
}

@Preview
@Composable
fun PTEditTextPreview() {
    val state = EditTextState(
        hint = "Name",
        isError = false,
        errorMessage = "are you sure this is valid name?",
        tailingResId = R.drawable.ic_launcher_foreground,
        errorResId = R.drawable.ic_launcher_foreground,
        isValid = true
    )
    Column {
        PTEditText(state = remember {
            EdittextStateCl(
                state.copy(
                    isError = true, isValid = false
                )
            )
        }, onTextChanged = {
            // value should be collected from here
        }, onFocusChanged = {
            // should do validation and error state step up here
        })
        PTEditText(state = remember { EdittextStateCl(state) }, onTextChanged = {
            // value should be collected from here
        }, onFocusChanged = {
            // should do validation and error state step up here
        })
    }

}

