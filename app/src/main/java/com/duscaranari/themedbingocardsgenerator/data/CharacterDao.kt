package com.duscaranari.themedbingocardsgenerator.data

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.duscaranari.themedbingocardsgenerator.domain.model.Character

@Dao
interface CharacterDao {

//    QUERY

    @Query("SELECT * from character_table")
    suspend fun getAllCharacters() : List<Character>

    @Query("SELECT * from character_table where character_theme_id =:themeId")
    suspend fun getThemeCharacters(themeId: String) : List<Character>

    @Query("SELECT * from character_table where character_id =:characterId")
    suspend fun getCharacterById(characterId: String) : Character


//    DELETE

    @Query("DELETE from character_table")
    fun clearCharacterTable()


//    INSERT

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertCharacters(characters: List<Character>)
}