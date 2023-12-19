package com.cookandroid.block7
//Event_independence_fightDameunEunju: 소운 담은 싸움
class Event_independence_ox_fightSounDameun(GameActivity: GameActivity, eventName: String, type: Int, weight: Int, isAvailable: Boolean)
    : Event(GameActivity, eventName, type, weight, isAvailable) {

    // 생성자 - 기본 preScript, postScript 설정
    init {
        setPreScript(getScript("event_independence_fightSounDameun_pre"))
        setPostScript(getScript("event_independence_fightSounDameun_post"))
    }

    // isAvailable, weight 수정 메소드
    override fun setIsAvailable() {
        if(GameActivity.member_dameun.getIsIn() && GameActivity.member_soun.getIsIn()){
            isAvailable = true
        }
        else{
            isAvailable = false
        }
    }

    // 이벤트 효과 메소드
    override fun eventEffect(choose_value: Boolean) {
        if(choose_value) { // 담은 고를경우
            if(GameActivity.item_rattle !in GameActivity.itemListOwned){
                GameActivity.item_rattle.getItem()
                addPostScript("그러자 어째서인지 책상 밑에 딸랑이가 테이프로 붙여져 있었다. 소름이 돋은 우리는 금세 다시 조용한 분위기를 되찾았다. 누가 어째서 이런 짓을 해 놓은 것일까…?")
            }
        }
        else { // 소운 고를경우
            setPostScript(getScript("event_independence_fightSounDameun_tmp"))
        }
    }
}