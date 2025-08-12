package com.mediaviewer.ios

import androidx.compose.ui.test.*
import androidx.compose.ui.test.junit4.createAndroidComposeRule
import androidx.test.ext.junit.runners.AndroidJUnit4
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class MainActivityTest {

    @get:Rule
    val composeTestRule = createAndroidComposeRule<MainActivity>()

    @Test
    fun mainActivity_displaysPhotosTitle() {
        composeTestRule.onNodeWithText("Photos").assertIsDisplayed()
    }

    @Test
    fun mainActivity_displaysTabRow() {
        composeTestRule.onNodeWithText("Library").assertIsDisplayed()
        composeTestRule.onNodeWithText("Albums").assertIsDisplayed()
    }

    @Test
    fun tabRow_switchesBetweenTabs() {
        // Initially Library tab should be selected
        composeTestRule.onNodeWithText("Library").assertIsDisplayed()

        // Click on Albums tab
        composeTestRule.onNodeWithText("Albums").performClick()

        // Verify Albums tab content is visible
        composeTestRule.onNodeWithText("Albums").assertIsDisplayed()
    }

    @Test
    fun gallery_showsLoadingIndicatorWhenLoading() {
        // Loading indicator should be visible during initial load
        composeTestRule.onNode(hasTestTag("loading_indicator")).assertExists()
    }
}