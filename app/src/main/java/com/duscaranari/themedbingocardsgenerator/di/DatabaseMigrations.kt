package com.duscaranari.themedbingocardsgenerator.di

import androidx.room.migration.Migration
import androidx.sqlite.db.SupportSQLiteDatabase

val MIGRATION_2_1 = object : Migration(2, 1) {
    override fun migrate(database: SupportSQLiteDatabase) {
        database.execSQL("DROP TABLE `classic_draw_table`")
    }
}