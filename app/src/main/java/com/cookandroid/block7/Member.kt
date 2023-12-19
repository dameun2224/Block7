package com.cookandroid.block7

import android.view.View
import android.widget.ImageView

class Member(GameActivity: GameActivity, name: String, nameKor: String, memberView: ImageView) {
    // 게임 엑티비티
    val GameActivity: GameActivity = GameActivity

    // 멤버 정보
    var name: String = name
    var nameKor: String = nameKor
    var memberView: ImageView = memberView

    var memberScript: String = ""

    // 살아있는지
    private var isAlive : Boolean = true

    // level : 배고픔, 목마름, 멘탈
    private var levelHunger: Int = 100
    private var levelThirst: Int = 100
    private var levelMental: Int = 100

    // state : 게임에 나타날 String
    private var stateHunger : String = ""
    private var stateThirst : String = ""
    private var stateMental: String = ""
    private var stateHurt: String =""
    private var stateSick: String = ""
    private var stateTired: String = ""
    private var stateFatigued: String = ""

    // 상태 변화
    private var isCrazy: Boolean = false
    private var isVeryCrazy: Boolean = false
    private var isHurt: Boolean = false
    private var isVeryHurt: Boolean = false
    private var isSick: Boolean = false
    private var isVerySick: Boolean = false
    private var isTired: Boolean = false
    private var isFatigued: Boolean = false

    private var dateCrazy: Int = 0
    private var dateVeryCrazy: Int = 0
    private var dateHurt: Int = 0
    private var dateVeryHurt: Int = 0
    private var dateSick: Int = 0
    private var dateVerySick: Int = 0
    private var dateTired: Int = 0
    private var dateFatigued: Int = 0

    // 상태변화 - 특수
    private var isAlien: Boolean = false
    private var isBrainwashing: Boolean = false

    // 탐험 관련?
    private var isIn : Boolean = true // 안에 있는가?
    private var isExploring = false // 탐험중인가?
    private var dateExplore: Int = 0

    init {
        if(nameKor == "담은" || nameKor == "현동") levelHunger = 100
        if(nameKor == "은주" || nameKor == "소운") levelHunger = 90
    }


    /* 멤버 메소드 */

    // 하루가 지날때마다 항상 호출되는 메소드. 굶주림 레벨, 목마름 레벨 감소
    fun dayPase() {
        levelHunger = maxOf(0, levelHunger - 10)
        levelThirst = maxOf(0, levelThirst - 15)

        if(levelHunger <= 0) die()
        if(levelThirst <= 0) die()

        /* 나머지 부분 구현 */

        updateMember()
    }

    // 멤버 상태 업데이트 메소드
    fun updateMember() {
        updateState() // 상태 업데이트
        updateVisibility() // 이미지 업데이트
        updateDate() // 날짜 업데이트
        updateScript() // 스크립트 업데이트
    }

    // 먹는 메소드
    fun eatKimbab() {
        if(nameKor == "담은" || nameKor == "현동") levelHunger = minOf(100, levelHunger + 30)
        if(nameKor == "은주" || nameKor == "소운") levelHunger = minOf(90, levelHunger + 30)
    }
    fun drinkWater() { levelThirst = 100 }

    // 응급상자 사용
    fun useMedkit() {
        stateChangeNotHurt()
        stateChangeNotVeryHurt()
        stateChangeNotSick()
        stateChangeNotVerySick()
        memberScript += nameKor+"은(는) 더이상 아프지 않다.\n"
    }

    // 탐험 - 갈 때 메소드
    fun goExploring() {
        if(!isAlive) return; // 살아있지 않다면 return
        isExploring = true
        isIn = false
        GameActivity.memberListIsIn.remove(this)
    }

    // 탐험 - 돌아올 때 메소드
    fun returnExploring() {
        if(!isAlive) return; // 살아있지 않다면 return
        isIn = true
        GameActivity.memberListIsIn.add(this)
    }

    // 죽음 메소드
    fun die() {
        isExploring = false
        isAlive = false
        isIn = false
        this.memberView.visibility = View.GONE
        GameActivity.memberListIsIn.remove(this)
    }

    // 상태 변화 메소드
    fun stateChangeCrazy() { isCrazy = true }
    fun stateChangeVeryCrazy() { isVeryCrazy = true }
    fun stateChangeHurt() { isHurt = true }
    fun stateChangeVeryHurt() { isVeryHurt = true }
    fun stateChangeSick() { isSick = true }
    fun stateChangeVerySick() { isVerySick = true }
    fun stateChangeTired() { isTired = true }
    fun stateChangeFatigued() { isFatigued = true }

    fun stateChangeNotCrazy() { isCrazy = false}
    fun stateChangeNotVeryCrazy() { isVeryCrazy = false }
    fun stateChangeNotHurt() { isHurt = false}
    fun stateChangeNotVeryHurt() { isVeryHurt = false }
    fun stateChangeNotSick() { isSick = false}
    fun stateChangeNotVerySick() { isVerySick = false }
    fun stateChangeNotTired() { isTired = false }
    fun stateChangeNotFatiqued() { isFatigued = false }

