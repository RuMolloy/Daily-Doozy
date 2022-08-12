package com.molloyruaidhri.dailydoozy.data.repository

import com.molloyruaidhri.dailydoozy.domain.model.Question
import com.molloyruaidhri.dailydoozy.repository.QuestionRepository
import com.molloyruaidhri.dailydoozy.util.Resource

class FakeRepositoryImpl : QuestionRepository {

    private val questions = mutableListOf<Question>()

    init{
        val q1 = Question(id = "1",
                question = "Q1",
                correctAnswer = "A",
                allAnswers = arrayListOf("A", "B", "C", "D")
        )
        val q2 = Question(id = "2",
            question = "Q2",
            correctAnswer = "W",
            allAnswers = arrayListOf("W", "X", "Y", "Z")
        )

        questions.add(q1)
        questions.add(q2)
    }

    override suspend fun get(): Resource<Question> {
        return Resource.Success(questions[0])
    }

}