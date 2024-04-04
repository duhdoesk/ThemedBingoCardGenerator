package com.duscaranari.themedbingocardsgenerator.data.local.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.duscaranari.themedbingocardsgenerator.domain.user.model.User

@Entity(tableName = "user_table")
class LocalUser(

    @PrimaryKey
    @ColumnInfo(name = "user_id")
    val userId: String,

    @ColumnInfo(name = "user_name")
    val userName: String?,
) {

    fun toObject() =
        this.run {
            User(
                id = userId,
                name = userName ?: "",
                picture = "",
                card = emptyList()
            )
        }
}
