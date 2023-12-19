package com.cookandroid.block7

import android.app.Service
import android.content.Intent
import android.media.MediaPlayer
import android.os.IBinder

class MusicService : Service() {
    private var backgroundMusicPlayer: MediaPlayer? = null
    private var clickSoundPlayer: MediaPlayer? = null

    override fun onCreate() {
        super.onCreate()

        val dbHelper = SoundSettingsDbHelper(this)
        val latestSettings = dbHelper.getLatestSoundSettings()

        // 초기 배경음악 설정
        backgroundMusicPlayer = MediaPlayer.create(this, R.raw.mainpage_background_music)
        backgroundMusicPlayer?.isLooping = true
        clickSoundPlayer = MediaPlayer.create(this, R.raw.button_click_music)

        // 설정이 있으면 볼륨 적용
        latestSettings?.let {
            val musicVolume = it.getAsInteger(SoundSettingsDbHelper.COLUMN_MUSIC_VOLUME) ?: 100
            val musicVolumeOnOff = it.getAsInteger(SoundSettingsDbHelper.COLUMN_MUSIC_VOLUME_ON_OFF) ?: 1
            val effectVolume = it.getAsInteger(SoundSettingsDbHelper.COLUMN_EFFECT_VOLUME) ?: 100
            val effectVolumeOnOff = it.getAsInteger(SoundSettingsDbHelper.COLUMN_EFFECT_VOLUME_ON_OFF) ?: 1

            // 볼륨 적용
            val musicVolumeFloat = if (musicVolumeOnOff == 0) musicVolume / 100f else 0f
            backgroundMusicPlayer?.setVolume(musicVolumeFloat, musicVolumeFloat)

            val soundVolumeFloat = if (effectVolumeOnOff == 0) effectVolume / 100f else 0f
            clickSoundPlayer?.setVolume(soundVolumeFloat, soundVolumeFloat)
        }

        backgroundMusicPlayer?.start()
    }

    override fun onStartCommand(intent: Intent, flags: Int, startId: Int): Int {
        when (intent.action) {
            "CHANGE_MUSIC" -> {
                // 새로운 배경음악 리소스 ID 가져오기
                val musicResId = intent.getIntExtra("MUSIC_RES_ID", R.raw.mainpage_background_music)
                changeBackgroundMusic(musicResId)
            }
            "PLAY_CLICK_SOUND" -> clickSoundPlayer?.start()
            "CHANGE_VOLUME" -> {
                // 볼륨 데이터 읽기
                val musicVolume = intent.getIntExtra("MUSIC_VOLUME", 100)
                val soundVolume = intent.getIntExtra("SOUND_VOLUME", 100)

                // MediaPlayer 볼륨 설정
                val musicVolumeFloat = musicVolume / 100f
                backgroundMusicPlayer?.setVolume(musicVolumeFloat, musicVolumeFloat)

                val soundVolumeFloat = soundVolume / 100f
                clickSoundPlayer?.setVolume(soundVolumeFloat, soundVolumeFloat)
            }
        }
        return START_STICKY
    }


    private fun changeBackgroundMusic(musicResId: Int) {
        backgroundMusicPlayer?.stop()
        backgroundMusicPlayer?.release()
        backgroundMusicPlayer = MediaPlayer.create(this, musicResId)
        backgroundMusicPlayer?.isLooping = true
        backgroundMusicPlayer?.start()
    }

    override fun onDestroy() {
        super.onDestroy()
        backgroundMusicPlayer?.stop()
        backgroundMusicPlayer?.release()
        clickSoundPlayer?.release()
    }

    override fun onBind(intent: Intent?): IBinder? {
        return null
    }
}
