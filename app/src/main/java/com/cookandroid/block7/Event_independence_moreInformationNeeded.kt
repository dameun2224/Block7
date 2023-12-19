package com.cookandroid.block7

class Event_independence_moreInformationNeeded (GameActivity: GameActivity, eventName: String,  type: Int, weight: Int, isAvailable: Boolean)
    : Event(GameActivity, eventName, type, weight, isAvailable) {

    // 생성자 - 기본 preScript, postScript 설정
    init {
        setPreScript(getScript("event_independence_moreInformationNeeded_pre"))
        setPostScript(getScript("event_independence_moreInformationNeeded_post"))
    }

    override fun setIsAvailable() {
        if(GameActivity.item_phone.getIsOwned()) isAvailable = true
        else isAvailable = false

    }

    // 이벤트 효과 메소드
    override fun eventEffect(choose_value: Boolean) {
        if(choose_value) {
            GameActivity.item_phone.breakItem()
        } else {
            val selectedMembers = GameActivity.memberListIsIn.shuffled().take(1)
            // 선택된 멤버에 대해 stateChangeSick() 적용
            selectedMembers.forEach { member ->
                member.stateChangeCrazy()
            }
            setPostScript(getScript("event_independence_moreInformationNeeded_tmp"))
        }
    }
}