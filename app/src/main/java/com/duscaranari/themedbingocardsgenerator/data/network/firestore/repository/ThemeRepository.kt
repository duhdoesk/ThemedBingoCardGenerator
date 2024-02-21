package com.duscaranari.themedbingocardsgenerator.data.network.firestore.repository

import com.duscaranari.themedbingocardsgenerator.domain.theme.model.BingoTheme
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.snapshots
import com.google.firebase.firestore.toObjects
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class ThemeRepository @Inject constructor(private val database: FirebaseFirestore) {

    private val collection = database.collection("themes")

    fun getAllThemes() =
        collection
            .snapshots()
            .map { it.toObjects<BingoTheme>() }

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