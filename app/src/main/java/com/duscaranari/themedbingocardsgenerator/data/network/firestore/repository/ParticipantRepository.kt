package com.duscaranari.themedbingocardsgenerator.data.network.firestore.repository

import com.duscaranari.themedbingocardsgenerator.domain.user.model.Participant
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.snapshots
import com.google.firebase.firestore.toObjects
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class ParticipantRepository @Inject constructor(database: FirebaseFirestore) {

    private val collection = database.collection("sessions")

    fun getParticipantsFromSessionId(sessionId: String) =
        collection
            .document(sessionId)
            .collection("participants")
            .snapshots()
            .map { it.toObjects<Participant>() }
}