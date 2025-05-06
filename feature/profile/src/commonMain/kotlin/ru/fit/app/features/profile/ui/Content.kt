package ru.fit.app.features.profile.ui

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ExperimentalLayoutApi
import androidx.compose.foundation.layout.FlowRow
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.DirectionsRun
import androidx.compose.material.icons.filled.Cake
import androidx.compose.material.icons.filled.CheckCircle
import androidx.compose.material.icons.filled.Email
import androidx.compose.material.icons.filled.FitnessCenter
import androidx.compose.material.icons.filled.Height
import androidx.compose.material.icons.filled.MonitorWeight
import androidx.compose.material.icons.filled.Notifications
import androidx.compose.material.icons.filled.SelfImprovement
import androidx.compose.material.icons.filled.Sms
import androidx.compose.material.icons.filled.Sports
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.FilterChip
import androidx.compose.material3.FilterChipDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.LinearProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Switch
import androidx.compose.material3.SwitchDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.unit.dp
import ru.fit.app.FitTheme
import ru.fit.app.design.component.ui.ProgressButton
import ru.fit.app.shared.profile.domain.entity.ActivityLevel
import ru.fit.app.shared.profile.domain.entity.NotificationSettings
import ru.fit.app.shared.profile.domain.entity.UserProfile
import ru.fit.app.shared.profile.domain.entity.WorkoutType

@Composable
fun Content(
	userProfile: UserProfile,
	modifier: Modifier = Modifier,
	onProgressClick: () -> Unit,
) {
	Column(
		modifier = modifier
			.verticalScroll(rememberScrollState())
	) {
		ProfileHeader(userProfile)

		ProgressButton(
			title = "Прогресс по упражнениям",
			subtitle = "Здесь вы сможете проверить свой прогресс про упражнениям",
			onClick = {},
			modifier = Modifier.padding(16.dp)
		)

		PhysicalParams(userProfile)

		GoalsSection(userProfile)

		ActivitySection(userProfile)

//		NotificationSettings(userProfile.notificationSettings)
	}
}

@Composable
private fun ProfileHeader(user: UserProfile) {
	Box(
		modifier = Modifier.fillMaxWidth()
	) {

		Spacer(modifier = Modifier.height(16.dp))
		Text(
			text = "${user.firstName} ${user.lastName}",
			style = MaterialTheme.typography.headlineMedium
		)
		Text(
			text = "@${user.username}",
			color = FitTheme.colors.fondSecondary,
			style = MaterialTheme.typography.bodyLarge
		)
	}
}

@Composable
fun ExerciseImage(
	imageUrl: String?,
	exerciseName: String,
	modifier: Modifier = Modifier
) {
	Box(
		modifier = modifier
			.size(64.dp)
			.background(
				color = FitTheme.colors.permanentPrimaryLight,
				shape = CircleShape
			),
		contentAlignment = Alignment.Center
	) {
		if (imageUrl != null) {
//			AsyncImage(
//				model = imageUrl,
//				contentDescription = "Exercise image for $exerciseName",
//				modifier = Modifier.size(64.dp),
//				onState = { state ->
//					when (state) {
//						is AsyncImagePainter.State.Loading -> {}
//						is AsyncImagePainter.State.Error -> {
//							Text(
//								text = exerciseName.take(2).uppercase(),
//								color = FitTheme.colors.fondInvert,
//								fontSize = 20.sp
//							)
//						}
//						else -> {}
//					}
//				}
//			)
		} else {
			Icon(
				imageVector = Icons.Default.FitnessCenter,
				contentDescription = null,
				tint = FitTheme.colors.fondInvert,
				modifier = Modifier.size(32.dp)
			)
		}
	}
}

@Composable
private fun PhysicalParams(user: UserProfile) {
	LazyRow(
		modifier = Modifier.padding(16.dp),
		horizontalArrangement = Arrangement.spacedBy(16.dp)
	) {
		item { ParamCard("Рост", user.height?.toString() ?: "-", Icons.Default.Height) }
		item { ParamCard("Вес", user.weight?.toString() ?: "-", Icons.Default.MonitorWeight) }
		item {
			ParamCard(
				title = "Возраст",
				value = "23",
				icon = Icons.Default.Cake
			)
		}
	}
}

@Composable
private fun ParamCard(title: String, value: String, icon: ImageVector) {
	Card(
		elevation = CardDefaults.cardElevation(4.dp),
		colors = CardDefaults.cardColors(
			containerColor = FitTheme.colors.bGTertiary,
			contentColor = FitTheme.colors.fondPrimary
		)
	) {
		Column(
			modifier = Modifier
				.width(120.dp)
				.padding(16.dp),
			horizontalAlignment = Alignment.CenterHorizontally
		) {
			Icon(
				icon,
				contentDescription = title,
				tint = FitTheme.colors.permanentPrimary
			)
			Spacer(modifier = Modifier.height(8.dp))
			Text(
				value,
				style = MaterialTheme.typography.headlineSmall
			)
			Text(
				title,
				style = MaterialTheme.typography.bodySmall,
				color = FitTheme.colors.fondSecondary
			)
		}
	}
}

