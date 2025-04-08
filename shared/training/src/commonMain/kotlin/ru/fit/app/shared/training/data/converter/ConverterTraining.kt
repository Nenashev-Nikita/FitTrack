package ru.fit.app.shared.training.data.converter

import kotlinx.datetime.Instant
import kotlinx.serialization.KSerializer
import kotlinx.serialization.descriptors.PrimitiveKind
import kotlinx.serialization.descriptors.PrimitiveSerialDescriptor
import kotlinx.serialization.encoding.Decoder
import kotlinx.serialization.encoding.Encoder
import ru.fit.app.shared.training.data.module.TrainingModule
import ru.fit.app.shared.training.domain.entity.Training

class ConverterTraining {

	operator fun invoke(from: List<TrainingModule>): List<Training> =
		from.map { training ->
			Training(
				id = training.id,
				name = training.name,
				date = training.date,
				exercise = training.exercise,
			)
		}
}

object InstantSerializer : KSerializer<Instant> {

	override val descriptor = PrimitiveSerialDescriptor("Instant", PrimitiveKind.STRING)

	override fun serialize(encoder: Encoder, value: Instant) {
		encoder.encodeString(value.toString())
	}

	override fun deserialize(decoder: Decoder): Instant {
		return Instant.parse(decoder.decodeString())
	}
}