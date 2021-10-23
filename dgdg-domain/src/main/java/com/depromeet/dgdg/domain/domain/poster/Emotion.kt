package com.depromeet.dgdg.domain.domain.poster

enum class Emotion(
    val title: String,
    val description: String,
    val backgroundColor: String
) {

    FURIOUS("Furious", "화난", "#ffffff"),
    JITTERY("Jittery", "초조", "#ffffff"),
    CHEERFUL("Cheerful", "쾌활", "#ffffff"),
    PROUD("Proud", "뿌듯", "#ffffff"),
    ANXIOUS("Anxious", "불안", "#ffffff"),
    IRRITATED("Irritated", "짜증", "#ffffff"),
    JOYFUL("Joyful", "기쁜", "#ffffff"),
    THRILIED("Thrilied", "짜릿", "#ffffff"),
    SAD("Sad", "슬플", "#ffffff"),
    BORED("Bored", "지루", "#ffffff"),
    CALM("Calm", "평온", "#ffffff"),
    FULFILLED("Fulfilled", "충만", "#ffffff"),
    DEPRESSED("Depressed", "우울", "#ffffff"),
    FATIGUED("Fatigued", "지친", "#ffffff"),
    SLEEPY("Sleepy", "나른", "#ffffff"),
    COMFORTABLE("Comfortable", "편안", "#ffffff"),
    ;

}
