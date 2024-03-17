package com.duscaranari.themedbingocardsgenerator.data.network.firestore.repository

import android.util.Log
import com.duscaranari.themedbingocardsgenerator.data.network.firestore.model.SessionDTO
import com.duscaranari.themedbingocardsgenerator.data.network.firestore.model.NetworkUser
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
            .snapshots()
            .map { it.toObjects<SessionDTO>() }

    fun getNotStartedSessions() =
        collection
            .orderBy("name")
            .whereEqualTo("state", "NOT_STARTED")
            .snapshots()
            .map { it.toObjects<SessionDTO>() }

    fun getSessionById(id: String) =
        collection
            .document(id)
            .snapshots()
            .map { it.toObject<SessionDTO>() }

    fun createNewSession(session: SessionDTO): String {
        val document = collection.document()

        document
            .set(session)
            .addOnSuccessListener { Log.d("FIRESTORE", "SUCCESS") }
            .addOnFailureListener { e -> Log.d("FIRESTORE", e.message.toString()) }

        return document.id
    }

    fun joinSession(
        sessionId: String,
        networkUser: NetworkUser
    ): String {
        var result = ""

        val document = collection
            .document(sessionId)
            .collection("participants")
            .document(networkUser.id)

        document
            .set(networkUser)
            .addOnSuccessListener { result = document.id }
            .addOnFailureListener { e -> result = e.message.toString() }

        return result
    }

    fun getParticipantsFromSessionId(sessionId: String) =
        collection
            .document(sessionId)
            .collection("participants")
            .snapshots()
            .map { it.toObjects<NetworkUser>() }
}