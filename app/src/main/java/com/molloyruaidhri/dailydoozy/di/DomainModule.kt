package com.molloyruaidhri.dailydoozy.di

import com.molloyruaidhri.dailydoozy.domain.use_cases.GetAnswerColorUseCase
import com.molloyruaidhri.dailydoozy.domain.use_cases.GetQuestionUseCase
import com.molloyruaidhri.dailydoozy.domain.use_cases.QuestionUseCases
import com.molloyruaidhri.dailydoozy.domain.use_cases.ValidateAnswerUseCase
import com.molloyruaidhri.dailydoozy.repository.QuestionRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object DomainModule {

    @Provides
    @Singleton
    fun provideUseCases(questionRepository: QuestionRepository): QuestionUseCases {
        return QuestionUseCases(
            getQuestion = GetQuestionUseCase(questionRepository),
            validateAnswer = ValidateAnswerUseCase(),
            getAnswerColorUseCase = GetAnswerColorUseCase()
        )
    }

}