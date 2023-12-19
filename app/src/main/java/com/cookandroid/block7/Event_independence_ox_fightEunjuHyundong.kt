package com.cookandroid.block7
//Event_independence_fightDameunEunju: 은주 현동 싸움
class Event_independence_ox_fightEunjuHyundong(GameActivity: GameActivity, eventName: String, type: Int, weight: Int, isAvailable: Boolean)
    : Event(GameActivity, eventName, type, weight, isAvailable) {

    // 생성자 - 기본 preScript, postScript 설정
    init {
        setPreScript(getScript("event_independence_fightEunjuHyundong_pre"))
        setPostScript(getScript("event_independence_fightEunjuHyundong_post"))
    }

    // isAvailable, weight 수정 메소드
    override fun setIsAvailable() {
        if(GameActivity.member_eunju.getIsIn() && GameActivity.member_hyundong.getIsIn()){
            isAvailable = true
        }
        else{
            isAvailable = false
        }
    }

    // 이벤트 효과 메소드
    override fun eventEffect(choose_value: Boolean) {
        if(choose_value) { // 은주 고를경우
            val randomValue = Math.random()
            // 90확률로 식량을 잃음
            if (randomValue < 0.9) {
                var selectedFood = GameActivity.selectRandomFood()
                selectedFood.loseFoodRandom(1,2)
            }
        }
        else { // 현동 고를경우
            setPostScript(getScript("event_independence_fightEunjuHyundong_tmp"))
            val randomValue = Math.random()
            // 70확률로 은주 병걸림
            if (randomValue < 0.7) {
                if(!GameActivity.member_eunju.getStateIsSick()){
                    GameActivity.member_eunju.stateChangeSick()
                    addPostScript("놀랍지도 않게 은주는 병에 걸렸다. 어째서 그 물고기를 삼킬 생각을 한걸까?")
                }
            }
        }
    }
}