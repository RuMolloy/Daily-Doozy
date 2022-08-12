package com.molloyruaidhri.dailydoozy.repository

import com.molloyruaidhri.dailydoozy.domain.model.Question
import com.molloyruaidhri.dailydoozy.network.QuestionService
import com.molloyruaidhri.dailydoozy.network.model.QuestionDto
import com.molloyruaidhri.dailydoozy.network.model.QuestionDtoMapper
import com.molloyruaidhri.dailydoozy.util.Resource

class QuestionRepositoryImpl(
    private val questionService: QuestionService,
    private val mapper: QuestionDtoMapper
) : QuestionRepository {

    override suspend fun get(): Resource<Question> {
        val listOfQuestionDto: List<QuestionDto>
        try{
            listOfQuestionDto = questionService.get()
        }
        catch(e: Exception) {
            return Resource.Error(e.message)
        }
        return Resource.Success(mapper.mapToDomainModel(listOfQuestionDto[0]))
    }

}