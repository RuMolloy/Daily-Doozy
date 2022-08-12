package com.molloyruaidhri.dailydoozy.network

import com.molloyruaidhri.dailydoozy.network.model.QuestionDto
import retrofit2.http.GET
import retrofit2.http.Query

interface QuestionService {

    @GET("api/questions")
    suspend fun get(
        @Query("limit") limit: Int = 1
    ) : List<QuestionDto>
}