package com.technorapper.myfriendsbday.ui.component

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material.DropdownMenu
import androidx.compose.material.DropdownMenuItem
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.material.Text
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.technorapper.myfriendsbday.data.model.CurrencyListModel


@Composable
fun Dropdown(items: List<CurrencyListModel>) {
    if(items.isEmpty()) return
    var expanded by remember { mutableStateOf(false) }
    val disabledValue = "B"
    var selectedIndex by remember { mutableStateOf(0) }
    Box(
        modifier = Modifier.height(50.dp).width(50.dp)
    ) {
        Text(
            text=items[selectedIndex].currency,
            modifier = Modifier
                .fillMaxWidth()
                .clickable(onClick = { expanded = true })
                .background(
                    Color.Gray
                )
        )
        DropdownMenu(
            expanded = expanded,
            onDismissRequest = { expanded = false },
            modifier =
                    Modifier.height(150.dp).width(250.dp)
                .background(
                    Color.Red
                )
        ) {
            items.forEachIndexed { index, s ->
                DropdownMenuItem(onClick = {
                    selectedIndex = index
                    expanded = false
                }) {
                    val disabledText = if (s.currency == disabledValue) {
                        " (Disabled)"
                    } else {
                        ""
                    }
                    Text(text = s.currency + disabledText)
                }
            }
        }
    }
}
