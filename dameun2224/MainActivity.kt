package com.example.myapplication

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import androidx.appcompat.app.AlertDialog

class MainActivity : AppCompatActivity() {
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

            fun dayGo() {
                hungry++
                thirsty++
                crazy++
                fatigued++
                sick++
                tired++
            }

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

        var a = person(findViewById(R.id.a),"a")
        var b = person(findViewById(R.id.b), "b")
        var c = person(findViewById(R.id.c), "c")
        var d = person(findViewById(R.id.d), "d")


        fun dayGoAll() {
            a.dayGo()
            b.dayGo()
            c.dayGo()
            d.dayGo()
        }

        val goBtn : Button = findViewById(R.id.go)
        goBtn.setOnClickListener {
            dayGoAll()
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
