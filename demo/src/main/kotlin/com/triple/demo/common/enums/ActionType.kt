package com.triple.demo.common.enums

enum class ActionType(
    private val key: String,
    private val value: String
): EnumModel {
    ADD("ADD", "저장"),
    MOD("MOD", "수정"),
    DELETE("DELETE", "삭제");

    override fun getKey(): String {
        return key
    }
    override fun getValue(): String {
        return value
    }
}