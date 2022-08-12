package com.molloyruaidhri.dailydoozy.domain.use_cases

import com.google.common.truth.Truth.assertThat
import com.molloyruaidhri.dailydoozy.data.repository.FakeRepositoryImpl
import com.molloyruaidhri.dailydoozy.domain.model.Question
import com.molloyruaidhri.dailydoozy.repository.QuestionRepository
import com.molloyruaidhri.dailydoozy.util.Resource
import kotlinx.coroutines.runBlocking
import org.junit.Before
import org.junit.Test

class GetQuestionUseTest{

    private lateinit var getQuestion: GetQuestionUseCase
    private lateinit var fakeRepository: QuestionRepository

    @Before
    fun setup() {
        fakeRepository = FakeRepositoryImpl()
        getQuestion = GetQuestionUseCase(fakeRepository)
    }

    @Test
    fun `test get question`() = runBlocking {
        val result = getQuestion.invoke()
        assertThat(result is Resource.Success).isTrue()
        assertThat(result.data is Question).isTrue()
    }

}