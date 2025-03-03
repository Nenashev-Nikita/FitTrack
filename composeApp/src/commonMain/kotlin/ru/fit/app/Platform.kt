package ru.fit.app

interface Platform {
    val name: String
}

expect fun getPlatform(): Platform