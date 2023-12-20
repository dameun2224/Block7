package com.cookandroid.block7

import kotlin.random.Random

// EventHandler : 이벤트 객체 생성, 확률 조정 등
class EventHandler(GameActivity: GameActivity) {
    var GameActivity: GameActivity = GameActivity

    var defaultWeight: Int = 5
    var defaultIsAvailable: Boolean = true

    val typeOX: Int = 1
    val typeItem: Int = 0

    var events = mutableListOf<Event>()

    // 클래스의 생성자 또는 초기화 블록
    init {
        // 강도 침입 이벤트
        val event_independence_invasionRobbery = Event_independence_ox_invasionRobbery(GameActivity, "event_independence_invasionRobbery", typeOX, defaultWeight, defaultIsAvailable)
        // 할머니 침입 이벤트
        val event_independence_invasionGrandma = Event_independence_ox_invasionGrandma(GameActivity, "event_independence_invasionGrandma", typeOX, defaultWeight, defaultIsAvailable)
        // 소방관 침입 이벤트
        val event_independence_invasionFirefighter = Event_independence_ox_invasionFirefighter(GameActivity, "event_independence_invasionFirefighter", typeOX, defaultWeight, defaultIsAvailable)
        // 노인 약탈 이벤트
        val event_independence_plunderOldMan = Event_independence_ox_plunderOldMan(GameActivity, "event_independence_plunderOldMan", typeOX, defaultWeight, defaultIsAvailable)
        // 1호관 동방 약탈 이벤트
        val event_independence_plunderB1cr = Event_independence_ox_plunderB1cr(GameActivity, "event_independence_plunderB1cr", typeOX, defaultWeight, defaultIsAvailable)
        // 자취방 약탈 이벤트
        val event_independence_plunderOtherHome = Event_independence_ox_plunderOtherHome(GameActivity, "event_independence_plunderOtherHome", typeOX, defaultWeight, defaultIsAvailable)
        // 버려진 탱크?
        val event_independence_abandondTankGetItem = Event_independence_ox_abandondTankGetItem(GameActivity, "event_independence_abandondTankGetItem", typeOX, defaultWeight, defaultIsAvailable)
        val event_independence_abandondTankDie = Event_independence_ox_abandondTankDie(GameActivity, "event_independence_abandondTankDie", typeOX, defaultWeight, defaultIsAvailable)
        val event_independence_goraniHouseGetKimbap = Event_independence_ox_goraniHouseGetKimbap(GameActivity, "event_independence_goraniHouseGetKimbap", typeOX, defaultWeight, defaultIsAvailable)
        val event_independence_goraniHouseDie = Event_independence_ox_goraniHouseDie(GameActivity, "event_independence_goraniHouseDie", typeOX, defaultWeight, defaultIsAvailable)
        val event_independence_fightDameunEunju = Event_independence_ox_fightDameunEunju(GameActivity, "event_independence_fightDameunEunju", typeOX, defaultWeight, defaultIsAvailable)
        val event_independence_fightEunjuHyundong = Event_independence_ox_fightEunjuHyundong(GameActivity, "event_independence_fightEunjuHyundong", typeOX, defaultWeight, defaultIsAvailable)
        val event_independence_fightSounDameun = Event_independence_ox_fightSounDameun(GameActivity, "event_independence_fightSounDameun", typeOX, defaultWeight, defaultIsAvailable)
        val event_independence_fightSounEunju = Event_independence_ox_fightSounEunju(GameActivity, "event_independence_fightSounEunju", typeOX, defaultWeight, defaultIsAvailable)
        val event_independence_insomnia = Event_independence_item_insomnia(GameActivity, "event_independence_insomnia", typeItem, defaultWeight, defaultIsAvailable)
        val event_independence_weWantToBathe = Event_independence_item_weWantToBathe(GameActivity, "event_independence_weWantToBathe", typeItem, defaultWeight, defaultIsAvailable)
        val event_independence_ventilatorOdorFlashlight = Event_independence_ox_ventilatorOdorFlashlight(GameActivity, "event_independence_ventilatorOdorFlashlight", typeOX, defaultWeight, defaultIsAvailable)
        val event_independence_ventilatorOdorToolBox = Event_independence_item_ventilatorOdorToolBox(GameActivity, "event_independence_ventilatorOdorToolBox", typeItem, defaultWeight, defaultIsAvailable)
        val event_independence_wallOdor = Event_independence_ox_wallOdor(GameActivity, "event_independence_wallOdor", typeOX, defaultWeight, defaultIsAvailable)
        val event_independence_nextRoomOdor = Event_independence_nextRoomOdor(GameActivity, "event_independence_nextRoomOdor", typeOX, defaultWeight, defaultIsAvailable)
        val event_independence_hiddenSpace = Event_independence_ox_hiddenSpace(GameActivity, "event_independence_hiddenSpace", typeOX, defaultWeight, defaultIsAvailable)
        val event_independence_moreInformationNeeded = Event_independence_moreInformationNeeded(GameActivity, "event_independence_moreInformationNeeded", typeOX, defaultWeight, defaultIsAvailable)
        val event_independence_phoneManipulation = Event_independence_ox_phoneManipulation(GameActivity, "event_independence_phoneManipulation", typeOX, defaultWeight, defaultIsAvailable)
        val event_independence_earthquake = Event_independence_ox_earthquake(GameActivity, "event_independence_earthquake", typeOX, defaultWeight, defaultIsAvailable)
        val event_independence_flood = Event_independence_item_flood(GameActivity, "event_independence_flood", typeItem, defaultWeight, defaultIsAvailable)
        val event_independence_fire = Event_independence_item_fire(GameActivity, "event_independence_fire", typeItem, defaultWeight, defaultIsAvailable)
        val event_independence_spiderWorld = Event_independence_item_spiderWorld(GameActivity, "event_independence_spiderWorld", typeItem, defaultWeight, defaultIsAvailable)
        val event_independence_cockroachKingdom = Event_independence_item_cockroachKingdom(GameActivity, "event_independence_cockroachKingdom", typeItem, defaultWeight, defaultIsAvailable)
        val event_independence_squeakSqueak = Event_independence_item_squeakSqueak(GameActivity, "event_independence_squeakSqueak", typeItem, defaultWeight, defaultIsAvailable)
        val event_independence_colorfulMushrooms = Event_independence_ox_colorfulMushrooms(GameActivity, "event_independence_colorfulMushrooms", typeOX, defaultWeight, defaultIsAvailable)
        val event_independence_stonePeddlerHomeless = Event_independence_ox_stonePeddlerHomeless(GameActivity, "event_independence_stonePeddlerHomeless", typeOX, defaultWeight, defaultIsAvailable)
        val event_independence_askWaterFromMedStudents = Event_independence_ox_askWaterFromMedStudents(GameActivity, "event_independence_askWaterFromMedStudents", typeOX, defaultWeight, defaultIsAvailable)
        val event_independence_honorForBeggars = Event_independence_ox_honorForBeggars(GameActivity, "event_independence_honorForBeggars", typeOX, defaultWeight, defaultIsAvailable)
        val event_independence_blueGuy = Event_independence_ox_blueGuy(GameActivity, "event_independence_blueGuy", typeOX, defaultWeight, defaultIsAvailable)
        val event_independence_poorSurvivorNormal = Event_independence_ox_poorSurvivorNormal(GameActivity, "event_independence_poorSurvivorNormal", typeOX, defaultWeight, defaultIsAvailable)
        val event_independence_poorSurvivorStrong = Event_independence_ox_poorSurvivorStrong(GameActivity, "event_independence_poorSurvivorStrong", typeOX, defaultWeight, defaultIsAvailable)
        val event_independence_povertyStrickenAttack = Event_independence_item_povertyStrickenAttack(GameActivity, "event_independence_povertyStrickenAttack", typeItem, defaultWeight, defaultIsAvailable)
        val event_independence_mysteriousKindPerson = Event_independence_mysteriousKindPerson(GameActivity, "event_independence_mysteriousKindPerson", typeOX, defaultWeight, defaultIsAvailable)
        val event_independence_bellRun = Event_independence_ox_bellRun(GameActivity, "event_independence_bellRun", typeOX, defaultWeight, defaultIsAvailable)

        events.add(event_independence_invasionRobbery)
        events.add(event_independence_invasionGrandma)
        events.add(event_independence_invasionFirefighter)
        events.add(event_independence_plunderOldMan)
        events.add(event_independence_plunderB1cr)
        events.add(event_independence_plunderOtherHome)
        events.add(event_independence_abandondTankGetItem)
        events.add(event_independence_abandondTankDie)
        events.add(event_independence_goraniHouseGetKimbap)
        events.add(event_independence_goraniHouseDie)
        events.add(event_independence_fightDameunEunju)
        events.add(event_independence_fightEunjuHyundong)
        events.add(event_independence_fightSounDameun)
        events.add(event_independence_fightSounEunju)
        events.add(event_independence_insomnia)
        events.add(event_independence_weWantToBathe)
        events.add(event_independence_ventilatorOdorFlashlight)
        events.add(event_independence_ventilatorOdorToolBox)
        events.add(event_independence_wallOdor)
        events.add(event_independence_nextRoomOdor)
        events.add(event_independence_hiddenSpace)
        events.add(event_independence_moreInformationNeeded)
        events.add(event_independence_phoneManipulation)
        events.add(event_independence_earthquake)
        events.add(event_independence_flood)
        events.add(event_independence_fire)
        events.add(event_independence_spiderWorld)
        events.add(event_independence_cockroachKingdom)
        events.add(event_independence_squeakSqueak)
        events.add(event_independence_colorfulMushrooms)
        events.add(event_independence_stonePeddlerHomeless)
        events.add(event_independence_askWaterFromMedStudents)
        events.add(event_independence_honorForBeggars)
        events.add(event_independence_blueGuy)
        events.add(event_independence_poorSurvivorNormal)
        events.add(event_independence_poorSurvivorStrong)
        events.add(event_independence_povertyStrickenAttack)
        events.add(event_independence_mysteriousKindPerson)
        events.add(event_independence_bellRun)
    }

