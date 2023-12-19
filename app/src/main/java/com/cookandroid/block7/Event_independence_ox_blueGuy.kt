package com.cookandroid.block7

class Event_independence_ox_blueGuy (GameActivity: GameActivity, eventName: String, type: Int, weight: Int, isAvailable: Boolean)
    : Event(GameActivity, eventName, type, weight, isAvailable) {

    // 생성자 - 기본 preScript, postScript 설정
    init {
        setPreScript(getScript("event_independence_blueGuy_pre"))
        setPostScript(getScript("event_independence_blueGuy_post"))
    }

    override fun setIsAvailable() {
        if(GameActivity.food_water.count > 2 && !GameActivity.itemListNotOwned.isEmpty()) isAvailable = true
        else isAvailable = false
    }

    // 이벤트 효과 메소드
    override fun eventEffect(choose_value: Boolean) {
        if(choose_value) {
            GameActivity.food_water.loseFood(1)
            val selectedItems = GameActivity.itemListNotOwned.shuffled().take(1)
            selectedItems.forEach { item ->
                item.getItem()
            }
        } else {
            setPostScript(getScript("event_independence_blueGuy_post_tmp"))
        }
    }
}