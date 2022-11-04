package com.technorapper.myfriendsbday.ui.dataList

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.lifecycle.Observer
import com.technorapper.myfriendsbday.data.db.model.UserInfoModel
import com.technorapper.myfriendsbday.domain.DataState
import com.technorapper.myfriendsbday.domain.Task
import com.technorapper.myfriendsbday.ui.component.list.LabsList
import com.technorapper.myfriendsbday.ui.theme.MyFriendsBdayTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class UserListActivity : ComponentActivity() {
    private val viewModel by viewModels<UserListViewModel>()
    lateinit var dateState: DateStateList
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            dateState = remember { DateStateList(emptyList()) }
            MyFriendsBdayTheme {
                Column {
                    dateState.dateStateChangeList.forEach { item ->
                        Text(text = item.name, color = Color.White)
                    }
                }
                ConstraintLayout(modifier = Modifier.fillMaxSize()) {
                    val column = createRef()


                    Column(
                        Modifier
                            .background(MaterialTheme.colors.background)
                            .fillMaxSize(),
                        horizontalAlignment = Alignment.CenterHorizontally,
                        verticalArrangement = Arrangement.Center
                    ) {

                        LabsList(true, dateState.dateStateChangeList!!) { lab ->

                        }
                    }
                }
            }
        }
        viewModel.getUiState().observe(this, Observer {
            var res = it as DataState
            when (res) {
                is DataState.Success<*> -> {
                    var data = res.data as DataState
                    when (res.task) {
                        Task.GET -> {
                            if (this::dateState.isInitialized) {
                                dateState.dateStateChangeList =
                                    (data as DataState.Success<*>).data as List<UserInfoModel>
                            }
                        }
                    }
                }
            }

        })
        viewModel.setStateEvent(MainStateEvent.getAllData)

    }
}

class DateStateList(date: List<UserInfoModel>) {
    var dateStateChangeList by mutableStateOf(date)
}