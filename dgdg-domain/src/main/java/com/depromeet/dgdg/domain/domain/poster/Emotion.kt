package com.depromeet.dgdg.domain.domain.poster

import com.depromeet.dgdg.common.exception.NotFoundException
import com.depromeet.dgdg.domain.domain.poster.Emotion.FontColor.BLACK
import com.depromeet.dgdg.domain.domain.poster.Emotion.FontColor.WHITE
import java.lang.IllegalArgumentException

enum class Emotion(
    val englishTitle: String,
    val koreanTitle: String,
    val startColor: String,
    val endColor: String,
    val fontColor: FontColor
) {

    FURIOUS("Furious", "화난", "#6C0000", "#110202", WHITE),
    JITTERY("Jittery", "초조", "#AA2900", "#221E47", WHITE),
    CHEERFUL("Cheerful", "쾌활", "#FFE500", "#FFF970", BLACK),
    PROUD("Proud", "뿌듯", "#FFE092", "#FF837B", BLACK),
    ANXIOUS("Anxious", "불안", "#082630", "#51094A", WHITE),
    IRRITATED("Irritated", "짜증", "#AE1111", "#AA4700", WHITE),
    JOYFUL("Joyful", "기쁜", "#FFF615", "#FF7A00", BLACK),
    THRILIED("Thrilied", "짜릿", "#FF2876", "#FB2BFF", BLACK),
    SAD("Sad", "슬픈", "#1B1B1B", "#222757", WHITE),
    BORED("Bored", "지루", "#274A26", "#0A2227", WHITE),
    CALM("Calm", "평온", "#BDF4CD", "#C1E5A4", BLACK),
    FULFILLED("Fulfilled", "충만", "#FFAF8F", "#FF7C7C", BLACK),
    DEPRESSED("Depressed", "우울", "#2F3135", "#000000", WHITE),
    FATIGUED("Fatigued", "지친", "#533C32", "#2E3112", WHITE),
    SLEEPY("Sleepy", "나른", "#DED84C", "#A2C04F", BLACK),
    COMFORTABLE("Comfortable", "편안", "#C0F0FF", "#AFD9FF", BLACK),
    ;

    enum class FontColor(
        val fontColor: String
    ) {
        WHITE("#ffffff"),
        BLACK("#000000")
    }

    companion object{
        @JvmStatic
        fun fromCode(code: String): Emotion {
            return try {
                valueOf(code)
            }catch (e : IllegalArgumentException){
                throw NotFoundException("${code}에 해당하는 Emotion이 존재하지 않습니다")
            }
        }
    }
}
