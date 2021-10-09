package com.depromeet.dgdg.domain.domain.tag

enum class TagType(
    val category: TagCategory,
    val description: String
) {

    EXERCISE_AEROBIC(TagCategory.EXERCISE, "유산소"),
    EXERCISE_MUSCULAR(TagCategory.EXERCISE, "근력"),

    FEELING_GOOD(TagCategory.FEELING, "기쁨"),
    FEELING_BAD(TagCategory.FEELING, "행복"),
    ;

    enum class TagCategory(
        val description: String
    ) {
        EXERCISE("운동"),
        FEELING("감정"),
        ;
    }

}
