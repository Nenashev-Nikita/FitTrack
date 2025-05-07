package ru.fit.app.shared.util.date

import kotlinx.datetime.LocalDate
import kotlinx.serialization.KSerializer
import kotlinx.serialization.descriptors.SerialDescriptor
import kotlinx.serialization.encoding.Decoder
import kotlinx.serialization.encoding.Encoder

object LocalDateSerializer : KSerializer<LocalDate> {

	//	private val formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy - HH:mm", Locale.getDefault())
//
//	override val descriptor = PrimitiveSerialDescriptor("LocalDate", PrimitiveKind.STRING)
//
//	override fun serialize(encoder: Encoder, value: LocalDate) {
//		encoder.encodeString(formatter.format(value))
//	}
//
//	override fun deserialize(decoder: Decoder): LocalDate {
//		return LocalDate.parse(decoder.decodeString())
//	}
	override val descriptor: SerialDescriptor
		get() = TODO("Not yet implemented")

	override fun deserialize(decoder: Decoder): LocalDate {
		TODO("Not yet implemented")
	}

	override fun serialize(encoder: Encoder, value: LocalDate) {
		TODO("Not yet implemented")
	}
}