package com.duscaranari.themedbingocardsgenerator.domain.character.use_case

import com.duscaranari.themedbingocardsgenerator.domain.character.repository.CharacterRepository
import javax.inject.Inject

class ClearCharacterTableUseCase @Inject constructor(private val characterRepository: CharacterRepository) {

    suspend operator fun invoke() =
        characterRepository.clearCharactersTable()
}