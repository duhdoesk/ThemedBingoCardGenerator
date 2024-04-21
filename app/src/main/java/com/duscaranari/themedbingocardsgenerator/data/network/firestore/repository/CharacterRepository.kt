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
        characters.forEachIndexed { index, character ->
            collection
                .document(themeId)
                .collection("characters")
                .document((index + 1).toString())
                .set(character)
        }
    }
}