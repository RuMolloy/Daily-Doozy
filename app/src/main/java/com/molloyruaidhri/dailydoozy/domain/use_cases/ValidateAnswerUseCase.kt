package com.molloyruaidhri.dailydoozy.domain.use_cases


import com.molloyruaidhri.dailydoozy.util.Resource

class ValidateAnswerUseCase {

    operator fun invoke(userAnswer: String?, actualAnswer: String): Resource<Boolean> {
        return if(userAnswer.isNullOrEmpty()) {
            Resource.Error("No answer provided!")
        } else {
            Resource.Success(userAnswer == actualAnswer)
        }
    }

}