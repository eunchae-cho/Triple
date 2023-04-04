package com.triple.demo.common.enums

enum class EventType(
    private val key: String,
    private val value: String
): EnumModel {
    REVIEW("REVIEW", "리뷰");

    override fun getKey(): String {
        return key
    }
    override fun getValue(): String {
        return value
    }
}