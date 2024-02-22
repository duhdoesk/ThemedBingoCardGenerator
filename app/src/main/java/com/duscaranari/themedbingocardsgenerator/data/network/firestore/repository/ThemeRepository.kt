package com.duscaranari.themedbingocardsgenerator.data.network.firestore.repository

import com.duscaranari.themedbingocardsgenerator.domain.theme.model.BingoTheme
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.snapshots
import com.google.firebase.firestore.toObject
import com.google.firebase.firestore.toObjects
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.mapLatest
import javax.inject.Inject

class ThemeRepository @Inject constructor(database: FirebaseFirestore) {

    private val collection = database.collection("themes")

    fun getAllThemes() =
        collection
            .snapshots()
            .map { it.toObjects<BingoTheme>() }

    fun getThemeById(id: String) =
        collection
            .document(id)
            .snapshots()
            .map { it.toObject<BingoTheme>() }
}