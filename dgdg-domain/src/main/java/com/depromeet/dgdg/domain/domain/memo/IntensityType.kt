package com.depromeet.dgdg.domain.domain.memo

// TODO 기획상 정해지면 카테고리 추가
enum class IntensityType(
    val description: String,
    val order: Int
) {

    ROUGHLY("한듯 안한듯", 10),
    TO_THE_LIMIT("한계까지", 20),
    ;

}