    // 상태 가져오기 - Boolean
    fun getStateIsCrazy(): Boolean { return isCrazy }
    fun getStateIsVeryCrazy(): Boolean { return isVeryCrazy }
    fun getStateIsHurt(): Boolean { return isHurt }
    fun getStateIsVeryHurt(): Boolean { return isVeryHurt }
    fun getStateIsSick(): Boolean { return isSick }
    fun getStateIsVerySick(): Boolean { return isVerySick }
    fun getStateIsTired(): Boolean { return isTired }
    fun getStateIsFatigued(): Boolean { return isFatigued }

    fun eatFull() { levelHunger = 100; stateHunger = ""}
    fun drinkFull() { levelThirst = 100; stateThirst = ""}


    // 상태 가져오기 - String
    fun getMemberState(): String { // 모든 상태 가져오기 - GameActivity에서 호출?
        return getStateHunger() + '\n' + getStateThirst() + '\n' + getStateMental() + '\n' + getStateHurt() + '\n' + getStateSick() + '\n' + getStateTired() + '\n' + getStateFatigued()
    }
    fun getStateHunger(): String { return stateHunger }
    fun getStateThirst(): String { return stateThirst }
    fun getStateMental(): String { return stateMental }
    fun getStateHurt(): String { return stateHurt }
    fun getStateSick(): String { return stateSick }
    fun getStateTired(): String { return stateTired }
    fun getStateFatigued(): String { return stateFatigued }

    fun getIsIn(): Boolean { return isIn }

    fun isNeedMedkit(): Boolean { return isHurt||isVeryHurt||isSick||isVerySick }

    /* update 메소드 */

    // 멤버 Script 업데이트.
    fun updateScript() {
        memberScript = ""
        if (isCrazy) memberScript += nameKor + "은(는) 미쳤다.\n"
        if (isVeryCrazy) memberScript += nameKor + "은(는) 착란한다.\n"
        if (isHurt) memberScript += nameKor + "은(는) 다쳤다.\n"
        if (isVeryHurt) memberScript += nameKor + "은(는) 고통받는다.\n"
        if (isSick) memberScript += nameKor + "은(는) 질병에 걸렸다.\n"
        if (isVerySick) memberScript += nameKor + "은(는) 병에 걸렸다.\n"
        if (isTired) memberScript += nameKor + "은(는) 지쳤다.\n"
        if (isFatigued) memberScript += nameKor + "은(는) 매우 지쳤다.\n"
        if(stateHunger == "배고픔") memberScript += nameKor + "은(는) 배고프다.\n"
        else if(stateHunger == "굶주림") memberScript += nameKor + "은(는) 굶주렸다.\n"
        if(stateThirst == "목마름") memberScript += nameKor + "은(는) 목마르다.\n"
        else if(stateThirst == "탈수") memberScript += nameKor + "은(는) 탈수 상태다.\n"

        // 안에 있지 않거나, 살아있지 않다면
        if (!isIn) memberScript = nameKor + "은(는) 아직 돌아오지 않았다.\n"
        if(!isAlive) memberScript = ""
        else memberScript += "\n"
    }

    // date 업데이트 - 수정 필요, 임의로 작성함
    fun updateDate() {
        if (isCrazy) dateCrazy++
        if (isVeryCrazy) dateVeryCrazy++
        if (isHurt) dateHurt++
        if (isVeryHurt) dateVeryHurt++
        if (isSick) dateSick++
        if (isVerySick) dateVerySick++
        if (isTired) dateTired++
        if (isFatigued) dateFatigued++
        if (!isIn) dateExplore++ // 안에 있지 않다면, dateExplore++
    }

    // 멤버의 이미지 업데이트 메소드
    fun updateVisibility() {
        // 살아있고, 탐험 가지 않았다면 보이게
        if(isAlive && isIn) memberView.visibility = View.VISIBLE
        // 살아있지 않거나, 탐험 갔다면 보이지 않게
        else if(!isAlive || !isIn) memberView.visibility = View.GONE
        // 상태 변화 추가 (미침, 지침, 다침, 외계인 등 ..)
        if(isTired || isFatigued) {
            when (nameKor) {
                "담은" -> memberView.setImageResource(R.drawable.member_dameun_tired)
                "소운" -> memberView.setImageResource(R.drawable.member_soun_tired)
                "현동" -> memberView.setImageResource(R.drawable.member_hyundong_tired)
                "은주" -> memberView.setImageResource(R.drawable.member_eunju_tired)
                else -> true
            }
        }
        if(isSick || isVerySick) {
            when (nameKor) {
                "담은" -> memberView.setImageResource(R.drawable.member_dameun_sick)
                "소운" -> memberView.setImageResource(R.drawable.member_soun_sick)
                "현동" -> memberView.setImageResource(R.drawable.member_hyundong_sick)
                "은주" -> memberView.setImageResource(R.drawable.member_eunju_sick)
                else -> true
            }
        }
        if(isCrazy || isVeryCrazy) {
            when (nameKor) {
                "담은" -> memberView.setImageResource(R.drawable.member_dameun_crazy)
                "소운" -> memberView.setImageResource(R.drawable.member_soun_crazy)
                "현동" -> memberView.setImageResource(R.drawable.member_hyundong_crazy)
                "은주" -> memberView.setImageResource(R.drawable.member_eunju_crazy)
                else -> true
            }
        }
        if(isHurt || isVeryHurt) {
            when (nameKor) {
                "담은" -> memberView.setImageResource(R.drawable.member_dameun_hurt)
                "소운" -> memberView.setImageResource(R.drawable.member_soun_hurt)
                "현동" -> memberView.setImageResource(R.drawable.member_hyundong_hurt)
                "은주" -> memberView.setImageResource(R.drawable.member_eunju_hurt)
                else -> true
            }
        }
    }

