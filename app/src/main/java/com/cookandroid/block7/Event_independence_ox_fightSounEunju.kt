package com.cookandroid.block7
//Event_independence_fightSounEunju: 은주 소운 싸움
class Event_independence_ox_fightSounEunju(GameActivity: GameActivity, eventName: String, type: Int, weight: Int, isAvailable: Boolean)
    : Event(GameActivity, eventName, type, weight, isAvailable) {

    // 생성자 - 기본 preScript, postScript 설정
    init {
        setPreScript(getScript("event_independence_fightSounEunju_pre"))
        setPostScript(getScript("event_independence_fightSounEunju_post"))
    }

    // isAvailable, weight 수정 메소드
    override fun setIsAvailable() {
        if(GameActivity.member_soun.getIsIn() && GameActivity.member_eunju.getIsIn()){
            isAvailable = true
        }
        else{
            isAvailable = false
        }
    }

    // 이벤트 효과 메소드
    override fun eventEffect(choose_value: Boolean) {
        if(choose_value) { // 소운이를 고를경우
            val randomValue = Math.random()
            // 50%의 확률로 소운이가다칩니다.
            if (randomValue < 0.5) {
                if(!GameActivity.member_soun.getStateIsHurt()){
                    GameActivity.member_soun.stateChangeHurt()
                    addPostScript("하지만 그 순간 은주가 소운을 향해 날아차기를 시전했다. 워낙 기습적이었기 때문에 소운은 그것을 피하지 못했다.")
                }
            }
        }
        else { // 은주를 고를경우
            setPostScript(getScript("event_independence_fightSounEunju_tmp"))
            val randomValue = Math.random()
            if(randomValue < 0.5){
                if(GameActivity.itemListNotOwned.isNotEmpty()){
                    getRandomItem()
                    addPostScript("날뛰던 소운은 뜻밖에도 책장 뒤에 가려져 있던 물건을 찾아냈다.")
                }
            }
            else{
                if(GameActivity.itemListOwned.isNotEmpty()){
                    loseRandomItem()
                    addPostScript("결국 날뛰던 소운은 물건 하나를 부숴버렸다.")
                }
            }
        }
    }
}