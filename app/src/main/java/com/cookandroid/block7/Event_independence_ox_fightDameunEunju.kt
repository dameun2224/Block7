package com.cookandroid.block7
//Event_independence_fightDameunEunju: 은주 담은 싸움
class Event_independence_ox_fightDameunEunju(GameActivity: GameActivity, eventName: String, type: Int, weight: Int, isAvailable: Boolean)
    : Event(GameActivity, eventName, type, weight, isAvailable) {

    // 생성자 - 기본 preScript, postScript 설정
    init {
        setPreScript(getScript("event_independence_fightDameunEunju_pre"))
        setPostScript(getScript("event_independence_fightDameunEunju_post"))
    }

    // isAvailable, weight 수정 메소드
    override fun setIsAvailable() {
        if(GameActivity.member_dameun.getIsIn() && GameActivity.member_eunju.getIsIn()){
            isAvailable = true
        }
        else{
            isAvailable = false
        }
    }

    // 이벤트 효과 메소드
    override fun eventEffect(choose_value: Boolean) {
        if(choose_value) { // 담은 고를경우
            if(GameActivity.item_pesticide !in GameActivity.itemListOwned){
                GameActivity.item_pesticide.getItem()
                addPostScript("그러던중 담은은 우연히 냉장고 뒤에 있던 살충제를 발견했다. 담은은 이것도 외계인의 힘이라며 외계인의 강력함에 대한 연설을 이어갔다.")
            }
        }
        else { // 은주를 고를경우
            setPostScript(getScript("event_independence_fightDameunEunju_tmp"))
            addPostScript("그러던 중 우연히 냉장고 가장 위칸에 가려져 있던 김밥을 발견했다. 유통기한이 조금 지났지만 먹고 살려면 어쩔 수 없는 거다.")
            getRandomKimbap(1, 3)
        }
    }
}