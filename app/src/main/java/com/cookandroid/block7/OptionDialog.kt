package com.cookandroid.block7

import android.content.Intent
import android.os.Bundle
import android.widget.ImageButton
import android.widget.SeekBar
import android.widget.Switch


class OptionDialog : BaseActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.option_dialog)

        val musicVolumeControl: SeekBar = findViewById(R.id.music_volume_seekbar)
        val soundVolumeControl: SeekBar = findViewById(R.id.effect_sound_volume_seekbar)
        val musicVolumeOnOffSwitch: Switch = findViewById(R.id.music_volume_onoff)
        val soundVolumeOnOffSwitch: Switch = findViewById(R.id.effect_sound_volume_onoff)

        val dbHelper = SoundSettingsDbHelper(this)
        val latestSettings = dbHelper.getLatestSoundSettings()

        latestSettings?.let {
            val musicVolume = it.getAsInteger(SoundSettingsDbHelper.COLUMN_MUSIC_VOLUME) ?: 100
            val musicVolumeOnOff = it.getAsInteger(SoundSettingsDbHelper.COLUMN_MUSIC_VOLUME_ON_OFF) ?: 1
            val effectVolume = it.getAsInteger(SoundSettingsDbHelper.COLUMN_EFFECT_VOLUME) ?: 100
            val effectVolumeOnOff = it.getAsInteger(SoundSettingsDbHelper.COLUMN_EFFECT_VOLUME_ON_OFF) ?: 1

            // SeekBar와 Switch 상태 업데이트
            musicVolumeControl.progress = musicVolume
            soundVolumeControl.progress = effectVolume
            musicVolumeOnOffSwitch.isChecked = musicVolumeOnOff == 1
            soundVolumeOnOffSwitch.isChecked = effectVolumeOnOff == 1
        }

        val applyButton : ImageButton = findViewById(R.id.apply_button)
        val closeButton : ImageButton = findViewById(R.id.close_button)
        closeButton.setOnClickListener{
            finish()
        }

        applyButton.setOnClickListener {

            val musicVolumeForDB = musicVolumeControl.progress
            val soundVolumeForDB = soundVolumeControl.progress

            val musicVolumeOnOff = if (musicVolumeOnOffSwitch.isChecked) 1 else 0
            val effectVolumeOnOff = if (soundVolumeOnOffSwitch.isChecked) 1 else 0

            // 데이터베이스 헬퍼 인스턴스 생성
            val dbHelper = SoundSettingsDbHelper(this)

            // 데이터 저장
            dbHelper.saveSoundSettings(musicVolumeForDB, musicVolumeOnOff, soundVolumeForDB, effectVolumeOnOff)

            // 인텐트 생성
            val serviceIntent = Intent(this, MusicService::class.java)

            // 스위치 및 시크바 상태 읽기
            val musicVolume = if (!musicVolumeOnOffSwitch.isChecked) musicVolumeControl.progress else 0
            val soundVolume = if (!soundVolumeOnOffSwitch.isChecked) soundVolumeControl.progress else 0

            // 인텐트에 볼륨 데이터 추가
            serviceIntent.putExtra("MUSIC_VOLUME", musicVolume)
            serviceIntent.putExtra("SOUND_VOLUME", soundVolume)
            serviceIntent.action = "CHANGE_VOLUME"

            // 서비스 시작
            startService(serviceIntent)
        }

    }
}