    // 탐험 시 발생할 수 있는 이벤트
    var events_exploring = mutableListOf<Event>()

    /* 랜덤으로 이벤트 선택 및 실행 */

    // 이벤트 실행
    fun runSelectedEvent(selectedEvent: Event, choose_value: Boolean) {
        selectedEvent.executeEventEffect(choose_value)
    }
    // 랜덤 이벤트 선택
    fun selectRandomEvent(): Event {
        // do-while 루프를 통해 선택된 이벤트 객체가 실행 불가능한 경우 다시 뽑도록 함
        // isAvailable가 true인 이벤트 객체가 뽑힐 때까지 실행
        var selectedEvent: Event

        do {
            selectedEvent = getRandomEvent()
        } while (!selectedEvent.isAvailable)

        return  selectedEvent
    }

    // 가중치를 기반으로 랜덤으로 이벤트 선택
    fun getRandomEvent(): Event {
        val totalWeight = events.sumBy { it.weight }

        var randomValue = Random.nextInt(totalWeight)
        var selectedEvent: Event = events.first()

        for (event in events) {
            if (randomValue < event.weight) {
                selectedEvent = event
                break
            }
            randomValue -= event.weight
        }

        return selectedEvent
    }

    /* events_scripts.txt에서 읽어오는 코드 */
    private val eventScripts: Map<String, String> = readEventScriptsFromResources(R.raw.event_scripts)

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

    fun getScript(eventKey: String): String {
        return eventScripts[eventKey] ?: ""
    }
}