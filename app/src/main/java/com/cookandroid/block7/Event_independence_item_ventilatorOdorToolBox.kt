package com.cookandroid.block7

class Event_independence_item_ventilatorOdorToolBox (GameActivity: GameActivity, eventName: String, type: Int, weight: Int, isAvailable: Boolean)
    : Event(GameActivity, eventName, type, weight, isAvailable) {

    // 생성자 - 기본 preScript, postScript 설정
    init {
        setPreScript(getScript("event_independence_ventilatorOdorToolBox_pre"))
        setPostScript(getScript("event_independence_ventilatorOdorToolBox_post"))
        selectionItem = "toolbox"
    }

    override fun setIsAvailable() {
        isAvailable = true
    }

    // 이벤트 효과 메소드
    override fun eventEffect(choose_value: Boolean) {
        if(choose_value) {
            val selectedMembers = GameActivity.memberListIsIn.shuffled().take(1)
            // 선택된 멤버에 대해 stateChangeSick() 적용
            selectedMembers.forEach { member ->
                member.stateChangeHurt()
            }
        } else {
            val selectedMembers = GameActivity.memberListIsIn.shuffled().take(1)
            // 선택된 멤버에 대해 stateChangeSick() 적용
            selectedMembers.forEach { member ->
                member.stateChangeCrazy()
            }
            setPostScript(getScript("event_independence_ventilatorOdorToolBox_post_tmp"))
        }
    }
}