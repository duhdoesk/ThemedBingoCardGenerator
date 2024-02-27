package com.duscaranari.themedbingocardsgenerator.ui.presentation.sessions

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.duscaranari.themedbingocardsgenerator.domain.session.model.Session
import com.duscaranari.themedbingocardsgenerator.domain.session.use_case.CreateNewSessionUseCase
import com.duscaranari.themedbingocardsgenerator.domain.session.use_case.GetAllSessionsUseCase
import com.duscaranari.themedbingocardsgenerator.domain.user.model.Host
import com.duscaranari.themedbingocardsgenerator.domain.user.model.Participant
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.stateIn
import javax.inject.Inject
import kotlin.random.Random

@HiltViewModel
class SessionsViewModel @Inject constructor(
    getAllSessionsUseCase: GetAllSessionsUseCase,
    private val createNewSessionUseCase: CreateNewSessionUseCase
) : ViewModel() {

    private val _sessions = getAllSessionsUseCase.invoke()
    val sessions = _sessions.stateIn(
        viewModelScope,
        SharingStarted.WhileSubscribed(),
        emptyList()
    )

    fun onCreateNewSession() {

        val session = Session(
            state = "DRAWING",
            host = Host(
                name = "Joaquim"
            ),
            themeId = "1",
            name = generateRandomString(Random.nextInt(6, 20)),
            participants = generateRandomListOfParticipants()
        )

        val id = createNewSessionUseCase(session)
        Log.d("FIRESTORE", "SESSION ID: $id")
    }

    private fun generateRandomString(len: Int = 15): String{
        val alphanumerics = CharArray(26) { it -> (it + 97).toChar() }.toSet()
            .union(CharArray(9) { it -> (it + 48).toChar() }.toSet())
        return (0..<len).map {
            alphanumerics.toList().random()
        }.joinToString("")
    }

    private fun generateRandomListOfParticipants(): List<Participant> {
        val size = Random.nextInt(6, 20)
        val list = mutableListOf<Participant>()

        for (i in 0..size) {
            list.add(
                Participant(
                    name = generateRandomString(8),
                    card = (0..9).toList().map { it.toString() },
                    winner = false
                )
            )
        }

        return list
    }
}