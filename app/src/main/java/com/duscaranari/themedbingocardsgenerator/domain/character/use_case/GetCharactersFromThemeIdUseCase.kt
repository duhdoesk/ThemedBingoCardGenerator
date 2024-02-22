package com.duscaranari.themedbingocardsgenerator.domain.character.use_case

import com.duscaranari.themedbingocardsgenerator.data.network.firestore.repository.CharacterRepository
import javax.inject.Inject

class GetCharactersFromThemeIdUseCase @Inject constructor(private val repository: CharacterRepository) {

    operator fun invoke(themeId: String) =
        repository.getCharactersFromThemeId(themeId)
}