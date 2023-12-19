package com.cookandroid.block7

import android.content.ContentValues
import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper

class SoundSettingsDbHelper(context: Context) : SQLiteOpenHelper(context, DATABASE_NAME, null, DATABASE_VERSION) {
    override fun onCreate(db: SQLiteDatabase) {
        val CREATE_TABLE = "CREATE TABLE $TABLE_NAME (" +
                "$COLUMN_ID INTEGER PRIMARY KEY AUTOINCREMENT," +
                "$COLUMN_MUSIC_VOLUME INTEGER," +
                "$COLUMN_MUSIC_VOLUME_ON_OFF INTEGER," +
                "$COLUMN_EFFECT_VOLUME INTEGER," +
                "$COLUMN_EFFECT_VOLUME_ON_OFF INTEGER)"
        db.execSQL(CREATE_TABLE)
    }

    override fun onUpgrade(db: SQLiteDatabase, oldVersion: Int, newVersion: Int) {
        db.execSQL("DROP TABLE IF EXISTS $TABLE_NAME")
        onCreate(db)
    }

    companion object {
        private const val DATABASE_VERSION = 1
        private const val DATABASE_NAME = "SoundSettingsDB"
        private const val TABLE_NAME = "SoundSettings"
        private const val COLUMN_ID = "id"
        const val COLUMN_MUSIC_VOLUME = "musicVolume"
        const val COLUMN_MUSIC_VOLUME_ON_OFF = "musicVolumeOnOff"
        const val COLUMN_EFFECT_VOLUME = "effectVolume"
        const val COLUMN_EFFECT_VOLUME_ON_OFF = "effectVolumeOnOff"
    }

    fun saveSoundSettings(musicVolume: Int, musicVolumeOnOff: Int, effectVolume: Int, effectVolumeOnOff: Int) {
        val db = this.writableDatabase

        val values = ContentValues().apply {
            put(COLUMN_MUSIC_VOLUME, musicVolume)
            put(COLUMN_MUSIC_VOLUME_ON_OFF, musicVolumeOnOff)
            put(COLUMN_EFFECT_VOLUME, effectVolume)
            put(COLUMN_EFFECT_VOLUME_ON_OFF, effectVolumeOnOff)
        }

        db.insert(TABLE_NAME, null, values)
        db.close()
    }

    fun getLatestSoundSettings(): ContentValues? {
        val db = this.readableDatabase
        val selectQuery = "SELECT * FROM $TABLE_NAME ORDER BY $COLUMN_ID DESC LIMIT 1"
        val cursor = db.rawQuery(selectQuery, null)

        if (cursor.moveToFirst()) {
            val musicVolumeIndex = cursor.getColumnIndex(COLUMN_MUSIC_VOLUME)
            val musicVolumeOnOffIndex = cursor.getColumnIndex(COLUMN_MUSIC_VOLUME_ON_OFF)
            val effectVolumeIndex = cursor.getColumnIndex(COLUMN_EFFECT_VOLUME)
            val effectVolumeOnOffIndex = cursor.getColumnIndex(COLUMN_EFFECT_VOLUME_ON_OFF)

            // 모든 인덱스가 유효한지 확인
            if (musicVolumeIndex != -1 && musicVolumeOnOffIndex != -1 && effectVolumeIndex != -1 && effectVolumeOnOffIndex != -1) {
                val values = ContentValues().apply {
                    put(COLUMN_MUSIC_VOLUME, cursor.getInt(musicVolumeIndex))
                    put(COLUMN_MUSIC_VOLUME_ON_OFF, cursor.getInt(musicVolumeOnOffIndex))
                    put(COLUMN_EFFECT_VOLUME, cursor.getInt(effectVolumeIndex))
                    put(COLUMN_EFFECT_VOLUME_ON_OFF, cursor.getInt(effectVolumeOnOffIndex))
                }
                cursor.close()
                db.close()
                return values
            } else {
                cursor.close()
                db.close()
                return null
            }
        } else {
            cursor.close()
            db.close()
            return null
        }
    }
}
