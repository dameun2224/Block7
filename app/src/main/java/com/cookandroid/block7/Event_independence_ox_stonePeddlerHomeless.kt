package com.cookandroid.block7

class Event_independence_ox_stonePeddlerHomeless (GameActivity: GameActivity, eventName: String, type: Int, weight: Int, isAvailable: Boolean)
    : Event(GameActivity, eventName, type, weight, isAvailable) {

    // 생성자 - 기본 preScript, postScript 설정
    init {
        setPreScript(getScript("event_independence_stonePeddlerHomeless_pre"))
        setPostScript(getScript("event_independence_stonePeddlerHomeless_post"))
    }

    override fun setIsAvailable() {
        isAvailable = true
    }

    // 이벤트 효과 메소드
    override fun eventEffect(choose_value: Boolean) {
        if(choose_value) {
            GameActivity.member_dameun.drinkFull()
            GameActivity.member_soun.drinkFull()
            GameActivity.member_eunju.drinkFull()
            GameActivity.member_hyundong.drinkFull()
        } else {
            setPostScript(getScript("event_independence_stonePeddlerHomeless_post_tmp"))
        }
    }
}