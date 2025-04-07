rootProject.name = "FitTrack"
enableFeaturePreview("TYPESAFE_PROJECT_ACCESSORS")

pluginManagement {
    repositories {
        google {
            mavenContent {
                includeGroupAndSubgroups("androidx")
                includeGroupAndSubgroups("com.android")
                includeGroupAndSubgroups("com.google")
            }
        }
        mavenCentral()
        gradlePluginPortal()
    }
}

dependencyResolutionManagement {
    repositories {
        google {
            mavenContent {
                includeGroupAndSubgroups("androidx")
                includeGroupAndSubgroups("com.android")
                includeGroupAndSubgroups("com.google")
            }
        }
        mavenCentral()
    }
}

include(":app")

//features
include(":feature:main")
include(":feature:exercise")
include(":feature:program")
include(":feature:workout")

//shared
include(":shared:training")

//design
include(":design:theme")
include(":design:resources")
include(":design:component")

//core
include(":core:presentation")
//include(":core:database")
//include(":core:network")
//include(":core:mvi")