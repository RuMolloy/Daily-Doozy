package com.molloyruaidhri.dailydoozy.network.model

import com.molloyruaidhri.dailydoozy.domain.model.Question
import com.molloyruaidhri.dailydoozy.domain.util.DomainMapper

class QuestionDtoMapper : DomainMapper<QuestionDto, Question> {

    override fun mapToDomainModel(model: QuestionDto): Question {
        //TODO could this be done somewhere else? maybe in the business logic?
        val answersAll = model.answersIncorrect
        answersAll.add(model.answerCorrect)
        answersAll.shuffle()

        return Question(
            id = model.id,
            question = model.question,
            correctAnswer = model.answerCorrect,
            allAnswers = answersAll
        )
    }

}