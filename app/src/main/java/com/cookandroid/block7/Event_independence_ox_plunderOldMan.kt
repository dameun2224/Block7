package com.cookandroid.block7

// 노인 약탈 이벤트
class Event_independence_ox_plunderOldMan(GameActivity: GameActivity, eventName: String, type: Int, weight: Int, isAvailable: Boolean)
    : Event(GameActivity, eventName, type, weight, isAvailable) {

    // 생성자 - 기본 preScript, postScript 설정
    init {
        super.setPreScript(getScript("event_plunder_oldMan_pre"))
        super.setPostScript(getScript("event_plunder_oldMan_post"))
    }

    // isAvailable, weight 수정 메소드
    override fun setIsAvailable() {
        isAvailable = true
    }

    // 이벤트 효과 메소드
    override fun eventEffect(choose_value: Boolean) {
        if(choose_value) { // O 선택
            if(generateRandomBoolean(3)) { // 실패
                hurtRandomMember()
                setPostScript("젠장! 그 늙은 노인들은 전혀 무력하지 않았다. 그들은 우리가 자신들을 공격한다는 것을 눈치채자, 지갑, 지팡이, 보행기로 우리를 때렸다. 우리는 쫓겨날 수 밖에 없었다.")
            } else { // 성공
                getRandomItem()
                var selectedFood = GameActivity.selectRandomFood()
                selectedFood.getFoodRandom(3,4)
                crazyRandomMember()
            }
        } else { // X 선택
            notCrazyLevelAllMember()
            setPostScript(getScript("event_plunder_oldMan_post_tmp"))
        }
    }
}