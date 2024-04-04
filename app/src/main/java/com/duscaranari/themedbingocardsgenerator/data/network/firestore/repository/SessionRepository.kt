package com.duscaranari.themedbingocardsgenerator.data.network.firestore.repository

import android.util.Log
import com.duscaranari.themedbingocardsgenerator.data.network.firestore.model.SessionDTO
import com.google.firebase.firestore.FieldValue
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.snapshots
import com.google.firebase.firestore.toObject
import com.google.firebase.firestore.toObjects
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class SessionRepository @Inject constructor(database: FirebaseFirestore) {

    private val collection = database.collection("sessions")

    fun getAllSessions() =
        collection
            .orderBy("name")
            .snapshots()
            .map { it.toObjects<SessionDTO>() }

    fun getSessionById(id: String) =
        collection
            .document(id)
            .snapshots()
            .map { it.toObject<SessionDTO>() }

    fun getNotStartedSessions() =
        collection
            .orderBy("name")
            .whereEqualTo("state", "NOT_STARTED")
            .snapshots()
            .map { it.toObjects<SessionDTO>() }

    fun createNewSession(session: SessionDTO): String {
        val document = collection.document()

        document
            .set(session)
            .addOnSuccessListener { Log.d("FIRESTORE", "SUCCESS") }
            .addOnFailureListener { e -> Log.d("FIRESTORE", e.message.toString()) }

        return document.id
    }

    fun startDrawing(sessionId: String) =
        collection
            .document(sessionId)
            .update("state", "DRAWING")

    fun finishSession(sessionId: String) =
        collection
            .document(sessionId)
            .update("state", "FINISHED")

    fun drawNextCharacter(sessionId: String, characterId: String) =
        collection
            .document(sessionId)
            .update("listOfDrawnCharactersIds", FieldValue.arrayUnion(characterId))

    fun addWinner(sessionId: String, winnerId: String) =
        collection
            .document(sessionId)
            .update("listOfWinnersIds", FieldValue.arrayUnion(winnerId))
}