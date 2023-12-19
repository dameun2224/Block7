package com.cookandroid.block7

import kotlin.random.Random

class Event_independence_ox_poorSurvivorStrong (GameActivity: GameActivity, eventName: String, type: Int, weight: Int, isAvailable: Boolean)
    : Event(GameActivity, eventName, type, weight, isAvailable) {

    // 생성자 - 기본 preScript, postScript 설정
    init {
        setPreScript(getScript("event_independence_phoneManipulation_pre"))
        setPostScript(getScript("event_independence_phoneManipulation_post"))
    }

    override fun setIsAvailable() {
        if(!GameActivity.item_phone.getIsOwned() && GameActivity.food_kimbap.count > 2) isAvailable = true
        else isAvailable = false
    }

    // 이벤트 효과 메소드
    override fun eventEffect(choose_value: Boolean) {
        if(choose_value) {
            GameActivity.item_phone.getItem()
            GameActivity.food_kimbap.loseFood(1)
        } else {
            val randomMember = if (GameActivity.memberListIsIn.isNotEmpty())
                GameActivity.memberListIsIn[Random.nextInt(GameActivity.memberListIsIn.size)] else null

            randomMember?.die()
            setPostScript(getScript("event_independence_phoneManipulation_post_tmp"))
        }
    }
}