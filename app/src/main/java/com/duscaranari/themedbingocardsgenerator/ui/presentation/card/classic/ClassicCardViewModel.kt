package com.duscaranari.themedbingocardsgenerator.ui.presentation.card.classic

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.duscaranari.themedbingocardsgenerator.data.local.model.LocalUser
import com.duscaranari.themedbingocardsgenerator.data.local.repository.LocalUserRepository
import com.duscaranari.themedbingocardsgenerator.ui.presentation.card.classic.state.ClassicCardUiState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ClassicCardViewModel @Inject constructor(
    private val localUserRepository: LocalUserRepository
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

        val numbers= mutableListOf<Int>()
        numbers.addAll(0, (61..75).toList().shuffled().subList(0, 5))
        numbers.addAll(0, (46..60).toList().shuffled().subList(0, 5))
        numbers.addAll(0, (31..45).toList().shuffled().subList(0, 4))
        numbers.addAll(0, (16..30).toList().shuffled().subList(0, 5))
        numbers.addAll(0, (1..15).toList().shuffled().subList(0, 5))

        return numbers
    }


    /**
     * CRUD functions for current user
     */

    fun updateCurrentUser(userName: String) {
        viewModelScope.launch(Dispatchers.IO) {
            setCurrentUser(
                LocalUser(
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

    private suspend fun getCurrentUser(userId: String = "1"): LocalUser? {
        return localUserRepository.getUser(userId)
    }

    private suspend fun setCurrentUser(localUser: LocalUser) {
        localUserRepository.insertUser(localUser)
    }
}