package com.duscaranari.themedbingocardsgenerator.data.network.firestore.repository

import android.content.res.Resources.Theme
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.snapshots
import com.google.firebase.firestore.toObjects
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class ThemeRepository @Inject constructor(private val database: FirebaseFirestore) {

    fun getAllThemes() =
        database
            .collection("themes")
            .snapshots()
            .map { it.toObjects<Theme>() }

    fun getThemeByName(name: String) =
        database
            .collection("themes")
            .whereEqualTo("name", name)
            .snapshots()
            .map { it.toObjects<Theme>() }
}