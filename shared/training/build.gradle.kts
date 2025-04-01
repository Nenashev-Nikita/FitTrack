import org.jetbrains.kotlin.gradle.dsl.JvmTarget

plugins {
	alias(libs.plugins.kotlinMultiplatform)
	alias(libs.plugins.androidLibrary)
	alias(libs.plugins.composeMultiplatform)
	alias(libs.plugins.composeCompiler)
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
		}
		commonMain.dependencies {
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
	debugImplementation(compose.uiTooling)
}