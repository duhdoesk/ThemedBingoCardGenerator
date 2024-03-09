package com.duscaranari.themedbingocardsgenerator.data.network.firestore.repository

import android.util.Log
import com.duscaranari.themedbingocardsgenerator.domain.session.model.Session
import com.duscaranari.themedbingocardsgenerator.domain.user.model.Participant
import com.google.firebase.firestore.FieldValue
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.Query
import com.google.firebase.firestore.snapshots
import com.google.firebase.firestore.toObject
import com.google.firebase.firestore.toObjects
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class SessionRepository @Inject constructor(database: FirebaseFirestore) {

    private val collection = database.collection("sessions")

    fun getAllSessions() =
        collection
            .snapshots()
            .map { it.toObjects<Session>() }

    fun getNotStartedSessions() =
        collection
            .orderBy("name")
            .whereEqualTo("state", "NOT_STARTED")
            .snapshots()
            .map { it.toObjects<Session>() }

    fun getSessionById(id: String) =
        collection
            .document(id)
            .snapshots()
            .map { it.toObject<Session>() }

    fun createNewSession(session: Session): String {
        val reference = collection.document()

        reference
            .set(session)
            .addOnSuccessListener { Log.d("FIRESTORE", "SUCCESS") }
            .addOnFailureListener { e -> Log.d("FIRESTORE", e.message.toString()) }

        return reference.id
    }

    fun joinSession(
        sessionId: String,
        participant: Participant
    ) {
        val reference = collection.document(sessionId)

        reference
            .update("participants", FieldValue.arrayUnion(participant))
    }
}