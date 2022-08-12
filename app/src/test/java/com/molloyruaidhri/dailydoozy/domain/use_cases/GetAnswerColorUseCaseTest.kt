package com.molloyruaidhri.dailydoozy.domain.use_cases

import androidx.compose.ui.graphics.Color
import com.google.common.truth.Truth.assertThat
import com.molloyruaidhri.dailydoozy.data.repository.FakeRepositoryImpl
import com.molloyruaidhri.dailydoozy.domain.model.Question
import kotlinx.coroutines.runBlocking
import org.junit.Before
import org.junit.FixMethodOrder
import org.junit.Test
import org.junit.runners.MethodSorters

@FixMethodOrder(MethodSorters.NAME_ASCENDING)
class GetAnswerColorUseCaseTest{

    private lateinit var fakeRepositoryImpl: FakeRepositoryImpl
    private lateinit var question: Question
    private lateinit var getAnswerColorUseCase: GetAnswerColorUseCase
    
    @Before
    fun setup() {
        fakeRepositoryImpl = FakeRepositoryImpl()
        getAnswerColorUseCase = GetAnswerColorUseCase()
    }

    @Test
    fun `01 test correct answer submitted`() = runBlocking {
        question = fakeRepositoryImpl.get().data!!

        val color = getAnswerColorUseCase.invoke(
            possibleAnswer = question.correctAnswer,
            userSelectedAnswer = question.correctAnswer,
            correctAnswer = question.correctAnswer,
            isAnswerCorrect = true
        )
        assertThat(color).isEqualTo(Color.Green)
    }

    @Test
    fun `02 test correct answer but not submitted`() = runBlocking {
        question = fakeRepositoryImpl.get().data!!

        val color = getAnswerColorUseCase.invoke(
            possibleAnswer = question.correctAnswer,
            userSelectedAnswer = question.correctAnswer,
            correctAnswer = question.correctAnswer,
            isAnswerCorrect = null
        )
        assertThat(color).isEqualTo(Color.Gray)
    }

    @Test
    fun `03 test no answer submitted`() = runBlocking {
        question = fakeRepositoryImpl.get().data!!

        val color = getAnswerColorUseCase.invoke(
            possibleAnswer = question.correctAnswer,
            userSelectedAnswer = null,
            correctAnswer = question.correctAnswer,
            isAnswerCorrect = null
        )
        assertThat(color).isEqualTo(Color.Gray)
    }

    @Test
    fun `04 test incorrect answer submitted`() = runBlocking {
        question = fakeRepositoryImpl.get().data!!

        val color = getAnswerColorUseCase.invoke(
            possibleAnswer = question.allAnswers.first { it != question.correctAnswer },
            userSelectedAnswer = question.allAnswers.first { it != question.correctAnswer },
            correctAnswer = question.correctAnswer,
            isAnswerCorrect = false
        )
        assertThat(color).isEqualTo(Color.Red)
    }

}