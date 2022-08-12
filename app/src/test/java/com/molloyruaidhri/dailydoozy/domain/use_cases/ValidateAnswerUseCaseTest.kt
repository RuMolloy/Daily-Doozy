package com.molloyruaidhri.dailydoozy.domain.use_cases

import com.google.common.truth.Truth.assertThat
import com.molloyruaidhri.dailydoozy.data.repository.FakeRepositoryImpl
import com.molloyruaidhri.dailydoozy.domain.model.Question
import com.molloyruaidhri.dailydoozy.repository.QuestionRepository
import com.molloyruaidhri.dailydoozy.util.Resource
import kotlinx.coroutines.runBlocking
import org.junit.Before
import org.junit.FixMethodOrder
import org.junit.Test
import org.junit.runners.MethodSorters

@FixMethodOrder(MethodSorters.NAME_ASCENDING)
class ValidateAnswerUseCaseTest{

    private lateinit var validateAnswer: ValidateAnswerUseCase
    private lateinit var fakeRepository: QuestionRepository
    private lateinit var question: Question

    @Before
    fun setup() {
        fakeRepository = FakeRepositoryImpl()
        validateAnswer = ValidateAnswerUseCase()
    }

    @Test()
    fun `01 test correct answer`() = runBlocking {
        val question = fakeRepository.get()
        val result = validateAnswer.invoke(
            userAnswer = question.data!!.correctAnswer,
            actualAnswer = question.data!!.correctAnswer
        )
        assertThat(result is Resource.Success).isTrue()
        assertThat(result.data).isTrue()
    }

    @Test
    fun `02 test incorrect answer`() = runBlocking {
        val question = fakeRepository.get()

        val result = validateAnswer.invoke(
            userAnswer = "asnsd 911 asd 1 qw",
            actualAnswer = question.data!!.correctAnswer
        )
        assertThat(result is Resource.Success).isTrue()
        assertThat(result.data).isFalse()
    }

    @Test
    fun `03 test invalid answer`() = runBlocking {
        val question = fakeRepository.get()

        val result = validateAnswer.invoke(
            userAnswer = null,
            actualAnswer = question.data!!.correctAnswer
        )
        assertThat(result is Resource.Error).isTrue()
        assertThat(result.msg).isEqualTo("No answer provided!")
    }

}