    // state(String) 업데이트
    fun updateState() {
        // stateHunger: 100~71 공백, 70~31 배고픔, 30~0 굶주림
        stateHunger = when {
            levelHunger > 70 -> ""
            levelHunger > 30 -> "배고픔"
            else -> "굶주림" }
        // stateThirst: 100~71 공백, 70~26 목마름, 25~0: 탈수
        stateThirst = when {
            levelThirst > 70 -> ""
            levelThirst > 25 -> "목마름"
            else -> "탈수" }
        // stateMental: isCrazy 미침, isVeryCrazy 착란
        stateMental = when {
            isCrazy -> "미침"
            isVeryCrazy -> "착란"
            else -> "" }
        // stateHurt: isHurt 다침, isVeryHurt 고통
        stateHurt = when {
            isHurt -> "다침"
            isVeryHurt -> "고통"
            else -> "" }
        // stateSick: isSick 질병, isVerySick 병
        stateSick = when {
            isSick -> "질병"
            isVerySick -> "병"
            else -> "" }
        // stateTired: isTired 피곤함
        stateTired = when {
            isTired -> "피곤함"
            else -> "" }
        // stateFatigued: isFatigued 지침
        stateFatigued = when {
            isFatigued -> "지침"
            else -> "" }
    }

    fun copy(
        GameActivity: GameActivity = this.GameActivity,
        name: String = this.name,
        nameKor: String = this.nameKor,
        memberView: ImageView = this.memberView,
        memberScript: String = this.memberScript,
        isAlive: Boolean = this.isAlive,
        levelHunger: Int = this.levelHunger,
        levelThirst: Int = this.levelThirst,
        levelMental: Int = this.levelMental,
        stateHunger: String = this.stateHunger,
        stateThirst: String = this.stateThirst,
        stateMental: String = this.stateMental,
        stateHurt: String = this.stateHurt,
        stateSick: String = this.stateSick,
        stateTired: String = this.stateTired,
        stateFatigued: String = this.stateFatigued,
        isCrazy: Boolean = this.isCrazy,
        isVeryCrazy: Boolean = this.isVeryCrazy,
        isHurt: Boolean = this.isHurt,
        isVeryHurt: Boolean = this.isVeryHurt,
        isSick: Boolean = this.isSick,
        isVerySick: Boolean = this.isVerySick,
        isTired: Boolean = this.isTired,
        isFatigued: Boolean = this.isFatigued,
        dateCrazy: Int = this.dateCrazy,
        dateVeryCrazy: Int = this.dateVeryCrazy,
        dateHurt: Int = this.dateHurt,
        dateVeryHurt: Int = this.dateVeryHurt,
        dateSick: Int = this.dateSick,
        dateVerySick: Int = this.dateVerySick,
        dateTired: Int = this.dateTired,
        dateFatigued: Int = this.dateFatigued,
        isAlien: Boolean = this.isAlien,
        isBrainwashing: Boolean = this.isBrainwashing,
        isIn: Boolean = this.isIn,
        isExploring: Boolean = this.isExploring,
        dateExplore: Int = this.dateExplore
    ): Member {
        return Member(
            GameActivity,
            name,
            nameKor,
            memberView
        ).apply {
            this.memberScript = memberScript
            this.isAlive = isAlive
            this.levelHunger = levelHunger
            this.levelThirst = levelThirst
            this.levelMental = levelMental
            this.stateHunger = stateHunger
            this.stateThirst = stateThirst
            this.stateMental = stateMental
            this.stateHurt = stateHurt
            this.stateSick = stateSick
            this.stateTired = stateTired
            this.stateFatigued = stateFatigued
            this.isCrazy = isCrazy
            this.isVeryCrazy = isVeryCrazy
            this.isHurt = isHurt
            this.isVeryHurt = isVeryHurt
            this.isSick = isSick
            this.isVerySick = isVerySick
            this.isTired = isTired
            this.isFatigued = isFatigued
            this.dateCrazy = dateCrazy
            this.dateVeryCrazy = dateVeryCrazy
            this.dateHurt = dateHurt
            this.dateVeryHurt = dateVeryHurt
            this.dateSick = dateSick
            this.dateVerySick = dateVerySick
            this.dateTired = dateTired
            this.dateFatigued = dateFatigued
            this.isAlien = isAlien
            this.isBrainwashing = isBrainwashing
            this.isIn = isIn
            this.isExploring = isExploring
            this.dateExplore = dateExplore
        }
    }

}