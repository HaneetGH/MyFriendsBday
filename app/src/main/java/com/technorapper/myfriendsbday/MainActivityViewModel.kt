package com.technorapper.myfriendsbday

import androidx.lifecycle.ViewModel
import com.technorapper.myfriendsbday.data.repository.MainActivityRepository
import javax.inject.Inject

class MainActivityViewModel @Inject constructor(
    private val repository: MainActivityRepository
) : ViewModel() {}