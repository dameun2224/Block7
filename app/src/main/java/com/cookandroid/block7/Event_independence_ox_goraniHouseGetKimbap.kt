package com.cookandroid.block7

class Event_independence_ox_goraniHouseGetKimbap(GameActivity: GameActivity, eventName: String, type: Int, weight: Int, isAvailable: Boolean)
    : Event(GameActivity, eventName, type, weight, isAvailable) {

    // 생성자 - 기본 preScript, postScript 설정
    init {
        setPreScript(getScript("event_independence_goraniHouseGetKimbap_pre"))
        setPostScript(getScript("event_independence_goraniHouseGetKimbap_post"))
    }

    override fun setIsAvailable() {
        isAvailable = true
    }

    // 이벤트 효과 메소드
    override fun eventEffect(choose_value: Boolean) {
        if(choose_value) {
            GameActivity.food_kimbap.getFood(2)
        } else {
            setPostScript(getScript("event_independence_goraniHouseGetKimbap_post_tmp"))
        }
    }
}