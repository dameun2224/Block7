package com.cookandroid.block7

import android.view.View
import android.widget.ImageView

class Item(GameActivity: GameActivity, nameItem: String, nameKorItem: String, itemImage: ImageView) {
    // 게임 액티비티
    var GameActivity: GameActivity = GameActivity

    // 아이템 이름
    var nameItem: String = nameItem

    var nameKorItem: String = nameKorItem

    // 아이템의 이미지뷰
    val itemImage: ImageView = itemImage
    lateinit var itemIconDefault: ImageView
    var itmeIconRes: Int = 0

    private var isOwned: Boolean = false
    private var isBroken: Boolean = false

    fun getIsOwned(): Boolean { return isOwned }
    fun getIsBroken(): Boolean { return isBroken }

    init {
        setItemIconDefault()
    }

    // 아이템 획득 메소드
    fun getItem() {
        isOwned = true
        GameActivity.itemListOwned.add(this)
        GameActivity.itemListNotOwned.remove(this)
        updateItemVisibility()
    }

    // 아이템 잃는 메소드
    fun loseItem() {
        isOwned = false
        GameActivity.itemListOwned.remove(this)
        GameActivity.itemListNotOwned.add(this)
        updateItemVisibility()
    }

    // 아이템 고장 메소드
    fun breakItem() {
        isBroken = true
        GameActivity.itemListBroken.add(this)
        updateItemVisibility()
    }

    fun fixItem() {
        isBroken = false
        GameActivity.itemListBroken.remove(this)
        updateItemVisibility()
    }

    // itemImage의 visivility를 업데이트 하는 함수
    fun updateItemVisibility() {
        if(this in GameActivity.itemListOwned) itemImage.visibility = View.VISIBLE
        if(this in GameActivity.itemListBroken) itemImage.visibility = View.VISIBLE
        if(this in GameActivity.itemListNotOwned) itemImage.visibility = View.GONE
        updateItemIconRes()
    }

    // 아이템 아이콘 리소스를 업데이트하는 함수
    fun updateItemIconRes() {
        if(isOwned) {
            itmeIconRes = when (nameItem) {
                "axe" -> R.drawable.icon_axe
                "book" -> R.drawable.icon_books
                "card" -> R.drawable.icon_cards
                "drone" -> R.drawable.icon_drone
                "medkit" -> R.drawable.icon_medkit
                "flashlight" -> R.drawable.icon_flashlight
                "gasmask" -> R.drawable.icon_gasmask
                "lock" -> R.drawable.icon_lock
                "map" -> R.drawable.icon_map
                "pesticide" -> R.drawable.icon_pesticide
                "phone" -> R.drawable.icon_phone
                "rattle" -> R.drawable.icon_rattle
                "toolbox" -> R.drawable.icon_toolbox
                else -> 0
            }
        }
        if(!isOwned) {
            itmeIconRes = when (nameItem) {
                "axe" -> R.drawable.icon_axe_x
                "book" -> R.drawable.icon_books_x
                "card" -> R.drawable.icon_cards_x
                "drone" -> R.drawable.icon_drone_x
                "medkit" -> R.drawable.icon_medkit_x
                "flashlight" -> R.drawable.icon_flashlight_x
                "gasmask" -> R.drawable.icon_gasmask_x
                "lock" -> R.drawable.icon_lock_x
                "map" -> R.drawable.icon_map_x
                "pesticide" -> R.drawable.icon_pesticide_x
                "phone" -> R.drawable.icon_phone_x
                "rattle" -> R.drawable.icon_rattle_x
                "toolbox" -> R.drawable.icon_toolbox_x
                else -> 0
            }
        }
    }

    fun setItemIconDefault() {
        // ImageView를 GameActivity의 context를 사용하여 초기화합니다.
        itemIconDefault = ImageView(GameActivity)
        when (nameItem) {
            "axe" -> itemIconDefault.setImageResource(R.drawable.icon_axe)
            "book" -> itemIconDefault.setImageResource(R.drawable.icon_books)
            "card" -> itemIconDefault.setImageResource(R.drawable.icon_cards)
            "drone" -> itemIconDefault.setImageResource(R.drawable.icon_drone)
            "medkit" -> itemIconDefault.setImageResource(R.drawable.icon_medkit)
            "flashlight" -> itemIconDefault.setImageResource(R.drawable.icon_flashlight)
            "gasmask" -> itemIconDefault.setImageResource(R.drawable.icon_gasmask)
            "lock" -> itemIconDefault.setImageResource(R.drawable.icon_lock)
            "map" -> itemIconDefault.setImageResource(R.drawable.icon_map)
            "pesticide" -> itemIconDefault.setImageResource(R.drawable.icon_pesticide)
            "phone" -> itemIconDefault.setImageResource(R.drawable.icon_phone)
            "rattle" -> itemIconDefault.setImageResource(R.drawable.icon_rattle)
            "toolbox" -> itemIconDefault.setImageResource(R.drawable.icon_toolbox)
            else -> true
        }
    }

    // 깊은 복사
    fun copy(
        GameActivity: GameActivity = this.GameActivity,
        nameItem: String = this.nameItem,
        nameKorItem: String = this.nameKorItem,
        itemImage: ImageView = this.itemImage,
        itmeIconRes: Int = this.itmeIconRes
    ): Item {
        val copiedItem = Item(GameActivity, nameItem, nameKorItem, itemImage)
        copiedItem.itmeIconRes = itmeIconRes
        copiedItem.isOwned = isOwned
        copiedItem.isBroken = isBroken
        return copiedItem
    }
}