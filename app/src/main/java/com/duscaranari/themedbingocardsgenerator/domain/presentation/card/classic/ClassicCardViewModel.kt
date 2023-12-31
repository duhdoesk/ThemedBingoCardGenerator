package com.duscaranari.themedbingocardsgenerator.domain.presentation.card.classic

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.duscaranari.themedbingocardsgenerator.domain.model.User
import com.duscaranari.themedbingocardsgenerator.domain.repository.UserRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ClassicCardViewModel @Inject constructor(
    private val userRepository: UserRepository
) : ViewModel() {

    private val _uiState = MutableStateFlow<ClassicCardUiState>(ClassicCardUiState.Loading)
    val uiState = _uiState.asStateFlow()

    init { setup() }

    private fun setup() {
        viewModelScope.launch(Dispatchers.IO) {
            _uiState.value = ClassicCardUiState.Ready(
                numbers = getRandomCard(),
                currentUser = getCurrentUser()?.userName.orEmpty()
            )
        }
    }

    fun drawNewCard() {
        when (val state = uiState.value) {
            is ClassicCardUiState.Ready -> {
                _uiState.value = state.copy(numbers = getRandomCard())
            }

            else -> {
                setup()
            }
        }
    }

    private fun getRandomCard(): List<Int> {
        return (0..75).toList().shuffled().subList(0, 24)
    }


    /**
     * CRUD functions for current user
     */

    fun updateCurrentUser(userName: String) {
        viewModelScope.launch(Dispatchers.IO) {
            setCurrentUser(
                User(
                    userId = "1",
                    userName = userName
                )
            )

            when (val state = uiState.value) {
                is ClassicCardUiState.Ready -> {
                    _uiState.value = state.copy(
                        currentUser = getCurrentUser()?.userName.orEmpty()
                    )
                }

                else -> {
                    _uiState.value = ClassicCardUiState.Ready(
                        numbers = getRandomCard(),
                        currentUser = getCurrentUser()?.userName.orEmpty()
                    )
                }
            }
        }
    }

    private suspend fun getCurrentUser(userId: String = "1"): User? {
        return userRepository.getUser(userId)
    }

    private suspend fun setCurrentUser(user: User) {
        userRepository.insertUser(user)
    }
}