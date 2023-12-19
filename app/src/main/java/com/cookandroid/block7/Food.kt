package com.cookandroid.block7

import android.graphics.drawable.Drawable
import android.view.View
import android.widget.ImageView

class Food(GameActivity: GameActivity, nameFood: String, nameKorFood: String, foodImage: ImageView) {
    var GameActivity = GameActivity

    // 식량 이름 (음식 or 물)
    var nameFood: String = nameFood
    var nameKorFood: String = nameKorFood
    var foodImage = foodImage

    // 식량 개수
    var count: Int = 0

    // 식량 획득 메소드
    fun getFood(cnt: Int) {
        this.count += cnt
    }

    // 식량 소모 메소드
    fun loseFood(cnt: Int) {
        count = maxOf(0, count - cnt)
    }

    fun getFoodRandom(start: Int, end: Int) {
        var cnt: Int = (start..end).random()
        getFood(cnt)
    }

    fun loseFoodRandom(start: Int, end: Int) {
        var lostCount: Int = (start..end).random()
        loseFood(lostCount)
    }

    // food의 이미지 업데이트 메소드
    fun updateVisibility() {
        // 0개도 else에 걸려서 max값 이미지로 변경됨
        // 수정필요
        var resourceId = 0
        if(nameFood == "kimbap") {
            resourceId = when (count) {
                1 -> R.drawable.food_kimbap_1
                2 -> R.drawable.food_kimbap_2
                3 -> R.drawable.food_kimbap_3
                4 -> R.drawable.food_kimbap_4
                5 -> R.drawable.food_kimbap_5
                6 -> R.drawable.food_kimbap_6
                else -> R.drawable.food_kimbap_1
            }
            if(count == 0) foodImage.visibility = View.GONE
            else GameActivity.findViewById<ImageView>(R.id.food_kimbap).setImageResource(resourceId)
        } else if(nameFood == "water") {
            resourceId = when (count) {
                1 -> R.drawable.food_water_1
                2 -> R.drawable.food_water_2
                3 -> R.drawable.food_water_3
                4 -> R.drawable.food_water_4
                5 -> R.drawable.food_water_5
                6 -> R.drawable.food_water_6
                else -> R.drawable.food_kimbap_1
            }
            if(count == 0) foodImage.visibility = View.GONE
            else GameActivity.findViewById<ImageView>(R.id.food_water).setImageResource(resourceId)
        }
    }

    // 깊은 복사 생성자
    constructor(food: Food) : this(food.GameActivity, food.nameFood, food.nameKorFood, food.foodImage) {
        this.count = food.count
    }
}