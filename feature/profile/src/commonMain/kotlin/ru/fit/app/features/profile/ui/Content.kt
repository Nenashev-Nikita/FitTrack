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
import androidx.compose.material.icons.filled.Phone
import androidx.compose.material.icons.filled.SelfImprovement
import androidx.compose.material.icons.filled.Sms
import androidx.compose.material.icons.filled.Sports
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.FilterChip
import androidx.compose.material3.Icon
import androidx.compose.material3.LinearProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Switch
import androidx.compose.material3.SwitchDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.unit.dp
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

		Button(onClick = { onProgressClick() }) {
			Text("Progress")
		}

		ProfileHeader(userProfile)
		PhysicalParams(userProfile)
		GoalsSection(userProfile)
		ActivitySection(userProfile)
		NotificationSettings(userProfile.notificationSettings)
//		SocialMediaLinks(userProfile.socialMediaLinks)
	}
}

@Composable
private fun ProfileHeader(user: UserProfile) {
	Card(
		modifier = Modifier
			.fillMaxWidth()
			.padding(16.dp),
		elevation = CardDefaults.cardElevation(4.dp)
	) {
		Column(
			modifier = Modifier.padding(16.dp),
			horizontalAlignment = Alignment.CenterHorizontally
		) {
			Box(
				modifier = Modifier
					.size(120.dp)
					.clip(CircleShape)
					.background(MaterialTheme.colorScheme.primary),
				contentAlignment = Alignment.Center
			) {
				user.avatarUrl?.let {
//					Image(
//						painter = ,
//						contentDescription = null,
//						modifier = Modifier.fillMaxSize()
//					)
				} ?: Text(
					text = user.firstName.take(1) + user.lastName.take(1),
					style = MaterialTheme.typography.displayLarge,
					color = MaterialTheme.colorScheme.onPrimary
				)
			}

			Spacer(modifier = Modifier.height(16.dp))

			Text(
				text = "${user.firstName} ${user.lastName}",
				style = MaterialTheme.typography.headlineMedium
			)

			Text(
				text = "@${user.username}",
				color = MaterialTheme.colorScheme.onSurfaceVariant,
				style = MaterialTheme.typography.bodyLarge
			)

			Spacer(modifier = Modifier.height(16.dp))

			ContactInfo(user.email, user.phone)
		}
	}
}

@Composable
private fun ContactInfo(email: String, phone: String?) {
	Column {
		Row(
			verticalAlignment = Alignment.CenterVertically,
			modifier = Modifier.padding(vertical = 4.dp)
		) {
			Icon(Icons.Default.Email, contentDescription = null)
			Spacer(modifier = Modifier.width(8.dp))
			Text(email)
		}

		phone?.let {
			Row(
				verticalAlignment = Alignment.CenterVertically,
				modifier = Modifier.padding(vertical = 4.dp)
			) {
				Icon(Icons.Default.Phone, contentDescription = null)
				Spacer(modifier = Modifier.width(8.dp))
				Text(phone)
			}
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
//				value = user.birthDate?.let { 23 }?.toString() ?: "-",
				value = "23",
				icon = Icons.Default.Cake
			)
		}
	}
}

