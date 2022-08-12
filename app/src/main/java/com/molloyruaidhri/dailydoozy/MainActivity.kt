package com.molloyruaidhri.dailydoozy


import android.os.Bundle
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import com.molloyruaidhri.dailydoozy.ui.components.LoadingQuestion
import com.molloyruaidhri.dailydoozy.ui.components.ShowQuiz
import com.molloyruaidhri.dailydoozy.ui.question.QuestionViewModel
import com.molloyruaidhri.dailydoozy.ui.theme.DailyDoozyTheme
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collectLatest

@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    private val viewModel: QuestionViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            DailyDoozyTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colors.background
                ) {
                    val question = viewModel.question.value
                    val scaffoldState = rememberScaffoldState()

                    LaunchedEffect(key1 = true) {
                        viewModel.eventFlow.collectLatest { event ->
                            when(event) {
                                is QuestionViewModel.UiEvent.ShowSnackBarWithoutAction -> {
                                    scaffoldState.snackbarHostState.showSnackbar(
                                        message = event.message,
                                        duration = SnackbarDuration.Short
                                    )
                                }
                                is QuestionViewModel.UiEvent.ShowSnackBarWithAction -> {
                                    val result = scaffoldState.snackbarHostState.showSnackbar(
                                        message = event.message,
                                        actionLabel = "RETRY",
                                        duration = SnackbarDuration.Indefinite
                                    )
                                    if(result == SnackbarResult.ActionPerformed) {
                                        viewModel.getQuestion()
                                    }
                                }
                                is QuestionViewModel.UiEvent.IsAnswerCorrect -> {
                                    runOnUiThread {
                                        Toast.makeText(baseContext,
                                            if (event.isCorrect) "Correct" else "Incorrect",
                                            Toast.LENGTH_SHORT)
                                            .show()
                                    }
                                }
                            }
                        }
                    }
                    Scaffold(
                        scaffoldState = scaffoldState,
                        snackbarHost = {
                            // reuse default SnackbarHost to have default animation and timing handling
                            SnackbarHost(it) { data ->
                                // custom snackbar with the custom colors
                                Snackbar(
                                    actionColor = Color.Green,
                                    snackbarData = data,
                                )
                            }
                        }
                    ) {
                        if(question==null) {
                            LoadingQuestion()
                        }
                        else {
                            ShowQuiz(viewModel, question)
                        }

                    }
                }
            }
        }
    }

}

@Composable
fun Greeting(name: String) {
    Text(text = "Hello $name!")
}

@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    DailyDoozyTheme {
        Greeting("Android")
    }
}