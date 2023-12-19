package com.cookandroid.block7

class Event_independence_ox_askWaterFromMedStudents (GameActivity: GameActivity, eventName: String, type: Int, weight: Int, isAvailable: Boolean)
    : Event(GameActivity, eventName, type, weight, isAvailable) {

    // 생성자 - 기본 preScript, postScript 설정
    init {
        setPreScript(getScript("event_independence_askWaterFromMedStudents_pre"))
        setPostScript(getScript("event_independence_askWaterFromMedStudents_post"))
    }

    override fun setIsAvailable() {
        if(GameActivity.food_water.count > 2) isAvailable = true
        else isAvailable = false
    }

    // 이벤트 효과 메소드
    override fun eventEffect(choose_value: Boolean) {
        if(choose_value) {
            GameActivity.member_dameun.stateChangeNotSick()
            GameActivity.member_soun.stateChangeNotSick()
            GameActivity.member_eunju.stateChangeNotSick()
            GameActivity.member_hyundong.stateChangeNotSick()

        } else {
            setPostScript(getScript("event_independence_askWaterFromMedStudents_post_tmp"))
        }
    }
}