@OptIn(ExperimentalLayoutApi::class)
@Composable
private fun ActivitySection(user: UserProfile) {
	Card(
		modifier = Modifier
			.fillMaxWidth()
			.padding(16.dp),
		elevation = CardDefaults.cardElevation(4.dp),
		colors = CardDefaults.cardColors(
			containerColor = FitTheme.colors.bGTertiary,
			contentColor = FitTheme.colors.fondPrimary
		)
	) {
		Column(modifier = Modifier.padding(16.dp)) {
			Text(
				text = "Уровень активности: ${user.activityLevel.name}",
				style = MaterialTheme.typography.titleLarge
			)
			Spacer(modifier = Modifier.height(8.dp))
			LinearProgressIndicator(
				progress = {
					when (user.activityLevel) {
						ActivityLevel.SEDENTARY -> 0.2f
						ActivityLevel.LIGHT     -> 0.4f
						ActivityLevel.MODERATE  -> 0.6f
						ActivityLevel.ACTIVE    -> 0.8f
						ActivityLevel.VERY_ACTIVE -> 1f
					}
				},
				modifier = Modifier.fillMaxWidth(),
				color = FitTheme.colors.permanentPrimary,
				trackColor = FitTheme.colors.bGTertiary
			)
			Spacer(modifier = Modifier.height(16.dp))
			Text(
				text = "Предпочитаемые тренировки:",
				style = MaterialTheme.typography.titleMedium
			)
			FlowRow(
				modifier = Modifier.padding(top = 8.dp),
				horizontalArrangement = Arrangement.spacedBy(8.dp)
			) {
				user.preferredWorkoutTypes.forEach { type ->
					FilterChip(
						selected = true,
						onClick = {},
						label = { Text(type.name) },
						leadingIcon = {
							Icon(
								when (type) {
									WorkoutType.STRENGTH_TRAINING -> Icons.Default.FitnessCenter
									WorkoutType.CARDIO            -> Icons.AutoMirrored.Filled.DirectionsRun
									WorkoutType.YOGA              -> Icons.Default.SelfImprovement
									else                          -> Icons.Default.Sports
								},
								contentDescription = null,
								tint = FitTheme.colors.permanentPrimary
							)
						},
						colors = FilterChipDefaults.filterChipColors(
							containerColor = FitTheme.colors.bGPrimary,
							selectedContainerColor = FitTheme.colors.bGPrimary,
							labelColor = FitTheme.colors.fondPrimary,
							iconColor = FitTheme.colors.permanentPrimary
						)
					)
				}
			}
		}
	}
}

@Composable
private fun GoalsSection(user: UserProfile) {
	Card(
		modifier = Modifier
			.fillMaxWidth()
			.padding(16.dp),
		elevation = CardDefaults.cardElevation(4.dp),
		colors = CardDefaults.cardColors(
			containerColor = FitTheme.colors.bGTertiary,
			contentColor = FitTheme.colors.fondPrimary
		)
	) {
		Column(modifier = Modifier.padding(16.dp)) {
			Text(
				text = "Мои цели",
				style = MaterialTheme.typography.titleLarge
			)
			if (user.fitnessGoals.isEmpty()) {
				Button(
					onClick = {},
					modifier = Modifier.padding(top = 16.dp),
					colors = androidx.compose.material3.ButtonDefaults.buttonColors(
						containerColor = FitTheme.colors.permanentPrimary,
						contentColor = FitTheme.colors.fondInvert
					)
				) {
					Text("Добавить первую цель")
				}
			} else {
				Column(modifier = Modifier.padding(top = 8.dp)) {
					user.fitnessGoals.forEach { goal ->
						Row(
							verticalAlignment = Alignment.CenterVertically,
							modifier = Modifier.padding(vertical = 8.dp)
						) {
							Icon(
								Icons.Default.CheckCircle,
								contentDescription = null,
								tint = FitTheme.colors.permanentPrimary
							)
							Spacer(modifier = Modifier.width(8.dp))
							Text(
								text = goal,
								color = FitTheme.colors.fondPrimary
							)
						}
					}
				}
			}
		}
	}
}

@Composable
private fun NotificationSettings(settings: NotificationSettings) {
	Card(
		modifier = Modifier
			.fillMaxWidth()
			.padding(16.dp),
		elevation = CardDefaults.cardElevation(4.dp),
		colors = CardDefaults.cardColors(
			containerColor = FitTheme.colors.bGTertiary,
			contentColor = FitTheme.colors.fondPrimary
		)
	) {
		Column(modifier = Modifier.padding(16.dp)) {
			Text(
				text = "Настройки уведомлений",
				style = MaterialTheme.typography.titleLarge
			)
			Spacer(modifier = Modifier.height(8.dp))
			NotificationSettingItem(
				label = "Email-уведомления",
				enabled = settings.emailNotifications,
				icon = Icons.Default.Email
			)
			NotificationSettingItem(
				label = "Push-уведомления",
				enabled = settings.pushNotifications,
				icon = Icons.Default.Notifications
			)
			NotificationSettingItem(
				label = "SMS-уведомления",
				enabled = settings.newFeatures,
				icon = Icons.Default.Sms
			)
		}
	}
}

@Composable
private fun NotificationSettingItem(label: String, enabled: Boolean, icon: ImageVector) {
	Row(
		verticalAlignment = Alignment.CenterVertically,
		modifier = Modifier
			.fillMaxWidth()
			.padding(vertical = 8.dp)
	) {
		Icon(
			icon,
			contentDescription = label,
			tint = FitTheme.colors.permanentPrimary
		)
		Spacer(modifier = Modifier.width(16.dp))
		Text(
			text = label,
			style = MaterialTheme.typography.bodyLarge,
			modifier = Modifier.weight(1f),
			color = FitTheme.colors.fondPrimary
		)
		Switch(
			checked = enabled,
			onCheckedChange = null,
			enabled = false,
			colors = SwitchDefaults.colors(
				checkedThumbColor = FitTheme.colors.permanentPrimary,
				checkedTrackColor = FitTheme.colors.permanentPrimaryLight,
				uncheckedThumbColor = FitTheme.colors.bGTertiary,
				uncheckedTrackColor = FitTheme.colors.bGTertiary
			)
		)
	}
}