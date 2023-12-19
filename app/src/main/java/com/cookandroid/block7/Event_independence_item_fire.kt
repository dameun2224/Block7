package com.cookandroid.block7

class Event_independence_item_fire (GameActivity: GameActivity, eventName: String, type: Int, weight: Int, isAvailable: Boolean)
    : Event(GameActivity, eventName, type, weight, isAvailable) {

    // 생성자 - 기본 preScript, postScript 설정
    init {
        setPreScript(getScript("event_independence_fire_pre"))
        setPostScript(getScript("event_independence_fire_post"))
        selectionItem = "pesticide"
    }

    override fun setIsAvailable() {
        if(GameActivity.itemListOwned.isNotEmpty()) isAvailable = true
        else isAvailable = false
    }

    // 이벤트 효과 메소드
    override fun eventEffect(choose_value: Boolean) {
        if(choose_value) {
        } else {
            val selectedItems = GameActivity.itemListOwned.shuffled().take(1)
            selectedItems.forEach { item ->
                item.loseItem()
            }
            setPostScript(getScript("event_independence_fire_post_tmp"))
        }
    }
}