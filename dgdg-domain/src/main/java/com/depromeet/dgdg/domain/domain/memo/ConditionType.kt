package com.depromeet.dgdg.domain.domain.memo

// TODO 기획상 정해지면 카테고리 추가
enum class ConditionType(
    val description: String,
    val order: Int
) {

    WORST("매우 나쁨", 10),
    BEST("매우 좋음" ,70)

}
