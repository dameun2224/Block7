package com.cookandroid.block7

class Event_independence_ox_honorForBeggars (GameActivity: GameActivity, eventName: String, type: Int, weight: Int, isAvailable: Boolean)
    : Event(GameActivity, eventName, type, weight, isAvailable) {

    // 생성자 - 기본 preScript, postScript 설정
    init {
        setPreScript(getScript("event_independence_honorForBeggars_pre"))
        setPostScript(getScript("event_independence_honorForBeggars_post"))
    }

    override fun setIsAvailable() {
        isAvailable = true
    }

    // 이벤트 효과 메소드
    override fun eventEffect(choose_value: Boolean) {
        if(choose_value) {
            var current_have_item_size = GameActivity.itemListOwned.size
            if(current_have_item_size>=7){
                val selectedItems = GameActivity.itemListOwned.shuffled().take(3)
                selectedItems.forEach { item ->
                    item.loseItem()
                }
                addPostScript("아무래도 우리는 그들이 생각하기에 너무나도 많은 물품을 가지고 있었던 것 같다.")
            }
            else{
                val selectedItems = GameActivity.itemListNotOwned.shuffled().take(3)
                selectedItems.forEach { item ->
                    item.getItem()
                }
                addPostScript("다행히 우리는 그들이 생각하기에 한없이 가난했던 것 같다.")
            }
        } else {
            setPostScript(getScript("event_independence_honorForBeggars_post_tmp"))
        }
    }
}