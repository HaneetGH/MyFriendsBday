package com.technorapper.myfriendsbday

import androidx.compose.ui.test.hasText
import androidx.compose.ui.test.junit4.createAndroidComposeRule
import androidx.test.ext.junit.runners.AndroidJUnit4
import dagger.hilt.android.testing.HiltAndroidRule
import dagger.hilt.android.testing.HiltAndroidTest
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

// We can mock and test each and every component but i am showing this small component to demonstrate How test works
@RunWith(AndroidJUnit4::class)
class MainActivityTest {

 @get:Rule
 val composeTestRule = createAndroidComposeRule<MainActivity>()

 @Test
 fun checkButton() {
  composeTestRule
   .onNode(hasText("Convert")) // Equivalent to onNodeWithText("Button")
 }
 @Test
 fun checkTextFiled() {
  composeTestRule
   .onNode(hasText("Amount")) // Equivalent to onNodeWithText("Button")
 }
}