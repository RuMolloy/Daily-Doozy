package com.molloyruaidhri.dailydoozy.domain.use_cases

data class QuestionUseCases(
    val getQuestion: GetQuestionUseCase,
    val validateAnswer: ValidateAnswerUseCase,
    val getAnswerColorUseCase: GetAnswerColorUseCase
)
