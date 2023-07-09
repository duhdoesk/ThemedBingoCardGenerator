package com.duscaranari.themedbingocardsgenerator.repository

import com.duscaranari.themedbingocardsgenerator.data.CharacterDao
import javax.inject.Inject

class CharacterRepository @Inject constructor(
    private val characterDao: CharacterDao
) {

    suspend fun getAllCharacters() =
        characterDao.getAllCharacters()

    suspend fun getThemeCharacters(themeId: String) =
        characterDao.getThemeCharacters(themeId)

    suspend fun getCharacterById(characterId: String) =
        characterDao.getCharacterById(characterId)
}