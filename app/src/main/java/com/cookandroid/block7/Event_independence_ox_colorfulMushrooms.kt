package com.cookandroid.block7

class Event_independence_ox_colorfulMushrooms (GameActivity: GameActivity, eventName: String, type: Int, weight: Int, isAvailable: Boolean)
    : Event(GameActivity, eventName, type, weight, isAvailable) {

    // 생성자 - 기본 preScript, postScript 설정
    init {
        setPreScript(getScript("event_independence_colorfulMushrooms_pre"))
        setPostScript(getScript("event_independence_colorfulMushrooms_post"))
    }

    override fun setIsAvailable() {
        isAvailable = true
    }

    // 이벤트 효과 메소드
    override fun eventEffect(choose_value: Boolean) {
        if(choose_value) {
            GameActivity.member_dameun.eatFull()
            GameActivity.member_soun.eatFull()
            GameActivity.member_eunju.eatFull()
            GameActivity.member_hyundong.eatFull()

        } else {
            val selectedMembers = GameActivity.memberListIsIn.shuffled().take(1)
            // 선택된 멤버에 대해 stateChangeSick() 적용
            selectedMembers.forEach { member ->
                member.stateChangeSick()
            }
            setPostScript(getScript("event_independence_colorfulMushrooms_post_tmp"))
        }
    }
}