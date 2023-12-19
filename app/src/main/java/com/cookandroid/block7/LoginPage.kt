package com.cookandroid.block7

import android.content.Intent
import android.os.Build
import android.os.Bundle
import android.view.MotionEvent
import android.view.View
import android.view.WindowInsets
import android.view.WindowInsetsController
import android.view.WindowManager
import android.widget.EditText
import android.widget.ImageButton
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.constraintlayout.widget.ConstraintLayout
import com.bumptech.glide.Glide

class MainActivity : BaseActivity() {

    var loginstate: Boolean = false
    private val mysqlConnection = MysqlConnection()

    override fun onCreate(savedInstanceState: Bundle?) {
        window.addFlags(WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON)

        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        window.decorView.systemUiVisibility = View.SYSTEM_UI_FLAG_FULLSCREEN
        actionBar?.hide()


        val customApplication = application as CustomApplication
        val studentId = customApplication.studentId

        val gamestart_button = findViewById<ImageButton>(R.id.gamestart_button)

        val loginDialog: ConstraintLayout = findViewById(R.id.login_dialog)
        loginDialog.elevation = 10f
        loginDialog.visibility = View.GONE

        val signupDialog: ConstraintLayout = findViewById(R.id.signup_dialog)
        signupDialog.elevation = 11f  // 값은 원하는 높이에 따라 조정할 수 있습니다.
        signupDialog.visibility = View.GONE

        val loginDialog_loginButton: ImageButton = findViewById(R.id.login_button)

        loginDialog_loginButton.setOnClickListener {
            val editText = findViewById<EditText>(R.id.login_studentid)
            val studentId = editText.text.toString()
            handleLogin(studentId) // 로그인 처리 메서드
        }

        val loginDialog_closeButton: ImageButton = findViewById(R.id.close_button)
        val loginDialog_registerBUtton: ImageButton = findViewById(R.id.register)


        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.R) {
            window.insetsController?.let {
                it.systemBarsBehavior = WindowInsetsController.BEHAVIOR_SHOW_TRANSIENT_BARS_BY_SWIPE
                it.hide(WindowInsets.Type.systemBars())
            }
        } else {
            @Suppress("DEPRECATION")
            window.decorView.systemUiVisibility = View.SYSTEM_UI_FLAG_FULLSCREEN or
                    View.SYSTEM_UI_FLAG_HIDE_NAVIGATION or
                    View.SYSTEM_UI_FLAG_IMMERSIVE_STICKY
        }

        //안개 gif파일 불러오는 라이브러리 호출
        val imageView: ImageView = findViewById(R.id.background_fog_effect)
        Glide.with(this)
            .asGif()
            .load(R.drawable.fog_effect)
            .into(imageView)

        gamestart_button.setOnTouchListener { view, motionEvent ->
            when (motionEvent.action) {
                MotionEvent.ACTION_DOWN -> {
                    gamestart_button.setImageResource(R.drawable.gamestart_button_pressed)
                }
                MotionEvent.ACTION_UP -> {
                    gamestart_button.setImageResource(R.drawable.gamestart_button_unpressed)
                    if(loginstate == false){
                        loginDialog.visibility = View.VISIBLE
                    }
                    else{
                        val intent = Intent(this, MainPage::class.java)
                        startActivity(intent)
                    }
                }
                MotionEvent.ACTION_CANCEL -> {
                    gamestart_button.setImageResource(R.drawable.gamestart_button_unpressed)
                }
            }
            true
        }

        loginDialog_closeButton.setOnTouchListener{view, motionEvent ->
            when (motionEvent.action) {
                MotionEvent.ACTION_DOWN -> {
                    loginDialog_closeButton.setImageResource(R.drawable.close_button_pressed)
                }
                MotionEvent.ACTION_UP -> {
                    loginDialog_closeButton.setImageResource(R.drawable.close_button)
                    loginDialog.visibility = View.GONE
                }
                MotionEvent.ACTION_CANCEL -> {
                    loginDialog_closeButton.setImageResource(R.drawable.close_button)
                }
            }
            true
        }
        val studentidEditText : EditText = findViewById(R.id.register_studentid)
        val usernameEditText : EditText = findViewById(R.id.register_username)

        val signUpButton : ImageButton = findViewById(R.id.register_button)
        val signupDialog_closeButton : ImageButton = findViewById(R.id.registerdialog_close_button)

        loginDialog_registerBUtton.setOnClickListener({
            signupDialog.visibility = View.VISIBLE
        })

        signUpButton.setOnClickListener {
            val studentId = studentidEditText.text.toString()
            val username = usernameEditText.text.toString()

            if (studentId.length != 9) {
                Toast.makeText(this@MainActivity, "학번은 9자리여야 합니다!", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }

            else if (!studentId.matches(Regex("[0-9]+"))) {
                Toast.makeText(this@MainActivity, "학번은 숫자만 포함되어야 합니다!", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }

            else {
                registerNewPlayer(studentId, username)
            }
        }

        signupDialog_closeButton.setOnTouchListener{view, motionEvent ->
            when (motionEvent.action) {
                MotionEvent.ACTION_DOWN -> {
                    signupDialog_closeButton.setImageResource(R.drawable.close_button_pressed)
                }
                MotionEvent.ACTION_UP -> {
                    signupDialog_closeButton.setImageResource(R.drawable.close_button)
                    signupDialog.visibility = View.GONE
                }
                MotionEvent.ACTION_CANCEL -> {
                    signupDialog_closeButton.setImageResource(R.drawable.close_button)
                }
            }
            true
        }
    }

    private fun handleLogin(studentId: String) {
        getPlayersDataByStudentId(studentId) { isDataAvailable ->
            if (isDataAvailable) {
                Toast.makeText(this@MainActivity, "로그인 성공!", Toast.LENGTH_SHORT).show()
                val loginStateTextView: TextView = findViewById(R.id.login_state)
                loginStateTextView.text = "$studentId"

                val customApplication = application as CustomApplication
                customApplication.studentId = studentId

                loginstate = true
                findViewById<ConstraintLayout>(R.id.login_dialog).visibility = View.GONE
            } else {
                // 로그인 실패 처리
                val failAlert = AlertDialog.Builder(this@MainActivity)
                failAlert.setMessage("로그인 실패. 학번을 확인해주세요.")
                failAlert.setPositiveButton("확인") { _, _ ->
                    // 필요한 경우 로그인 다이얼로그를 다시 표시합니다.
                }
                failAlert.show()
            }
        }
    }

    private fun registerNewPlayer(studentId: String, username: String) {
        mysqlConnection.putPlayer(studentId, username) { isSuccess, message ->
            runOnUiThread {
                if (isSuccess) {
                    // 등록 성공 메시지를 보여줍니다
                    Toast.makeText(this, message, Toast.LENGTH_SHORT).show()
                } else {
                    // 에러 메시지를 보여줍니다
                    Toast.makeText(this, message, Toast.LENGTH_LONG).show()
                }
            }
        }
    }

    private fun getPlayersDataByStudentId(studentId: String, callback: (Boolean) -> Unit) {
        mysqlConnection.getPlayerByStudentId(studentId) { player ->
            runOnUiThread {
                val isDataAvailable = player != null
                callback(isDataAvailable)
            }
        }
    }

    private fun getAllPlayersData() {
        mysqlConnection.getAllPlayer { players ->
            runOnUiThread {
            }
        }
    }

}

