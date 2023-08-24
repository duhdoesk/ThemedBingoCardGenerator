package com.duscaranari.themedbingocardsgenerator.domain.repository

import com.duscaranari.themedbingocardsgenerator.data.CharacterDao
import com.duscaranari.themedbingocardsgenerator.domain.model.Character
import javax.inject.Inject

class CharacterRepository @Inject constructor(
    private val characterDao: CharacterDao
) {

    suspend fun getAllCharacters() =
        characterDao.getAllCharacters()

    suspend fun getThemeCharacters(themeId: String): List<Character>? =
        characterDao.getThemeCharacters(themeId)

    suspend fun getCharacterById(characterId: String) =
        characterDao.getCharacterById(characterId)

    suspend fun clearCharactersTable() =
        characterDao.clearCharacterTable()

    suspend fun insertCharacters(characters: List<Character>) =
        characterDao.insertCharacters(characters)
}