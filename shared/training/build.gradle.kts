import org.jetbrains.kotlin.gradle.dsl.JvmTarget

plugins {
	alias(libs.plugins.kotlinMultiplatform)
	alias(libs.plugins.androidLibrary)
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
			baseName = "shared_training"
			isStatic = true
		}
	}

	sourceSets {
		androidMain.dependencies {
			implementation(libs.ktor.client.android)
		}

		commonMain.dependencies {
			implementation(libs.kodein.di)

			implementation(libs.kotlinx.datetime)
			implementation(libs.kotlinx.serialization.json)
			implementation(libs.parcelable)

			implementation(libs.ktor.client.core)
			implementation(libs.ktor.client.mock)
			implementation(libs.ktor.client.content.negotiation)

			implementation(project(":shared:util"))
			implementation(project(":shared:exercise"))

			implementation(project(":core:network"))
		}

		iosMain.dependencies {
			implementation(libs.ktor.client.darwin)
		}

		commonTest.dependencies {
			implementation(libs.kotlin.test)
			implementation(libs.kotlin.coroutine.test)
			implementation(libs.mockk)
		}
		iosTest {
			dependencies {
				implementation(libs.mockk.agent)
			}
		}
	}
}

android {
	namespace = "ru.fit.app.shared.training"
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
}