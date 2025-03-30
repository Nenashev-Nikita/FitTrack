plugins {
    `kotlin-dsl`
}

repositories {
    mavenCentral()
}

// Use the build script defined in buildSrc
apply(from = rootProject.file("buildSrc/shared.gradle.kts"))

// Use the custom task defined in buildSrc
tasks.register<MyCustomTask>("myCustomTask")