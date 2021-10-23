package com.depromeet.dgdg.domain.domain.poster

enum class Emotion(
    val title: String,
    val description: String,
    val startColor: String,
    val endColor: String
) {

    FURIOUS("Furious", "화난", "#6C0000", "#110202"),
    JITTERY("Jittery", "초조", "#AA2900", "#AA2900"),
    CHEERFUL("Cheerful", "쾌활", "#FFE500", "#FFF970"),
    PROUD("Proud", "뿌듯", "#FFE092", "#FFE092"),
    ANXIOUS("Anxious", "불안", "#082630", "#51094A"),
    IRRITATED("Irritated", "짜증", "#AE1111", "#AA4700"),
    JOYFUL("Joyful", "기쁜", "#FFF615", "#FF7A00"),
    THRILIED("Thrilied", "짜릿", "#FF2876", "#FB2BFF"),
    SAD("Sad", "슬플", "#1B1B1B", "#222757"),
    BORED("Bored", "지루", "#274A26", "#0A2227"),
    CALM("Calm", "평온", "#BDF4CD", "#C1E5A4"),
    FULFILLED("Fulfilled", "충만", "#FFAF8F", "#FF7C7C"),
    DEPRESSED("Depressed", "우울", "#2F3135", "#000000"),
    FATIGUED("Fatigued", "지친", "#533C32", "#2E3112"),
    SLEEPY("Sleepy", "나른", "#DED84C", "#A2C04F"),
    COMFORTABLE("Comfortable", "편안", "#C0F0FF", "#AFD9FF"),
    ;

}
