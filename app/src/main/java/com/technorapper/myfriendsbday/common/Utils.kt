package com.technorapper.myfriendsbday.common

import java.util.*

class Utils {
    companion object {
        fun isSyncUnable(sync: Long): Boolean {
            val now = Calendar.getInstance().timeInMillis
            val last = Calendar.getInstance()
            last.timeInMillis = sync
            last.add(Calendar.MINUTE, 30);
            return last.timeInMillis > now
        }
    }
}