package com.molloyruaidhri.dailydoozy.domain.use_cases

import com.molloyruaidhri.dailydoozy.repository.QuestionRepository

class GetQuestionUseCase(
    private val questionRepository: QuestionRepository
) {

    suspend fun invoke() = questionRepository.get()

}