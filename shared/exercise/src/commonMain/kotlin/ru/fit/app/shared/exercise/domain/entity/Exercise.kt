package ru.fit.app.shared.exercise.domain.entity

import kotlinx.serialization.Serializable

@Serializable
data class Exercise(
	val id: Int,
	val name: String,
	val type: ExerciseType,
	val targetMuscles: List<String>? = listOf(),
	val equipment: EquipmentType? = null,
	val stepByStepDescription: List<String>? = listOf(),
	val img: String? = null,
	val difficulty: DifficultyLevel? = null,
	val parameters: ExerciseParameters? = null,
) {

	@Serializable
	enum class ExerciseType {

		STRENGTH,
		CARDIO,
		FLEXIBILITY,
		FUNCTIONAL,
		COMPOUND,
		ISOLATION
	}

	@Serializable
	enum class EquipmentType {

		BARBELL,
		DUMBBELL,
		MACHINE,
		CABLE,
		BODYWEIGHT,
		KETTLEBELL,
		RESISTANCE_BAND,
		MEDICINE_BALL,
		OTHER
	}

	@Serializable
	data class ExerciseParameters(
		val approaches: List<Approaches>,
		val duration: Int? = null,
		val tempo: Tempo? = null,
	)

	@Serializable
	data class Approaches(
		val sets: Int,
		val reps: Int?,
		val weightRange: Float?,
	)

	@Serializable
	data class Tempo(
		val eccentric: Int,
		val pause: Int,
		val concentric: Int,
		val rest: Int,
	)

	@Serializable
	enum class DifficultyLevel {

		BEGINNER,
		INTERMEDIATE,
		ADVANCED,
		ATHLETE
	}
}