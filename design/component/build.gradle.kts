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
			baseName = "design_component"
			isStatic = true
		}
	}

	sourceSets {
		commonMain.dependencies {
			implementation(libs.runtime)
			implementation(libs.foundation)
			implementation(libs.compose.material3)
			implementation(libs.ui)
			implementation(libs.components.resources)
			implementation(libs.material.icons.extended)

			implementation(projects.design.theme)
		}
	}
}

android {
	namespace = "ru.fit.app.design.resources"
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