import org.jetbrains.kotlin.gradle.dsl.JvmTarget

plugins {
	alias(libs.plugins.kotlinMultiplatform)
	alias(libs.plugins.androidApplication)
	alias(libs.plugins.composeMultiplatform)
	alias(libs.plugins.composeCompiler)
	alias(libs.plugins.kotlinSerialization)
//	alias(libs.plugins.cocoapods)
	id("kotlin-parcelize")
}

kotlin {
	androidTarget {
		compilerOptions {
			jvmTarget.set(JvmTarget.JVM_11)
		}
	}

	listOf(
		iosX64(),
		iosArm64(),
		iosSimulatorArm64()
	).forEach { iosTarget ->
		iosTarget.binaries.framework {
			baseName = "ComposeApp"
			isStatic = true
		}
	}

	sourceSets {

		androidMain.dependencies {
			implementation(libs.kodein.di.framework.android.x)
			implementation(libs.androidx.activity.compose)

			implementation(libs.kotlin.parcelize.runtime)
		}

		commonMain.dependencies {
			implementation(compose.runtime)
			implementation(compose.foundation)
			implementation(compose.material)
			implementation(compose.ui)
			implementation(compose.components.resources)
			implementation(compose.components.uiToolingPreview)
			implementation(compose.runtime)
			implementation(compose.materialIconsExtended)

			implementation(libs.compose.material3)

			implementation(libs.kodein.di)

			implementation(libs.decompose.core)
			implementation(libs.decompose.extensions.compose)

			implementation(libs.kotlinx.serialization.json)
			implementation(libs.kotlinx.serialization.core)

			implementation(projects.feature.main)
			implementation(projects.feature.exercise.details)
			implementation(projects.feature.exercise.list)
			implementation(projects.feature.profile)
			implementation(projects.feature.program)
			implementation(projects.feature.workout)

			implementation(projects.shared.training)
			implementation(projects.shared.profile)
			implementation(projects.shared.exercise)

			implementation(projects.design.theme)
			implementation(projects.design.resources)
		}

		iosMain.dependencies {

			implementation(projects.feature.main)
			implementation(projects.feature.exercise.details)
			implementation(projects.feature.exercise.list)
			implementation(projects.feature.profile)
			implementation(projects.feature.program)
			implementation(projects.feature.workout)

			implementation(projects.shared.training)
			implementation(projects.shared.profile)
			implementation(projects.shared.exercise)

			implementation(projects.design.theme)
			implementation(projects.design.resources)
		}
	}
}

android {
	namespace = "ru.fit.app"
	compileSdk = libs.versions.android.compileSdk.get().toInt()

	defaultConfig {
		applicationId = "ru.fit.app"
		minSdk = libs.versions.android.minSdk.get().toInt()
		targetSdk = libs.versions.android.targetSdk.get().toInt()
		versionCode = 1
		versionName = "1.0"
	}
	packaging {
		resources {
			excludes += "/META-INF/{AL2.0,LGPL2.1}"
		}
	}
	buildTypes {
		getByName("release") {
			isMinifyEnabled = false
		}
	}
	compileOptions {
		sourceCompatibility = JavaVersion.VERSION_11
		targetCompatibility = JavaVersion.VERSION_11
	}
}

dependencies {

	debugImplementation(compose.uiTooling)
}