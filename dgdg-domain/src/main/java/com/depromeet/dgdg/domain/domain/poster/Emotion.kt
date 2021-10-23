package com.depromeet.dgdg.domain.domain.poster

enum class Emotion(
    val title: String,
    val description: String,
    val startColor: String,
    val endColor: String
) {

    FURIOUS("Furious", "화난", "#ffffff", "#ffffff"),
    JITTERY("Jittery", "초조", "#ffffff", "#ffffff"),
    CHEERFUL("Cheerful", "쾌활", "#ffffff", "#ffffff"),
    PROUD("Proud", "뿌듯", "#ffffff", "#ffffff"),
    ANXIOUS("Anxious", "불안", "#ffffff", "#ffffff"),
    IRRITATED("Irritated", "짜증", "#ffffff", "#ffffff"),
    JOYFUL("Joyful", "기쁜", "#ffffff", "#ffffff"),
    THRILIED("Thrilied", "짜릿", "#ffffff", "#ffffff"),
    SAD("Sad", "슬플", "#ffffff", "#ffffff"),
    BORED("Bored", "지루", "#ffffff", "#ffffff"),
    CALM("Calm", "평온", "#ffffff", "#ffffff"),
    FULFILLED("Fulfilled", "충만", "#ffffff", "#ffffff"),
    DEPRESSED("Depressed", "우울", "#ffffff", "#ffffff"),
    FATIGUED("Fatigued", "지친", "#ffffff", "#ffffff"),
    SLEEPY("Sleepy", "나른", "#ffffff", "#ffffff"),
    COMFORTABLE("Comfortable", "편안", "#ffffff", "#ffffff"),
    ;

}
