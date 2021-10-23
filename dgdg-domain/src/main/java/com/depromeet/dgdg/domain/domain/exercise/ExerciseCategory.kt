package com.depromeet.dgdg.domain.domain.exercise

enum class ExerciseCategory(
    val title: String,
    val description: String
) {

    SHOULDER("Shoulder", "어꺠 운동"),
    CHEST("Chest", "가슴 운동"),
    BACK_WAIST("Back/Waist", "등, 허리"),
    TRICEPS_BICEPS("Triceps/Biceps", "삼두/이두"),
    ABDOMEN("Abdomen", "복부"),
    HIPS_LEGS("Hips/Legs", "엉덩이/하체"),
    AEROBIC("Aerobic", "유산소"),
    ;

}
