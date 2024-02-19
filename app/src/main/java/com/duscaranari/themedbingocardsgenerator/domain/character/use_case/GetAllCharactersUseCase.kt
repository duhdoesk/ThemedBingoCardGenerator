package com.duscaranari.themedbingocardsgenerator.domain.character.use_case

import com.duscaranari.themedbingocardsgenerator.domain.character.model.Character
import com.duscaranari.themedbingocardsgenerator.domain.character.repository.CharacterRepository
import javax.inject.Inject

class GetAllCharactersUseCase @Inject constructor(private val characterRepository: CharacterRepository) {

    suspend operator fun invoke(): List<Character> {
        return characterRepository.getAllCharacters()
    }
}