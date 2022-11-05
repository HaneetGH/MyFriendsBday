package com.technorapper.myfriendsbday.ui.component

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.material.DropdownMenu
import androidx.compose.material.DropdownMenuItem
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.input.KeyboardType.Companion.Text
import com.technorapper.myfriendsbday.data.model.CurrencyListModel


@Composable
fun Dropdown(items: List<CurrencyListModel>) {
    var expanded by remember { mutableStateOf(false) }
    val disabledValue = "B"
    var selectedIndex by remember { mutableStateOf(0) }

}
