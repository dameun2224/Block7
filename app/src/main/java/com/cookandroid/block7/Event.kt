package com.cookandroid.block7

import kotlin.random.Random

// Event: MainGame, 이벤트 이름, 호출 가능 여부, 가중치
open class Event(GameActivity: GameActivity, eventName: String,  type: Int, weight: Int, isAvailable: Boolean) {
    // 게임 액티비티
    var GameActivity: GameActivity = GameActivity
    // 이벤트 이름
    var eventName: String = eventName
    // 가중치
    var weight: Int = weight
    // 호출 가능 여부
    var isAvailable: Boolean = isAvailable

    var triger: Boolean = false
    var type: Int = type
    var selectionItem: String = ""

    private val eventScripts: Map<String, String> = readEventScriptsFromResources(R.raw.event_scripts)

    // 스크립트
    private var preScript: String = ""
    private var postScript: String = ""

    init {
        setIsAvailable()
    }

    // isAvailable 및 weight 수정 메소드
    open fun setIsAvailable() {

    }

    // 이벤트 효과 메소드
    open fun eventEffect(choose_value: Boolean) { }

    /* 이벤트 실행 메소드 */
    fun executeEventEffect(choose_value: Boolean) {
        // 아이템, 식량, 멤버
        val itemListTmp = GameActivity.itemList.map { it.copy() }
        val itemListOwnedTmp = GameActivity.itemListOwned.map { it.copy() }
        val itemListNotOwnedTmp = GameActivity.itemListNotOwned.map { it.copy() }
        val itemListBrokenTmp = GameActivity.itemListBroken.map { it.copy() }
        val kimbabTmp = Food(GameActivity.food_kimbap)
        val waterTmp = Food(GameActivity.food_water)
        val memberListTmp = GameActivity.memberList.map { it.copy() }

        /* 이벤트 실행 */
        eventEffect(choose_value)
        triger = true

        /* postScript 수정 */
        // 아이템
        for(itemTmp in itemListTmp) {
            val item = GameActivity.itemList.find { it.nameItem == itemTmp.nameItem }
            if(item?.getIsOwned() == true && !itemTmp.getIsOwned()){ addPostScript(item?.nameKorItem + "을(를) 얻었다.") }
            if(item?.getIsOwned() == false && itemTmp.getIsOwned()) { addPostScript(item?.nameKorItem + "을(를) 잃었다.") }
            if(item?.getIsBroken() == true && !itemTmp.getIsBroken()) { addPostScript(item?.nameKorItem + "이(가) 고장났다.") }

        }

        // 식량
        val numKimbab = GameActivity.food_kimbap.count - kimbabTmp.count
        val numWater = GameActivity.food_water.count - waterTmp.count
        if(numKimbab > 0) { addPostScript("김밥을 " + numKimbab +" 개 얻었다.") }
        else if(numKimbab < 0) { addPostScript("김밥을 " + Math.abs(numKimbab) +" 개 잃었다.") }
        if(numWater > 0) { addPostScript("물을 " + numWater +" 개 얻었다.") }
        else if(numWater < 0) {  addPostScript("물을 " + Math.abs(numWater) +" 개 잃었다.") }

        // 멤버
        for(memberTmp in memberListTmp) {
            val member = GameActivity.memberList.find { it.name == memberTmp.name }
            if(memberTmp.getStateIsCrazy() != member?.getStateIsCrazy()) {
                // isVeryCarzy -> isCrazy로 바뀐 경우, 여기에 걸림. "멤버이(가) 미쳤습니다" 표기
                if(member?.getStateIsCrazy() == true && member?.getStateIsVeryCrazy() == false) addPostScript(member?.nameKor + "이(가) 미쳤습니다.")
                if(member?.getStateIsCrazy() == false && member?.getStateIsVeryCrazy() == false) addPostScript(member?.nameKor + "이(가) 정상으로 돌아왔습니다.")
            }
            if(memberTmp.getStateIsVeryCrazy() != member?.getStateIsVeryCrazy()) {
                if(member?.getStateIsVeryCrazy() == true) addPostScript(member?.nameKor + "이(가) 착란 상태 입니다.")
                if(member?.getStateIsVeryCrazy() == false && member?.getStateIsCrazy() == false) addPostScript(member?.nameKor + "이(가) 정상으로 돌아왔습니다.")
            }
            if(memberTmp.getStateIsHurt() != member?.getStateIsHurt()) {
                if(member?.getStateIsHurt() == true && member?.getStateIsVeryHurt() == false) addPostScript(member?.nameKor + "이(가) 다쳤습니다.")
                if(member?.getStateIsHurt() == false && member?.getStateIsVeryHurt() == false) addPostScript(member?.nameKor + "이(가) 정상으로 돌아왔습니다.")
            }
            if(memberTmp.getStateIsVeryHurt() != member?.getStateIsVeryHurt()) {
                if(member?.getStateIsVeryHurt() == true)  addPostScript(member?.nameKor + "이(가) 고통받습니다.")
                if(member?.getStateIsVeryHurt() == false && member?.getStateIsHurt() == false) addPostScript(member?.nameKor + "이(가) 정상으로 돌아왔습니다.")
            }
            if(memberTmp.getStateIsSick() != member?.getStateIsSick()) {
                if(member?.getStateIsSick() == true && member?.getStateIsVerySick() == false) addPostScript(member?.nameKor + "이(가) 질병에 걸렸습니다.")
                if(member?.getStateIsSick() == false && member?.getStateIsVerySick() == false) addPostScript(member?.nameKor + "이(가) 정상으로 돌아왔습니다.")
            }
            if(memberTmp.getStateIsVerySick() != member?.getStateIsVerySick()) {
                if(member?.getStateIsVerySick() == true) addPostScript(member?.nameKor + "이(가) 병에 걸렸습니다.")
                if(member?.getStateIsSick() == false && member?.getStateIsVerySick() == false) addPostScript(member?.nameKor + "이(가) 정상으로 돌아왔습니다.")
            }
            if(memberTmp.getStateIsTired() != member?.getStateIsTired()) {
                if(member?.getStateIsTired() == true && member?.getStateIsFatigued() == false) addPostScript(member?.nameKor + "은(는) 피곤합니다.")
                if(member?.getStateIsTired() == false && member?.getStateIsFatigued() == false) addPostScript(member?.nameKor + "이(가) 정상으로 돌아왔습니다.")
            }
            if(memberTmp.getStateIsFatigued() != member?.getStateIsFatigued()) {
                if(member?.getStateIsFatigued() == true) addPostScript(member?.nameKor + "이(가) 지쳤습니다.")
                if(member?.getStateIsTired() == false && member?.getStateIsFatigued() == false) addPostScript(member?.nameKor + "이(가) 정상으로 돌아왔습니다.")
            }
        }

        // 스크립트 추가 부분 구현
    }

