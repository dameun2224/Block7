package com.cookandroid.block7

class Event_independence_ox_poorSurvivorNormal (GameActivity: GameActivity, eventName: String,  type: Int, weight: Int, isAvailable: Boolean)
    : Event(GameActivity, eventName, type, weight, isAvailable) {

    // 생성자 - 기본 preScript, postScript 설정
    init {
        setPreScript(getScript("event_independence_poorSurvivorNormal_pre"))
        setPostScript(getScript("event_independence_poorSurvivorNormal_post"))
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
            setPostScript(getScript("event_independence_poorSurvivorNormal_post_tmp"))
        }
    }
}