package com.example.myapplication

import android.annotation.SuppressLint
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.CountDownTimer
import android.view.View
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.app.AlertDialog

class MainActivity : AppCompatActivity() {
    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // 사람 클래스
        class person(personView: View, name: String) {
            var personView: View = personView
            var name: String = name
            var hungry: Int = 0
            var thirsty: Int = 0
            var crazy: Int = 0
            var fatigued: Int = 0
            var sick: Int = 0
            var tired: Int = 0

            // 하루가 지나면 스탯이 1씩 오름
            fun dayGo() {
                hungry++
                thirsty++
                crazy++
                fatigued++
                sick++
                tired++
            }

            // 각 객체와 연결된 View 클릭 시 객체의 상태를 AlertDialog로 보여줌
            fun showPropertiesDialog() {
                val dialogBuilder = AlertDialog.Builder(this@MainActivity)
                dialogBuilder.setTitle(name)
                dialogBuilder.setMessage(
                    "Hungry: $hungry\n" +
                            "Thirsty: $thirsty\n" +
                            "Crazy: $crazy\n" +
                            "Fatigued: $fatigued\n" +
                            "Sick: $sick\n" +
                            "Tired: $tired"
                )
                dialogBuilder.setPositiveButton("OK") { dialog, _ ->
                    dialog.dismiss()
                }
                val dialog = dialogBuilder.create()
                dialog.show()
            }
        }

        // 사람 객체 생성
        var a = person(findViewById(R.id.a),"a")
        var b = person(findViewById(R.id.b), "b")
        var c = person(findViewById(R.id.c), "c")
        var d = person(findViewById(R.id.d), "d")

        val goBtn : Button = findViewById(R.id.go)

        val blackView : View = findViewById(R.id.blackView)

        val dayTextView : TextView = findViewById(R.id.day)
        var day : Int = 1

        // fo 버튼 클릭 시 호출됨. 모든 객체의 스탯 증가
        fun dayGoAll() {
            a.dayGo()
            b.dayGo()
            c.dayGo()
            d.dayGo()
        }

        // go 버튼 클릭 리스너
        goBtn.setOnClickListener {
            // 검은 화면을 보이게 하고 2초 후에 다시 가리기
            blackView.visibility = View.VISIBLE
            object : CountDownTimer(2000, 1000) {
                override fun onTick(millisUntilFinished: Long) {
                    // Do nothing on tick
                }

                override fun onFinish() {
                    blackView.visibility = View.INVISIBLE
                    dayGoAll()
                    day++
                    dayTextView.text = "Day " + day.toString()
                }
            }.start()
        }

        // 각 personView에 대한 클릭 이벤트 설정
        a.personView.setOnClickListener {
            a.showPropertiesDialog()
        }
        b.personView.setOnClickListener {
            b.showPropertiesDialog()
        }
        c.personView.setOnClickListener {
            c.showPropertiesDialog()
        }
        d.personView.setOnClickListener {
            d.showPropertiesDialog()
        }

    }
}
