package com.molloyruaidhri.dailydoozy.repository

import com.molloyruaidhri.dailydoozy.domain.model.Question
import com.molloyruaidhri.dailydoozy.util.Resource

interface QuestionRepository {

    suspend fun get(): Resource<Question>

}