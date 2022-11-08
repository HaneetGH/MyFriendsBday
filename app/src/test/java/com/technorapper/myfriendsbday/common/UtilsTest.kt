package com.technorapper.myfriendsbday.common

import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.Test
import java.util.*


class UtilsTest {

    @Test
    fun test_isSyncUnable_Pass() {
        var current = Calendar.getInstance()
        current.timeInMillis = current.timeInMillis - 18000

        assert(Utils.isSyncUnable(current.timeInMillis))
    }

    @Test
    fun test_isSyncUnable_Fail() {
        var current = Calendar.getInstance()
        current.timeInMillis = current.timeInMillis + 1800

        assert(Utils.isSyncUnable(current.timeInMillis))
    }
}