@Composable
private fun ParamCard(title: String, value: String, icon: ImageVector) {
	Card(
		elevation = CardDefaults.cardElevation(4.dp)
	) {
		Column(
			modifier = Modifier
				.width(120.dp)
				.padding(16.dp),
			horizontalAlignment = Alignment.CenterHorizontally
		) {
			Icon(icon, contentDescription = title)
			Spacer(modifier = Modifier.height(8.dp))
			Text(value, style = MaterialTheme.typography.headlineSmall)
			Text(title, style = MaterialTheme.typography.bodySmall)
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
		elevation = CardDefaults.cardElevation(4.dp)
	) {
		Column(modifier = Modifier.padding(16.dp)) {
			Text(
				"Уровень активности: ${user.activityLevel.name}",
				style = MaterialTheme.typography.titleLarge
			)
			Spacer(modifier = Modifier.height(8.dp))
			LinearProgressIndicator(
				progress = {
					when (user.activityLevel) {
						ActivityLevel.SEDENTARY -> 0.2f
						ActivityLevel.LIGHT -> 0.4f
						ActivityLevel.MODERATE -> 0.6f
						ActivityLevel.ACTIVE -> 0.8f
						ActivityLevel.VERY_ACTIVE -> 1f
					}
				},
				modifier = Modifier.fillMaxWidth(),
			)

			Spacer(modifier = Modifier.height(16.dp))

			Text("Предпочитаемые тренировки:", style = MaterialTheme.typography.titleMedium)
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
								contentDescription = null
							)
						}
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
		elevation = CardDefaults.cardElevation(4.dp)
	) {
		Column(modifier = Modifier.padding(16.dp)) {
			Text("Мои цели", style = MaterialTheme.typography.titleLarge)

			if (user.fitnessGoals.isEmpty()) {
				Button(
					onClick = { /* Добавить цель */ },
					modifier = Modifier.padding(top = 16.dp)
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
								tint = MaterialTheme.colorScheme.primary
							)
							Spacer(modifier = Modifier.width(8.dp))
							Text(goal)
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
		elevation = CardDefaults.cardElevation(4.dp)
	) {
		Column(modifier = Modifier.padding(16.dp)) {
			Text("Настройки уведомлений", style = MaterialTheme.typography.titleLarge)

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
			tint = MaterialTheme.colorScheme.primary
		)
		Spacer(modifier = Modifier.width(16.dp))
		Text(
			text = label,
			style = MaterialTheme.typography.bodyLarge,
			modifier = Modifier.weight(1f)
		)
		Switch(
			checked = enabled,
			onCheckedChange = null,
			enabled = false,
			colors = SwitchDefaults.colors(
				checkedThumbColor = MaterialTheme.colorScheme.primary,
				checkedTrackColor = MaterialTheme.colorScheme.primaryContainer
			)
		)
	}
}
/*

@Composable
private fun SocialMediaLinks(links: List<SocialMediaLink>) {
	Card(
		modifier = Modifier
			.fillMaxWidth()
			.padding(16.dp),
		elevation = CardDefaults.cardElevation(4.dp)
	) {
		Column(modifier = Modifier.padding(16.dp)) {
			Text("Социальные сети", style = MaterialTheme.typography.titleLarge)

			Spacer(modifier = Modifier.height(8.dp))

			links.forEach { link ->
				SocialMediaLinkItem(
					type = link.type,
					url = link.url,
					modifier = Modifier.padding(vertical = 8.dp)
				)
			}
		}
	}
}

@Composable
private fun SocialMediaLinkItem(type: UserProfile.SocialMediaType, url: String, modifier: Modifier = Modifier) {
	val context = LocalContext.current
	val icon = when (type) {
		UserProfile.SocialMediaType.INSTAGRAM -> Icons.Default.Person
		UserProfile.SocialMediaType.FACEBOOK -> Icons.Default.Facebook
		UserProfile.SocialMediaType.TWITTER -> Icons.Default.Public
		UserProfile.SocialMediaType.LINKEDIN -> Icons.Default.Work
		else -> Icons.Default.Link
	}

	Row(
		verticalAlignment = Alignment.CenterVertically,
		modifier = modifier
			.fillMaxWidth()
			.clickable {
				try {
					context.startActivity(Intent(Intent.ACTION_VIEW, Uri.parse(url)))
				} catch (e: Exception) {
					Toast.makeText(context, "Невозможно открыть ссылку", Toast.LENGTH_SHORT).show()
				}
			}
			.padding(8.dp)
	) {
		Icon(
			icon,
			contentDescription = type.name,
			tint = MaterialTheme.colorScheme.primary
		)
		Spacer(modifier = Modifier.width(16.dp))
		Text(
			text = when (type) {
				UserProfile.SocialMediaType.INSTAGRAM -> "Instagram"
				UserProfile.SocialMediaType.FACEBOOK -> "Facebook"
				UserProfile.SocialMediaType.TWITTER -> "Twitter"
				UserProfile.SocialMediaType.LINKEDIN -> "LinkedIn"
				else -> "Другая сеть"
			},
			style = MaterialTheme.typography.bodyLarge,
			color = MaterialTheme.colorScheme.primary
		)
	}
}
*/