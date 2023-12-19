package com.cookandroid.block7

class Event_independence_item_povertyStrickenAttack (GameActivity: GameActivity, eventName: String, type: Int, weight: Int, isAvailable: Boolean)
    : Event(GameActivity, eventName, type, weight, isAvailable) {

    // 생성자 - 기본 preScript, postScript 설정
    init {
        setPreScript(getScript("event_independence_povertyStrickenAttack_pre"))
        setPostScript(getScript("event_independence_povertyStrickenAttack_post"))
        selectionItem = "axe"
    }

    override fun setIsAvailable() {
        isAvailable = true
    }

    // 이벤트 효과 메소드
    override fun eventEffect(choose_value: Boolean) {
        if(choose_value) {
        } else {
            var size = GameActivity.itemListOwned.size
            size = size / 2
            val selectedItems = GameActivity.itemListOwned.shuffled().take(size)
            selectedItems.forEach { item ->
                item.loseItem()
            }
            setPostScript(getScript("event_independence_povertyStrickenAttack_post_tmp"))
        }
    }
}