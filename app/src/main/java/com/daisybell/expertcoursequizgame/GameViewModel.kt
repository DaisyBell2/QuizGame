package com.daisybell.expertcoursequizgame

class GameViewModel(private val repository: GameRepository) {

    fun init(): GameUiState {
        val data = repository.questionAndChoices()
        return GameUiState.AnswerQuestion(
            question = data.question,
            choices = data.choices
        )
    }

    fun chooseFirst(): GameUiState {
        repository.saveUserChoice(0)
        val data = repository.questionAndChoices()
        return GameUiState.ChoiceMade(
            question = data.question,
            choices = listOf(
                ChoiceUiState.NotAvailableToChoose(data.choices[0]),
                ChoiceUiState.AvailableToChoose(data.choices[1]),
                ChoiceUiState.AvailableToChoose(data.choices[2]),
                ChoiceUiState.AvailableToChoose(data.choices[3]),
            )
        )
    }

    fun chooseSecond(): GameUiState {
        repository.saveUserChoice(1)
        val data = repository.questionAndChoices()
        return GameUiState.ChoiceMade(
            question = data.question,
            choices = listOf(
                ChoiceUiState.AvailableToChoose(data.choices[0]),
                ChoiceUiState.NotAvailableToChoose(data.choices[1]),
                ChoiceUiState.AvailableToChoose(data.choices[2]),
                ChoiceUiState.AvailableToChoose(data.choices[3]),
            )
        )
    }

    fun chooseThird(): GameUiState {
        repository.saveUserChoice(2)
        val data = repository.questionAndChoices()
        return GameUiState.ChoiceMade(
            question = data.question,
            choices = listOf(
                ChoiceUiState.AvailableToChoose(data.choices[0]),
                ChoiceUiState.AvailableToChoose(data.choices[1]),
                ChoiceUiState.NotAvailableToChoose(data.choices[2]),
                ChoiceUiState.AvailableToChoose(data.choices[3]),
            )
        )
    }

    fun chooseFourth(): GameUiState {
        repository.saveUserChoice(3)
        val data = repository.questionAndChoices()
        return GameUiState.ChoiceMade(
            question = data.question,
            choices = listOf(
                ChoiceUiState.AvailableToChoose(data.choices[0]),
                ChoiceUiState.AvailableToChoose(data.choices[1]),
                ChoiceUiState.AvailableToChoose(data.choices[2]),
                ChoiceUiState.NotAvailableToChoose(data.choices[3]),
            )
        )
    }

    fun check(): GameUiState {
        val indexes = repository.check()
        val data = repository.questionAndChoices()

        return GameUiState.AnswerChecked(
            question = data.question,
            choices = data.choices.mapIndexed { index, choice ->
                if (indexes.correctIndex == index) {
                    ChoiceUiState.Correct(choice)
                } else if (indexes.userChoiceIndex == index) {
                    ChoiceUiState.Incorrect(choice)
                } else {
                    ChoiceUiState.NotAvailableToChoose(choice)
                }
            }
        )
    }

    fun next(): GameUiState {
        repository.next()
        return init()
    }

}
