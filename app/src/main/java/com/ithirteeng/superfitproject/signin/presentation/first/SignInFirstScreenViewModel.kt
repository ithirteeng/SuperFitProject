package com.ithirteeng.superfitproject.signin.presentation.first

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.ithirteeng.superfitproject.R
import com.ithirteeng.superfitproject.common.entity.ErrorEntity
import com.ithirteeng.superfitproject.common.token.domain.usecase.SaveCurrentUserNameUseCase

class SignInFirstScreenViewModel(
    private val saveCurrentUserNameUseCase: SaveCurrentUserNameUseCase,
) : ViewModel() {

    fun accept(signInFirstEvent: SignInFirstEvent) {
        when (signInFirstEvent) {
            is SignInFirstEvent.ChangeTextField -> changeTextFieldValue(signInFirstEvent.value)
            is SignInFirstEvent.SignInFirstButtonCLick -> {
                handleSignInButton(signInFirstEvent.userName)
            }

            is SignInFirstEvent.SignUpButtonClickFirst -> {}
            SignInFirstEvent.DismissError -> {}
            SignInFirstEvent.Initial -> initState()
        }
    }

    private val _state = MutableLiveData(SignInFirstState())

    val state: LiveData<SignInFirstState> = _state

    private fun initState() {
        _state.value = _state.value?.copy(
            isCompleted = false
        )
    }

    private fun changeTextFieldValue(value: String) {
        _state.value = _state.value?.copy(
            userName = value
        )
    }

    private fun handleSignInButton(userName: String) {
        _state.value = _state.value?.copy(
            isLoading = true
        )
        if (validateUserName(userName = userName)) {
            saveCurrentUserNameUseCase.invoke(userName = userName)
            _state.value = _state.value?.copy(
                isLoading = false,
                isCompleted = true
            )
        } else {
            _state.value = _state.value?.copy(
                isLoading = false,
                error = ErrorEntity(
                    Exception(),
                    R.string.incorrect_email
                )
            )
        }
    }

    private fun onDismissError() {
        _state.value = _state.value?.copy(
            error = null
        )
    }

    private fun validateUserName(userName: String): Boolean {
        return userName.isNotEmpty() && userName.contains("@")
    }
}