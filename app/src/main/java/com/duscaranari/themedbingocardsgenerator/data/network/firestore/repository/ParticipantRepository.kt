package com.duscaranari.themedbingocardsgenerator.data.network.firestore.repository

import com.duscaranari.themedbingocardsgenerator.data.network.firestore.model.ParticipantDTO
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.snapshots
import com.google.firebase.firestore.toObject
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
            .map { it.toObjects<ParticipantDTO>() }

    fun getParticipantFromSessionId(
        sessionId: String,
        participantId: String
    ) = collection
        .document(sessionId)
        .collection("participants")
        .document(participantId)
        .snapshots()
        .map { it.toObject<ParticipantDTO>() }

    fun joinSession(
        sessionId: String,
        participantDTO: ParticipantDTO
    ): String {
        var result = ""

        val document = collection
            .document(sessionId)
            .collection("participants")
            .document(participantDTO.id)

        document
            .set(participantDTO)
            .addOnSuccessListener { result = document.id }
            .addOnFailureListener { e -> result = e.message.toString() }

        return result
    }
}