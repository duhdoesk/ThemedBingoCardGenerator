package com.duscaranari.themedbingocardsgenerator.data

import androidx.room.Dao
import androidx.room.Query
import com.duscaranari.themedbingocardsgenerator.model.Character

@Dao
interface CharacterDao {

    @Query("SELECT * from character_table")
    suspend fun getAllCharacters() : List<Character>

    @Query("SELECT * from character_table where character_theme_id =:themeId")
    suspend fun getThemeCharacters(themeId: String) : List<Character>

    @Query("SELECT * from character_table where character_id =:characterId")
    suspend fun getCharacterById(characterId: String) : Character
}