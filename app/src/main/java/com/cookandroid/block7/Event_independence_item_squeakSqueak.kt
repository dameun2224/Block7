package com.cookandroid.block7

class Event_independence_item_squeakSqueak (GameActivity: GameActivity, eventName: String, type: Int, weight: Int, isAvailable: Boolean)
    : Event(GameActivity, eventName, type, weight, isAvailable) {

    // 생성자 - 기본 preScript, postScript 설정
    init {
        setPreScript(getScript("event_independence_squeakSqueak_pre"))
        setPostScript(getScript("event_independence_squeakSqueak_post"))
        selectionItem = "axe"
    }

    override fun setIsAvailable() {
        if(GameActivity.food_kimbap.count > 1) isAvailable = true
        else isAvailable = false
    }

    // 이벤트 효과 메소드
    override fun eventEffect(choose_value: Boolean) {
        if(choose_value) {
        } else {
            GameActivity.food_kimbap.loseFood(1)
            setPostScript(getScript("event_independence_squeakSqueak_post_tmp"))
        }
    }
}