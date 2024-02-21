package com.duscaranari.themedbingocardsgenerator.data.network.firestore.repository

import android.util.Log
import com.duscaranari.themedbingocardsgenerator.domain.character.model.BingoCharacter
import com.duscaranari.themedbingocardsgenerator.domain.theme.model.BingoTheme
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.snapshots
import com.google.firebase.firestore.toObjects
import kotlinx.coroutines.flow.flatMapLatest
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.onEach
import javax.inject.Inject

class ThemeRepository @Inject constructor(private val database: FirebaseFirestore) {

    private val collection = database.collection("themes")

    fun getAllThemes() =
        collection
            .snapshots()
            .onEach {
                val a = it.documents.first()
                val collection = a.reference.collection("characters")
                collection.addSnapshotListener { value, error ->
                    println(value?.toObjects<BingoCharacter>())
                }
            }
            .map {
                it.toObjects<BingoTheme>()
            }

    fun getThemeById(id: String) =
        collection
            .whereEqualTo("id", id)
            .snapshots()
            .map { it.toObjects<BingoTheme>() }

    fun getThemeByName(name: String) =
        collection
            .whereEqualTo("name", name)
            .snapshots()
            .map { it.toObjects<BingoTheme>() }
}