package com.cookandroid.block7

// 노인 약탈 이벤트
class Event_independence_ox_plunderB1cr(GameActivity: GameActivity, eventName: String, type: Int, weight: Int, isAvailable: Boolean)
    : Event(GameActivity, eventName, type, weight, isAvailable) {

    // 생성자 - 기본 preScript, postScript 설정
    init {
        super.setPreScript(getScript("event_plunder_b1cr_pre"))
        super.setPostScript(getScript("event_plunder_b1cr_post"))
    }

    // isAvailable, weight 수정 메소드
    override fun setIsAvailable() {
        isAvailable = true
    }

    // 이벤트 효과 메소드
    override fun eventEffect(choose_value: Boolean) {
        if(choose_value) {
            if(generateRandomBoolean(3)) { // 실패
                hurtRandomMember()
                setPostScript("1호관에 이런 경비 시스템이 있었다고? 우리가 문을 열려고 하자마자 우리는 위에서 떨어지는 벽돌과 밀가루를 피애햐만 했다. 우리는 정신없이 달아나기 바빴다. 끔찍한 1호관, 끔찍한 사람들!")
            } else { // 성공
                var selectedFood = GameActivity.selectRandomFood()
                selectedFood.getFoodRandom(4, 4)
                tiredRandomMember()
                crazyRandomMember()
            }
        } else {
            notCrazyLevelAllMember()
            setPostScript(getScript("event_plunder_b1cr_post_tmp"))
        }
    }
}