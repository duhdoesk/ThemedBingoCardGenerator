package com.duscaranari.themedbingocardsgenerator.di

import androidx.room.migration.Migration
import androidx.sqlite.db.SupportSQLiteDatabase

val MIGRATION_1_2 = object : Migration(1, 2) {
    override fun migrate(database: SupportSQLiteDatabase) {
        database.execSQL(
            "CREATE TABLE `classic_draw_table` (" +
                    "`draw_id` INTEGER NOT NULL, " +
                    "`numbers` INTEGER NOT NULL, " +
                    "`is_completed` INTEGER NOT NULL, " +
                    "`drawn_numbers` TEXT NOT NULL, " +
                    "PRIMARY KEY(`draw_id`))")
    }
}