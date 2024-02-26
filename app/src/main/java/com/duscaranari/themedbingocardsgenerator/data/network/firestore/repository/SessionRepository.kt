package com.duscaranari.themedbingocardsgenerator.data.network.firestore.repository

import android.util.Log
import com.duscaranari.themedbingocardsgenerator.domain.session.model.Session
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
}