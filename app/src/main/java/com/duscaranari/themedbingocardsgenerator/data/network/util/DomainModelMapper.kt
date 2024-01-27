package com.duscaranari.themedbingocardsgenerator.data.network.util

import com.duscaranari.themedbingocardsgenerator.domain.app_data.AppData
import com.duscaranari.themedbingocardsgenerator.domain.character.Character
import com.duscaranari.themedbingocardsgenerator.domain.theme.model.Theme
import com.duscaranari.themedbingocardsgenerator.data.network.model.CharacterNetworkEntity
import com.duscaranari.themedbingocardsgenerator.data.network.model.DataNetworkEntity
import com.duscaranari.themedbingocardsgenerator.data.network.model.ThemeNetworkEntity

class DomainModelMapper {

    fun mapAppDataFromNetworkEntity(dataNetworkEntity: DataNetworkEntity): AppData {

        return AppData(
            appName = dataNetworkEntity.appName,
            dataVersion = dataNetworkEntity.dataVersion
        )
    }

    fun mapThemeFromNetworkEntity(themeNetworkEntity: ThemeNetworkEntity): Theme {

        return Theme(
            themeId = themeNetworkEntity.themeId,
            themeName = themeNetworkEntity.themeName,
            themePicture = themeNetworkEntity.themePicture
        )
    }

    fun mapCharacterFromNetworkEntity(
        characterNetworkEntity: CharacterNetworkEntity,
        themeNetworkEntity: ThemeNetworkEntity
    ): Character {

        return Character(
            characterId = "t${themeNetworkEntity.themeId}e${characterNetworkEntity.characterId}",
            characterName = characterNetworkEntity.characterName,
            characterPicture = characterNetworkEntity.characterPicture,
            characterThemeId = themeNetworkEntity.themeId,
            characterCardId = characterNetworkEntity.characterId
        )
    }
}