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
include(":feature:exercise:details")
include(":feature:exercise:list")
include(":feature:program")
include(":feature:workout")
include(":feature:profile")
include(":feature:progress")
include(":feature:list")
include(":feature:detail")
include(":feature:selection")

//shared
include(":shared:training")
include(":shared:util")
include(":shared:profile")
include(":shared:exercise")

//design
include(":design:theme")
include(":design:resources")
include(":design:component")

//core
include(":core:presentation")
//include(":core:database")
include(":core:network")
//include(":core:mvi")