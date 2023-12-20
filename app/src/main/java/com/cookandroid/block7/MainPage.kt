package com.cookandroid.block7

import android.app.AlertDialog
import android.content.DialogInterface
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.MotionEvent
import android.view.View
import android.widget.ImageButton
import android.widget.ImageView
import android.widget.SeekBar
import android.widget.Switch


class MainPage : BaseActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.main_page)
        startService(Intent(this, MusicService::class.java))

        var gamestart_image : ImageView = findViewById(R.id.gamestart_image)
        var option_image : ImageView = findViewById(R.id.option_image)
        var explanation_image : ImageView = findViewById(R.id.explanation_image)
        var developers_image : ImageView = findViewById(R.id.developers_image)
        var quit_image : ImageView = findViewById(R.id.quit_image)

        var gamestart_button : ImageButton = findViewById(R.id.startGame)
        var option_button : ImageButton = findViewById(R.id.option_game)
        var explanation_button : ImageButton = findViewById(R.id.explanation_button)
        var developers_button : ImageButton = findViewById(R.id.developers_button)
        var quit_button : ImageButton = findViewById(R.id.quit_button)


        val array = arrayOf(" ")

        gamestart_button.setOnTouchListener { v, event ->
            when (event.action) {
                MotionEvent.ACTION_DOWN -> {
                    // 버튼이 눌렸을 때의 이미지로 변경
                    gamestart_image.setImageResource(R.drawable.menu_gamestart_clicked)
                }
                MotionEvent.ACTION_UP, MotionEvent.ACTION_CANCEL -> {
                    // 버튼에서 손을 떼었을 때의 원래 이미지로 변경
                    gamestart_image.setImageResource(R.drawable.menu_gamestart)
                }
            }
            false
        }

        option_button.setOnTouchListener { v, event ->
            when (event.action) {
                MotionEvent.ACTION_DOWN -> {
                    // 버튼이 눌렸을 때의 이미지로 변경
                    option_image.setImageResource(R.drawable.menu_option_clicked)
                }
                MotionEvent.ACTION_UP, MotionEvent.ACTION_CANCEL -> {
                    // 버튼에서 손을 떼었을 때의 원래 이미지로 변경
                    option_image.setImageResource(R.drawable.menu_option)
                }
            }
            false
        }


        developers_button.setOnTouchListener { v, event ->
            when (event.action) {
                MotionEvent.ACTION_DOWN -> {
                    // 버튼이 눌렸을 때의 이미지로 변경
                    developers_image.setImageResource(R.drawable.menu_developers_clicked)
                }
                MotionEvent.ACTION_UP, MotionEvent.ACTION_CANCEL -> {
                    // 버튼에서 손을 떼었을 때의 원래 이미지로 변경
                    developers_image.setImageResource(R.drawable.menu_developers)

                }
            }
            false

        }


        explanation_button.setOnTouchListener { v, event ->
            when (event.action) {
                MotionEvent.ACTION_DOWN -> {
                    // 버튼이 눌렸을 때의 이미지로 변경
                    explanation_image.setImageResource(R.drawable.menu_explanation_clicked)
                }
                MotionEvent.ACTION_UP, MotionEvent.ACTION_CANCEL -> {
                    // 버튼에서 손을 떼었을 때의 원래 이미지로 변경
                    explanation_image.setImageResource(R.drawable.menu_explanation)
                }
            }
            false
        }

        explanation_button.setOnClickListener{
            click_sound()
            val intent = Intent(this, MoreDialog::class.java)
            startActivity(intent)

    }

        developers_button.setOnClickListener {
            click_sound()

            // AlertDialog 생성
            val alertDialog = AlertDialog.Builder(this)
                .setTitle(" ")
                .setItems(array) { dialog: DialogInterface, which: Int ->
                    val currentItem = array[which]
                    Log.d("MyTag", "currentItem: $currentItem")
                }
                .show()

            // AlertDialog의 Window에 직접 배경 설정
            val window = alertDialog.window
            window?.setBackgroundDrawableResource(R.drawable.developer)
        }



        quit_button.setOnTouchListener { v, event ->
            when (event.action) {
                MotionEvent.ACTION_DOWN -> {
                    // 버튼이 눌렸을 때의 이미지로 변경
                    quit_image.setImageResource(R.drawable.menu_quit_clicked)
                }
                MotionEvent.ACTION_UP, MotionEvent.ACTION_CANCEL -> {
                    // 버튼에서 손을 떼었을 때의 원래 이미지로 변경
                    quit_image.setImageResource(R.drawable.menu_quit)
                }
            }
            false
        }





        // 게임 시작 버튼에 클릭 리스너 추가
        gamestart_button.setOnClickListener {
            click_sound()
            val intent = Intent(this, GameActivity::class.java)
            startActivity(intent)

        }

        var option_dialog : View = findViewById(R.id.option_dialog)
        option_dialog.visibility = View.GONE

        // 옵션 이미지에 클릭 리스너 추가
        option_button.setOnClickListener {
            click_sound()
            option_dialog.visibility = View.VISIBLE
        }


        // 종료 이미지에 클릭 리스너 추가
        val exitFrame : ImageButton = findViewById(R.id.quit_button)
        exitFrame.setOnClickListener {
            click_sound()
            finishAffinity()
        }

        val musicVolumeControl: SeekBar = findViewById(R.id.music_volume_seekbar)
        val soundVolumeControl: SeekBar = findViewById(R.id.effect_sound_volume_seekbar)
        val musicVolumeOnOffSwitch: Switch = findViewById(R.id.music_volume_onoff)
        val soundVolumeOnOffSwitch: Switch = findViewById(R.id.effect_sound_volume_onoff)

        val dbHelper = SoundSettingsDbHelper(this)
        val latestSettings = dbHelper.getLatestSoundSettings()

        latestSettings?.let {
            val musicVolume = it.getAsInteger(SoundSettingsDbHelper.COLUMN_MUSIC_VOLUME) ?: 100
            val musicVolumeOnOff = it.getAsInteger(SoundSettingsDbHelper.COLUMN_MUSIC_VOLUME_ON_OFF) ?: 0
            val effectVolume = it.getAsInteger(SoundSettingsDbHelper.COLUMN_EFFECT_VOLUME) ?: 100
            val effectVolumeOnOff = it.getAsInteger(SoundSettingsDbHelper.COLUMN_EFFECT_VOLUME_ON_OFF) ?: 0

            // SeekBar와 Switch 상태 업데이트
            musicVolumeControl.progress = musicVolume
            soundVolumeControl.progress = effectVolume
            musicVolumeOnOffSwitch.isChecked = musicVolumeOnOff == 0
            soundVolumeOnOffSwitch.isChecked = effectVolumeOnOff == 0
        }

        val applyButton : ImageButton = findViewById(R.id.apply_button)
        val closeButton : ImageButton = findViewById(R.id.option_dialog_close_button)
        closeButton.setOnClickListener{
            option_dialog.visibility = View.GONE
        }


        applyButton.setOnClickListener {

            val musicVolumeForDB = musicVolumeControl.progress
            val soundVolumeForDB = soundVolumeControl.progress

            val musicVolumeOnOff = if (musicVolumeOnOffSwitch.isChecked) 0 else 1
            val effectVolumeOnOff = if (soundVolumeOnOffSwitch.isChecked) 0 else 1

            // 데이터베이스 헬퍼 인스턴스 생성
            val dbHelper = SoundSettingsDbHelper(this)

            // 데이터 저장
            dbHelper.saveSoundSettings(musicVolumeForDB, musicVolumeOnOff, soundVolumeForDB, effectVolumeOnOff)

            // 인텐트 생성
            val serviceIntent = Intent(this, MusicService::class.java)

            // 스위치 및 시크바 상태 읽기
            val musicVolume = if (musicVolumeOnOffSwitch.isChecked) musicVolumeControl.progress else 1
            val soundVolume = if (soundVolumeOnOffSwitch.isChecked) soundVolumeControl.progress else 1

            // 인텐트에 볼륨 데이터 추가
            serviceIntent.putExtra("MUSIC_VOLUME", musicVolume)
            serviceIntent.putExtra("SOUND_VOLUME", soundVolume)
            serviceIntent.action = "CHANGE_VOLUME"

            // 서비스 시작
            startService(serviceIntent)
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