    /* 이벤트 메소드 */
    // 모두를 미침 상태로
    fun CrazyLevelAllMember() {
        for(member in GameActivity.memberList) {
            if(member.getStateIsCrazy() || member.getStateIsVeryCrazy()) {
                if(member.getIsIn() && member.getIsAlive()) {
                    member.stateChangeCrazy()
                }
            }
        }
    }

    // 모두를 미침, 착란 -> 정상
    fun notCrazyLevelAllMember() {
        for(member in GameActivity.memberList) {
            if(member.getIsIn() && member.getIsAlive()) {
                member.stateChangeNotCrazy()
                member.stateChangeNotVeryCrazy()
            }
        }
    }

    /* 랜덤 이벤트 메소드 */
    fun generateRandomBoolean(cnt: Int): Boolean {
        if(cnt < 0) return false
        val randomNumber = Random.nextInt(cnt)
        return randomNumber == 0
    }
    //랜덤으로 가지고 있지 않은 아이템 중 하나를 얻음
    fun getRandomItem(){
        var selectedItem: Item? = GameActivity.getRandomItemitemListNotOwned()
        if(selectedItem != null) selectedItem.getItem()
    }
    // 랜덤으로 가지고 있는 아이템 중 하나를 잃음
    fun loseRandomItem() {
        var selectedItem: Item? = GameActivity.getRandomItemitemListOwned()
        if(selectedItem != null) selectedItem.loseItem()
    }
    fun getRandomKimbap(start: Int, end: Int){
        GameActivity.food_kimbap.getFoodRandom(start, end)
    }
    fun getRandomWater(start: Int, end: Int){
        GameActivity.food_kimbap.getFoodRandom(start, end)
    }
    fun loseRandomKimbap(start: Int, end: Int){
        GameActivity.food_kimbap.loseFoodRandom(start, end)
    }
    fun loseRandomWater(start: Int, end: Int){
        GameActivity.food_kimbap.loseFoodRandom(start, end)
    }

