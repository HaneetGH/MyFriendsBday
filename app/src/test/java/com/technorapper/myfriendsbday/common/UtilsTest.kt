package com.technorapper.myfriendsbday.common

import junit.framework.TestCase
import org.junit.Test
import java.util.Calendar


class UtilsTest : TestCase() {

    @Test
    fun test_isSyncUnable() {
        var current = Calendar.getInstance()
        current.timeInMillis = current.timeInMillis - 18000

        assert(Utils.isSyncUnable(current.timeInMillis))
    }
}