package com.molloyruaidhri.dailydoozy.domain.use_cases

import org.junit.runner.RunWith
import org.junit.runners.Suite

@RunWith(Suite::class)
@Suite.SuiteClasses(
    GetQuestionUseTest::class,
    ValidateAnswerUseCaseTest::class,
    GetAnswerColorUseCaseTest::class
)
class TestSuite