    // 랜덤 멤버 상태 변화
    fun crazyRandomMember() {
        val selectdeMember: Member = GameActivity.getRandomMemberFromListIsIn()
        selectdeMember.stateChangeCrazy()
    }
    fun veryCrazyRandomMember() {
        val selectdeMember: Member = GameActivity.getRandomMemberFromListIsIn()
        selectdeMember.stateChangeVeryCrazy()
    }
    fun hurtRandomMember() {
        val selectdeMember: Member = GameActivity.getRandomMemberFromListIsIn()
        selectdeMember.stateChangeHurt()
    }
    fun veryHurtRandomMember() {
        val selectdeMember: Member = GameActivity.getRandomMemberFromListIsIn()
        selectdeMember.stateChangeVeryHurt()
    }
    fun sickRandomMember() {
        val selectdeMember: Member = GameActivity.getRandomMemberFromListIsIn()
        selectdeMember.stateChangeSick()
    }
    fun verySickRandomMember() {
        val selectdeMember: Member = GameActivity.getRandomMemberFromListIsIn()
        selectdeMember.stateChangeVerySick()
    }
    fun tiredRandomMember() {
        val selectdeMember: Member = GameActivity.getRandomMemberFromListIsIn()
        selectdeMember.stateChangeTired()
    }
    fun fatiguedRandomMember() {
        val selectdeMember: Member = GameActivity.getRandomMemberFromListIsIn()
        selectdeMember.stateChangeFatigued()
    }

    // 스크립트 반환 메소드
    fun getPreScript(): String { return preScript }
    fun getPostScript(): String { return postScript }

    // 스크립트 설정 메소드
    fun setPreScript(str: String) { preScript = str }
    fun setPostScript(str: String) { postScript = str }
    //따로 할말이 있을때 PostScript에 추가
    fun addPostScript(str: String){ postScript += '\n' + str }

    fun getScript(eventKey: String): String {
        return eventScripts[eventKey] ?: ""
    }

    private fun readEventScriptsFromResources(resourceId: Int): Map<String, String> {
        return try {
            val scriptText = GameActivity.resources.openRawResource(resourceId).bufferedReader().use { it.readText() }
            parseEventScripts(scriptText)
        } catch (e: Exception) {
            mapOf()
        }
    }

    private fun parseEventScripts(scriptText: String): Map<String, String> {
        val scripts = mutableMapOf<String, String>()
        val lines = scriptText.split("\n")

        var currentEventKey: String? = null
        var currentEventScript: StringBuilder? = null

        for (line in lines) {
            if (line.startsWith("event_")) {
                currentEventKey?.let { key ->
                    currentEventScript?.let { script ->
                        scripts[key] = script.toString()
                    }
                }
                currentEventKey = line.trim()
                currentEventScript = StringBuilder()
            } else {
                currentEventScript?.append(line)?.append("\n")
            }
        }

        currentEventKey?.let { key ->
            currentEventScript?.let { script ->
                scripts[key] = script.toString()
            }
        }

        return scripts
    }
}