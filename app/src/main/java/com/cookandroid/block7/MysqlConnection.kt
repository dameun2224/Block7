package com.cookandroid.block7

import android.util.Log
import com.google.gson.annotations.SerializedName
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.PUT
import retrofit2.http.Path

data class Player(
    @SerializedName("student_id") val studentId: String,
    @SerializedName("currentexp") var currentExp: Int,
    @SerializedName("gold") var gold: Int,
    @SerializedName("gems") var gems: Int,
    @SerializedName("username") var username: String,
)

data class NewPlayer(
    val studentId: String,
    val username: String
)


interface CloudFunctionService {
    @GET("player/getPlayer")
    fun getAllPlayer(): Call<List<Player>>

    @GET("player/getPlayer/{studentId}")
    fun getPlayerByStudentId(@Path("studentId") studentId: String): Call<List<Player>>

    @PUT("player/putPlayer")
    fun putPlayer(@Body newPlayer: NewPlayer): Call<Void>
}

class MysqlConnection {
    val retrofit = Retrofit.Builder()
        .baseUrl("https://asia-northeast3-curious-system-403802.cloudfunctions.net/mysql_connection/")
        .addConverterFactory(GsonConverterFactory.create())
        .build()


    private val service = retrofit.create(CloudFunctionService::class.java)

    fun getAllPlayer(callback: (List<Player>) -> Unit) {
        service.getAllPlayer().enqueue(object : Callback<List<Player>> {
            override fun onResponse(call: Call<List<Player>>, response: Response<List<Player>>) {
                if (response.isSuccessful) {
                    val players = response.body() ?: listOf()
                    callback(players)
                } else {
                    Log.e("API Error", "Response Code: " + response.code())
                }
            }

            override fun onFailure(call: Call<List<Player>>, t: Throwable) {
                Log.e("API Error", "Network Error", t)
            }
        })
    }

    // 특정 학번의 Player 정보를 가져오는 새로운 메소드
    fun getPlayerByStudentId(studentId: String, callback: (Player?) -> Unit) {
        service.getPlayerByStudentId(studentId).enqueue(object : Callback<List<Player>> {
            override fun onResponse(call: Call<List<Player>>, response: Response<List<Player>>) {
                if (response.isSuccessful) {
                    // 성공적으로 데이터를 받아왔을 때, 첫 번째 Player 객체를 콜백에 전달합니다.
                    val player = response.body()?.firstOrNull()
                    callback(player)
                } else {
                    // 오류 응답 처리
                    Log.e("API Error", "Response Code: " + response.code())
                    callback(null)
                }
            }

            override fun onFailure(call: Call<List<Player>>, t: Throwable) {
                // 네트워크 오류 처리
                Log.e("API Error", "Network Error", t)
                callback(null)
            }
        })
    }

    fun putPlayer(studentId: String, username: String, callback: (Boolean, String) -> Unit) {
        val newPlayer = NewPlayer(studentId, username)
        service.putPlayer(newPlayer).enqueue(object : Callback<Void> {
            override fun onResponse(call: Call<Void>, response: Response<Void>) {
                if (response.isSuccessful) {
                    // If the server returns a 201 status code, the registration was successful.
                    callback(true, "등록 완료")
                } else {
                    // Handle the different error cases based on the status code
                    when (response.code()) {
                        409 -> callback(false, if (response.message().contains("학번")) "해당 학번이 이미 존재합니다" else "해당 이름은 사용중입니다")
                        else -> callback(false, "Error: ${response.code()} ${response.message()}")
                    }
                }
            }

            override fun onFailure(call: Call<Void>, t: Throwable) {
                // Network error handling
                Log.e("API Error", "Network Error", t)
                callback(false, "Network Error: ${t.message}")
            }
        })
    }
}
