package com.depromeet.dgdg.domain.domain.exercise

enum class ExerciseCategory(
    val title: String,
    val description: String,
    val displayOrder: Int,
    val isActivated: Boolean
) {

    SHOULDER("Shoulder", "어깨 운동", 1, true),
    CHEST("Chest", "가슴 운동", 2, true),
    BACK_WAIST("Back/Waist", "등, 허리", 3, true),
    TRICEPS_BICEPS("Triceps/Biceps", "삼두/이두", 4, true),
    ABDOMEN("Abdomen", "복부", 5, true),
    HIPS_LEGS("Hips/Legs", "엉덩이/하체", 6, true),
    AEROBIC("Aerobic", "유산소", 7, true),
    ;

}
