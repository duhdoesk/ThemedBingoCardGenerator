package com.duscaranari.themedbingocardsgenerator.domain.character.use_case

import com.duscaranari.themedbingocardsgenerator.domain.character.model.Character
import com.duscaranari.themedbingocardsgenerator.domain.character.repository.CharacterRepository
import javax.inject.Inject

class InsertCharactersUseCase @Inject constructor(private val characterRepository: CharacterRepository) {

    suspend operator fun invoke(characters: List<Character>) =
        characterRepository.insertCharacters(characters)
}