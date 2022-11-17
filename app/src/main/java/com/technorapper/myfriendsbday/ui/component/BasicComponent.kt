package com.technorapper.myfriendsbday.ui.component

import android.content.Context
import android.widget.Toast
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.GridCells
import androidx.compose.foundation.lazy.LazyVerticalGrid
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.technorapper.myfriendsbday.data.model.CurrencyConvertedListModel
import com.technorapper.myfriendsbday.data.model.CurrencyListModel


@Composable
fun Dropdown(items: List<CurrencyListModel>, myCallback: (result: String?) -> Unit) {
    if (items.isEmpty()) return
    var expanded by remember { mutableStateOf(false) }
    val disabledValue = "B"
    var selectedIndex by remember { mutableStateOf(0) }
    Box(
        modifier = Modifier
            .height(50.dp)
            .width(50.dp)
    ) {
        Text(
            text = items[selectedIndex].currency,
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
            modifier = Modifier
                .height(150.dp)
                .width(250.dp)
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
                    myCallback.invoke(s.currency)
                    Text(text = s.currency + disabledText)
                }
            }
        }
    }
}

@OptIn(ExperimentalFoundationApi::class, ExperimentalMaterialApi::class)
@Composable
fun gridView(context: Context, list: List<CurrencyConvertedListModel>) {


    // on below line we are adding lazy
    // vertical grid for creating a grid view.
    LazyVerticalGrid(
        // on below line we are setting the
        // column count for our grid view.
        cells = GridCells.Fixed(2),
        // on below line we are adding padding
        // from all sides to our grid view.
        modifier = Modifier.padding(10.dp)
    ) {
        // on below line we are displaying our
        // items upto the size of the list.
        items(list.size) {
            // on below line we are creating a
            // card for each item of our grid view.
            Card(
                // inside our grid view on below line we are
                // adding on click for each item of our grid view.
                onClick = {
                    // inside on click we are displaying the toast message.
                    Toast.makeText(
                        context, list[it].from + " selected..", Toast.LENGTH_SHORT
                    ).show()
                },

                // on below line we are adding padding from our all sides.
                modifier = Modifier.padding(8.dp),

                // on below line we are adding elevation for the card.
                elevation = 6.dp
            ) {
                // on below line we are creating a column on below sides.
                Column(
                    // on below line we are adding padding
                    // padding for our column and filling the max size.
                    Modifier
                        .fillMaxSize()
                        .padding(5.dp),

                    // on below line we are adding
                    // horizontal alignment for our column
                    horizontalAlignment = Alignment.CenterHorizontally,

                    // on below line we are adding
                    // vertical arrangement for our column
                    verticalArrangement = Arrangement.Center
                ) {
                    // on below line we are creating image for our grid view item.


                    // on the below line we are adding a spacer.
                    Spacer(modifier = Modifier.height(9.dp))

                    // on below line we are creating
                    // a text for our grid view item
                    Column() {
                        Text(text = list[it].to + " " + list[it].Conversion)
                    }
                }
            }
        }
    }
}
