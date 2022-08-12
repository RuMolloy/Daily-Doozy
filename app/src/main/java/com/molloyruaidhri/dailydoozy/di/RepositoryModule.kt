package com.molloyruaidhri.dailydoozy.di

import com.molloyruaidhri.dailydoozy.network.QuestionService
import com.molloyruaidhri.dailydoozy.network.model.QuestionDtoMapper
import com.molloyruaidhri.dailydoozy.repository.QuestionRepository
import com.molloyruaidhri.dailydoozy.repository.QuestionRepositoryImpl
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object RepositoryModule {

    @Provides
    @Singleton
    fun provideQuestionRepository(questionService: QuestionService, mapper: QuestionDtoMapper)
    : QuestionRepository {
        return QuestionRepositoryImpl(questionService, mapper)
    }
}