package com.cookandroid.block7

import android.content.Intent
import android.os.Bundle
import android.provider.Settings.Global
import android.widget.ImageButton


class MainPage : BaseActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.main_page)
        startService(Intent(this, MusicService::class.java))

        // 게임 시작 버튼에 클릭 리스너 추가
        val startGameFrame : ImageButton = findViewById(R.id.startGame)
        startGameFrame.setOnClickListener {
            click_sound()
            val intent = Intent(this, GameActivity::class.java)
            startActivity(intent)

        }

        // 옵션 이미지에 클릭 리스너 추가
        val optionButton: ImageButton = findViewById(R.id.option_game)
        optionButton.setOnClickListener {
            click_sound()
            val intent = Intent(this, OptionDialog::class.java)
            startActivity(intent)
        }

        // 종료 이미지에 클릭 리스너 추가
        val exitFrame : ImageButton = findViewById(R.id.Cancel)
        exitFrame.setOnClickListener {
            finishAffinity()
        }

    }

    override fun onDestroy() {
        super.onDestroy()
        stopService(Intent(this, MusicService::class.java))
    }

    fun click_sound(){
        val music_intent = Intent(this, MusicService::class.java)
        music_intent.action = "PLAY_CLICK_SOUND"
        startService(music_intent)
    }
}

