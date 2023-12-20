package com.cookandroid.block7

// 노인 약탈 이벤트
class Event_independence_ox_plunderOtherHome(GameActivity: GameActivity, eventName: String, type: Int, weight: Int, isAvailable: Boolean)
    : Event(GameActivity, eventName, type, weight, isAvailable) {

    // 생성자 - 기본 preScript, postScript 설정
    init {
        super.setPreScript(getScript("event_plunder_otherHome_pre"))
        super.setPostScript(getScript("event_plunder_otherHome_post"))
    }

    // isAvailable, weight 수정 메소드
    override fun setIsAvailable() {
        isAvailable = true
    }

    // 이벤트 효과 메소드
    override fun eventEffect(choose_value: Boolean) {
        if(choose_value) { // O 선택
            var selectedFood = GameActivity.selectRandomFood()
            selectedFood.getFoodRandom(4, 4)
            if(generateRandomBoolean(2)) { // 낮은 확률로 전원 미침
                CrazyLevelAllMember()
                addPostScript("그들은 우리와 같은 처지의 학생이었다 해도..")
            } else { // 무작위 한명 미침
                crazyRandomMember()
            }
        } else { // X 선택
            notCrazyLevelAllMember()
            setPostScript(getScript("event_plunder_otherHome_post_tmp"))
        }
    }
}