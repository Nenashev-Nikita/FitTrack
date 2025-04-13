import org.jetbrains.kotlin.gradle.dsl.JvmTarget

plugins {
	alias(libs.plugins.kotlinMultiplatform)
	alias(libs.plugins.androidLibrary)
	alias(libs.plugins.composeMultiplatform)
	alias(libs.plugins.composeCompiler)
	alias(libs.plugins.kotlinSerialization)
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
			baseName = "features_profile"
			isStatic = true
		}
	}

	sourceSets {

		commonMain.dependencies {
			implementation(project(":design:component"))
			implementation(project(":shared:training"))
			implementation(project(":shared:profile"))
			implementation(project(":shared:exercise"))
			implementation(compose.runtime)
			implementation(compose.foundation)
			implementation(compose.material3)
			implementation(libs.compose.material3)
			implementation(compose.materialIconsExtended)

			implementation(libs.koin.core)
			implementation(libs.koin.compose)
			implementation(libs.koin.compose.viewmodel)

			implementation(libs.decompose.core)

			implementation(libs.kotlinx.serialization.core)
			implementation(libs.kotlinx.serialization.json)
		}
	}
}

android {
	namespace = "ru.fit.app.features.profile"
	compileSdk = libs.versions.android.compileSdk.get().toInt()

	defaultConfig {
		minSdk = libs.versions.android.minSdk.get().toInt()
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