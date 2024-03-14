package com.duscaranari.themedbingocardsgenerator.data.network.firestore.repository

import com.duscaranari.themedbingocardsgenerator.domain.character.model.BingoCharacter
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.snapshots
import com.google.firebase.firestore.toObjects
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class CharacterRepository @Inject constructor(database: FirebaseFirestore) {

    private val collection = database.collection("themes")

    fun getCharactersFromThemeId(themeId: String) =
        collection
            .document(themeId)
            .collection("characters")
            .snapshots()
            .map { it.toObjects<BingoCharacter>() }

    fun uploadThemeCharacters(
        themeId: String,
        characters: List<BingoCharacter>
    ) {
        characters.forEach() {
            collection
                .document(themeId)
                .collection("characters")
                .add(it)
        }
    }
}