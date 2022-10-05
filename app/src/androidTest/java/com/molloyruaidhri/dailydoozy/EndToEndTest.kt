package com.molloyruaidhri.dailydoozy

import androidx.compose.ui.test.*
import androidx.compose.ui.test.junit4.createAndroidComposeRule
import androidx.test.ext.junit.runners.AndroidJUnit4
import dagger.hilt.android.testing.HiltAndroidRule
import dagger.hilt.android.testing.HiltAndroidTest
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@HiltAndroidTest
@RunWith(AndroidJUnit4::class)
class EndToEndTest {

    @get:Rule(order = 0)
    var hiltRule = HiltAndroidRule(this)

    @get:Rule(order = 1)
    val composeRule = createAndroidComposeRule(MainActivity::class.java)

    @Before
    fun setup() {
        hiltRule.inject()
    }

    @Test
    fun noAnswerSubmitted() {
        // 1. Verify one question placeholder and four answer placeholders exist
        composeRule
            .onNodeWithTag("Question Placeholder")
            .assertExists()
        composeRule
            .onAllNodesWithTag("Answer Placeholder")
            .assertCountEquals(4)

        // 2. Test "Submit Answer" button exists (requires API network request hence wait-until)
        composeRule.waitUntil(timeoutMillis = 10000) {
            composeRule
                .onAllNodesWithText("Submit Answer")
                .fetchSemanticsNodes().size == 1
        }

        // 3. Test Q&A buttons exist
        composeRule
            .onNodeWithTag("Question")
            .assertExists()
        composeRule
            .onAllNodesWithTag("Answer")
            .assertCountEquals(4)

        // 4. Submit answer and verify no answer has been selected
        composeRule
            .onNodeWithText("Submit Answer")
            .performClick()

        composeRule.onNode(hasText("No answer provided!"))
            .assertIsDisplayed()
    }

    @Test
    fun yesAnswerSubmitted() {
        // 1. Test "Submit Answer" button exists (requires API network request to retrieve question hence wait-until)
        composeRule.waitUntil(timeoutMillis = 10000) {
            composeRule
                .onAllNodesWithText("Submit Answer")
                .fetchSemanticsNodes().size == 1
        }

        // 2. Select any answer
        composeRule
            .onAllNodesWithTag("Answer")[0]
            .performClick()

        // 3. Submit the selected answer
        composeRule
            .onNodeWithText("Submit Answer")
            .performClick()

        // 4. Verify answer is one of 'Correct' or 'Incorrect'
        composeRule.onNodeWithText(text = "correct", substring = true, ignoreCase = true)
    }

    @Test
    fun skipQuestion() {
        // 1. Verify one question placeholder and four answer placeholders exist
        composeRule
            .onNodeWithTag("Question Placeholder")
            .assertExists()
        composeRule
            .onAllNodesWithTag("Answer Placeholder")
            .assertCountEquals(4)

        // 2. Test "Skip Question" button exists (requires API network request to retrieve question hence wait-until)
        composeRule.waitUntil(timeoutMillis = 10000) {
            composeRule
                .onAllNodesWithText("Skip Question")
                .fetchSemanticsNodes().size == 1
        }

        // 3. Press 'Skip Question' to trigger retrieval of new question
        composeRule
            .onNodeWithText("Skip Question")
            .performClick()
        composeRule
            .onNodeWithTag("Question Placeholder")
            .assertExists()
        composeRule
            .onAllNodesWithTag("Answer Placeholder")
            .assertCountEquals(4)

        // 4. Test "Question" exists (requires API network request to retrieve question hence wait-until)
        composeRule.waitUntil(timeoutMillis = 6000) {
            composeRule
                .onAllNodesWithTag("Question")
                .fetchSemanticsNodes().size == 1
        }
    }
}