package com.duscaranari.themedbingocardsgenerator.domain.theme.use_case

import com.duscaranari.themedbingocardsgenerator.data.network.firestore.repository.ThemeRepository
import com.google.firebase.firestore.FirebaseFirestore
import javax.inject.Inject

class GetBingoThemeByIdUseCase @Inject constructor(private val repository: ThemeRepository) {

    operator fun invoke(id: String) =
        repository.getThemeById(id)
}