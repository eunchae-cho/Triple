package com.triple.demo.common.enums

enum class PointType(
    private val type: String,
    private val reason: String,
    private val point: Long
): EnumModel{
    ADD_TEXT("NORMAL","1자 이상 텍스트 작성", 1),
    ADD_PHOTO("NORMAL","1장 이상 사진 첨부", 1),
    BONUS_FIRST_REVIEW("BONUS","특정 장소에 첫 리뷰 작성", 1);

    override fun getKey(): String {
        return type
    }
    override fun getValue(): String {
        return reason
    }
    fun getPoint(): Long {
        return point
    }
}